package dev.anullihate.vpnguard;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import dev.anullihate.vpnguard.internationalization.CountryCode;
import dev.anullihate.vpnguard.report.GenerateReport;
import dev.anullihate.vpnguard.storage.SimpleCache;
import dev.anullihate.vpnguard.storage.StreamManager;
import dev.anullihate.vpnguard.subnet.SubnetManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.Level;

public class VPNGuard extends PluginBase implements Listener {
    public boolean isCached = false;
    public String apiKey = null;
    public String api_package = null;
    public ConfigReader configReader;
    public SimpleCache cache;
    public final String CACHEFOLDER;
    public StreamManager stream;

    public VPNGuard() {
        this.CACHEFOLDER = "cache" + File.separator;
    }

    public void onLoad() {
        File file = new File(this.getDataFolder(), "config.yml");
        if (!file.exists()) {
            this.saveDefaultConfig();
        }

    }

    public void onEnable() {
        this.stream = new StreamManager(this, this.getDataFolder() + File.separator + "settings.dat");
        this.stream.initDataStream();
        this.configReader = new ConfigReader(this);
        this.getServer().getPluginManager().registerEvents(this, this);
        if (this.configReader.getAPICache()) {
            (new File(this.getDataFolder() + File.separator + this.CACHEFOLDER)).mkdir();
            this.cache = new SimpleCache(this.getDataFolder() + File.separator + this.CACHEFOLDER);
            this.cache.set_cache_extension(".json");
            this.cache.set_cache_time(this.configReader.getAPICacheTime());
        }

        if (this.configReader.getAPIKey().isEmpty()) {
            this.getLogger().info("No API key specified, using free package.");
        } else {
            this.apiKey = this.configReader.getAPIKey();
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String noperm = this.msg("You don't have permission to run this VPNGuard command");
        if (cmd.getName().equalsIgnoreCase("vpnguard")) {
            if (args.length < 1) {
                if (sender.hasPermission("vpnguard.command.vpnguard")) {
                    sender.sendMessage(this.msg("VPNGuard Command List"));
                    if (sender.hasPermission("vpnguard.command.notify")) {
                        sender.sendMessage(this.msg("/vpnguard notify"));
                    }

                    if (this.configReader.getReportLogging() && sender.hasPermission("vpnguard.command.report")) {
                        sender.sendMessage(this.msg("/vpnguard report"));
                    }

                    if (this.cache instanceof SimpleCache) {
                        if (sender.hasPermission("vpnguard.command.clearcache")) {
                            sender.sendMessage(this.msg("/vpnguard clearcache"));
                        }

                        if (sender.hasPermission("vpnguard.command.clearip")) {
                            sender.sendMessage(this.msg("/vpnguard clearip <ipv4 address>"));
                        }
                    }

                    if (sender.hasPermission("vpnguard.command.country")) {
                        sender.sendMessage(this.msg("/vpnguard country toggle"));
                        sender.sendMessage(this.msg("/vpnguard country <add/remove> <country code>"));
                        sender.sendMessage(this.msg("/vpnguard country <view/clear>"));
                    }

                    if (sender.hasPermission("vpnguard.command.subnet")) {
                        sender.sendMessage(this.msg("/vpnguard subnet toggle"));
                        sender.sendMessage(this.msg("/vpnguard subnet <add/remove> <cidr notation>"));
                        sender.sendMessage(this.msg("/vpnguard subnet <view/clear>"));
                    }

                    if (sender.hasPermission("vpnguard.command.whitelist")) {
                        sender.sendMessage(this.msg("/vpnguard whitelist <add/remove> <Org Name>"));
                        sender.sendMessage(this.msg("/vpnguard whitelist <view/clear>"));
                    }

                    if (sender.hasPermission("vpnguard.command.lookup")) {
                        sender.sendMessage(this.msg("/vpnguard lookup <player or ipv4 address>"));
                    }

                    if (sender.hasPermission("vpnguard.command.about")) {
                        sender.sendMessage(this.msg("/vpnguard about"));
                    }
                } else {
                    sender.sendMessage(this.msg("You dont have permission to view VPNGuard commands."));
                }
            } else if (args[0].equalsIgnoreCase("notify")) {
                if (sender.hasPermission("vpnguard.command.notify")) {
                    this.CMD_notify(sender, args);
                } else {
                    sender.sendMessage(noperm);
                }
            } else if (args[0].equalsIgnoreCase("report")) {
                if (sender.hasPermission("vpnguard.command.report")) {
                    this.CMD_report(sender, args);
                } else {
                    sender.sendMessage(noperm);
                }
            } else if (args[0].equalsIgnoreCase("clearcache")) {
                if (sender.hasPermission("vpnguard.command.clearcache")) {
                    this.CMD_clearcache(sender, args);
                } else {
                    sender.sendMessage(noperm);
                }
            } else if (args[0].equalsIgnoreCase("clearip")) {
                if (sender.hasPermission("vpnguard.command.clearip")) {
                    this.CMD_clearip(sender, args);
                } else {
                    sender.sendMessage(noperm);
                }
            } else if (args[0].equalsIgnoreCase("country")) {
                if (sender.hasPermission("vpnguard.command.country")) {
                    this.CMD_country(sender, args);
                } else {
                    sender.sendMessage(noperm);
                }
            } else if (args[0].equalsIgnoreCase("subnet")) {
                if (sender.hasPermission("vpnguard.command.subnet")) {
                    this.CMD_subnet(sender, args);
                } else {
                    sender.sendMessage(noperm);
                }
            } else if (args[0].equalsIgnoreCase("whitelist")) {
                if (sender.hasPermission("vpnguard.command.whitelist")) {
                    this.CMD_whitelist(sender, args);
                } else {
                    sender.sendMessage(noperm);
                }
            } else if (args[0].equalsIgnoreCase("lookup")) {
                if (sender.hasPermission("vpnguard.command.lookup")) {
                    this.CMD_lookup(sender, args);
                } else {
                    sender.sendMessage(noperm);
                }
            } else if (args[0].equalsIgnoreCase("about")) {
                if (sender.hasPermission("vpnguard.command.about")) {
                    this.CMD_about(sender, args);
                } else {
                    sender.sendMessage(noperm);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public void onDisable() {
        this.stream.saveDataStream();
        this.configReader.onDisable();
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent e) {
        String playerName = e.getPlayer().getName();
        String address = e.getPlayer().getAddress();
        if (this.configReader.getLogging()) {
            this.getLogger().info(String.format("Player %s is trying to connect with IP: %s", playerName, address));
        }

        if (e.getPlayer().isOp() && this.configReader.getBypassOps()) {
            if (this.configReader.getLogging()) {
                this.getLogger().info(String.format("Player %s is operative bypassing check", playerName));
            }
        } else if (e.getPlayer().hasPermission("vpnguard.permission.bypass")) {
            if (this.configReader.getLogging()) {
                this.getLogger().info(String.format("Bypassing check for player %s", playerName));
            }
        } else if ((Boolean)this.stream.data.get("subnet_toggle")) {
            SubnetManager subnet = new SubnetManager(this, e.getPlayer().getAddress());
            if (subnet.isBanned()) {
                e.getPlayer().kick(this.configReader.getSubnetMessage());
                if (this.configReader.getLogging()) {
                    this.getLogger().info(String.format("%s is joining from a IP Range that you have subnet banned.", playerName));
                }
            } else {
                this.callAPI(e.getPlayer());
            }
        } else {
            this.callAPI(e.getPlayer());
        }

    }

    private void callAPI(Player player) {
        this.getServer().getScheduler().scheduleAsyncTask(this, new CallAPI(this, 0, player, (CommandSender)null, (String)null));
    }

    public String msg(String msg) {
        return this.configReader.getPrefix() + msg;
    }

    // command related
    private void CMD_notify(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            UUID uuid = player.getUniqueId();
            ArrayList<UUID> notifyList = (ArrayList)this.stream.data.get("notify");
            if (notifyList.contains(uuid)) {
                notifyList.remove(uuid);
                this.stream.data.put("notify", notifyList);
                sender.sendMessage(this.msg("Notifications Disabled"));
            } else {
                notifyList.add(uuid);
                this.stream.data.put("notify", notifyList);
                sender.sendMessage(this.msg("Notifications Enabled"));
            }
        } else {
            sender.sendMessage(this.msg("This command can only be executed in-game."));
        }

    }

    private void CMD_report(CommandSender sender, String[] args) {
        if (this.configReader.getReportLogging()) {
            HashMap<Object, Object> history = (HashMap)this.stream.data.get("history");
            if (history.isEmpty()) {
                sender.sendMessage(this.msg("Sorry no data to generate a report."));
            } else {
                this.getServer().getScheduler().scheduleAsyncTask(this, new GenerateReport(this, sender));
            }
        } else {
            sender.sendMessage(this.msg("Report Logging is Disabled!"));
        }

    }

    private void CMD_clearcache(CommandSender sender, String[] args) {
        if (this.cache instanceof SimpleCache) {
            int numFiles = this.cache.get_total_cached();
            if (numFiles == 0) {
                sender.sendMessage(this.msg("There are no cached files to delete."));
            } else {
                sender.sendMessage(this.msg("Now attempting to delete: " + numFiles + " cached file(s)"));
                this.cache.clearCache();
                sender.sendMessage(this.msg("API cache cleared!"));
            }
        } else {
            sender.sendMessage(this.msg("API Caching is Disabled!"));
        }

    }

    private void CMD_clearip(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(this.msg("Usage: /vpnguard clearip <ipv4 address>"));
        } else if (checkIPv4(args[1])) {
            if (this.cache instanceof SimpleCache) {
                try {
                    this.cache.clearCache(args[1]);
                    if (this.cache.is_cached(args[1])) {
                        sender.sendMessage(this.msg("Unable to delete the cached API file."));
                    } else {
                        sender.sendMessage(this.msg("Successfully deleted the cached API file."));
                    }
                } catch (FileNotFoundException var4) {
                    sender.sendMessage(this.msg("No cache file found matching the ipv4 address: " + args[1]));
                }
            } else {
                sender.sendMessage(this.msg("API Caching is Disabled!"));
            }
        } else {
            sender.sendMessage(this.msg("You did not enter in a valid ipv4 address."));
        }

    }

    private void CMD_country(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(this.msg("Command Usage for Country Blocking"));
            sender.sendMessage("Usage: /vpnguard country toggle");
            sender.sendMessage("Usage: /vpnguard country <add/remove> <country code>");
            sender.sendMessage("Usage: /vpnguard country <view/clear>");
        } else {
            Boolean toggle = (Boolean)this.stream.data.get("country_toggle");
            ArrayList<String> arrayList = (ArrayList)this.stream.data.get("countries");
            String mode = toggle ? "Blacklist" : "Whitelist";
            if (!args[1].equalsIgnoreCase("add") && !args[1].equalsIgnoreCase("remove")) {
                if (args[1].equalsIgnoreCase("clear")) {
                    arrayList.clear();
                    this.stream.data.put("countries", arrayList);
                    sender.sendMessage(this.msg("Cleared all Saved Country Codes from your " + mode));
                } else if (args[1].equalsIgnoreCase("view")) {
                    if (arrayList.isEmpty()) {
                        sender.sendMessage(this.msg("You have no countries in your " + mode));
                    } else {
                        sender.sendMessage(this.msg("The following Countries are in your " + mode));
                        Iterator var8 = arrayList.iterator();

                        while(var8.hasNext()) {
                            String countryCode = (String)var8.next();
                            sender.sendMessage(this.msg(countryCode.toUpperCase() + " => " + CountryCode.getByCode(countryCode.toUpperCase()).getName()));
                        }
                    }
                } else if (args[1].equalsIgnoreCase("toggle")) {
                    toggle = !toggle;
                    this.stream.data.put("country_toggle", toggle);
                    if (toggle) {
                        sender.sendMessage(this.msg("Country Blacklist Enabled."));
                        sender.sendMessage(this.msg("Users will be forbidded to join if their IP belongs to a blacklisted country."));
                    } else {
                        sender.sendMessage(this.msg("Country Whitelist Enabled."));
                        sender.sendMessage(this.msg("Users will only be allowed to join if their IP belongs to a whitelisted country."));
                    }
                } else {
                    sender.sendMessage(this.msg("Command Usage for Country Blocking"));
                    sender.sendMessage("Usage: /vpnguard country toggle");
                    sender.sendMessage("Usage: /vpnguard country <add/remove> <country code>");
                    sender.sendMessage("Usage: /vpnguard country <view/clear>");
                }
            } else if (args.length < 3) {
                sender.sendMessage(this.msg("Usage: /vpnguard country <add/remove> <country code>"));
            } else {
                String userCC = args[2].toUpperCase();
                CountryCode cc = CountryCode.getByCode(userCC);
                if (cc == null) {
                    sender.sendMessage(this.msg(userCC + " is not a valid ISO 3166-1 alpha-2 Country Code."));
                } else {
                    if (args[1].equalsIgnoreCase("add")) {
                        if (arrayList.contains(userCC)) {
                            sender.sendMessage(this.msg(cc.getName() + " is already on the " + mode));
                        } else {
                            arrayList.add(userCC);
                            this.stream.data.put("countries", arrayList);
                            sender.sendMessage(this.msg("Added: " + cc.getName() + " to your " + mode));
                        }
                    } else if (args[1].equals("remove")) {
                        if (arrayList.contains(userCC)) {
                            arrayList.remove(userCC);
                            this.stream.data.put("countries", arrayList);
                            sender.sendMessage(this.msg("Removed: " + cc.getName() + " from your " + mode));
                        } else {
                            sender.sendMessage(this.msg(cc.getName() + " is not on the " + mode));
                        }
                    }

                    if (this.api_package != null && !this.api_package.equals("Professional")) {
                        sender.sendMessage(this.msg("Please Note: Country Whitelisting and Blacklisting will only work if you have the Professional API Package from the API Provider."));
                        sender.sendMessage(this.msg("You are currently on the " + this.api_package + " package."));
                        sender.sendMessage(this.msg("For more information: /vpnguard about"));
                    }
                }
            }
        }

    }

    private void CMD_subnet(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(this.msg("Command Usage for Subnet Banning"));
            sender.sendMessage("Usage: /vpnguard subnet toggle");
            sender.sendMessage("Usage: /vpnguard subnet <add/remove> <cidr notation>");
            sender.sendMessage("Usage: /vpnguard subnet <view/clear>");
        } else {
            Boolean toggle = (Boolean)this.stream.data.get("subnet_toggle");
            ArrayList<String> arrayList = (ArrayList)this.stream.data.get("subnets");
            if (!args[1].equalsIgnoreCase("add") && !args[1].equalsIgnoreCase("remove")) {
                if (args[1].equalsIgnoreCase("clear")) {
                    arrayList.clear();
                    this.stream.data.put("subnets", arrayList);
                    sender.sendMessage(this.msg("Cleared all Saved Subnet Rules!"));
                } else if (args[1].equalsIgnoreCase("view")) {
                    if (arrayList.isEmpty()) {
                        sender.sendMessage(this.msg("You have no subnets banned."));
                    } else {
                        sender.sendMessage(this.msg("The following subnets are banned"));
                        Iterator var9 = arrayList.iterator();

                        while(var9.hasNext()) {
                            String subnet = (String)var9.next();
                            sender.sendMessage(this.msg(subnet));
                        }
                    }
                } else if (args[1].equalsIgnoreCase("toggle")) {
                    toggle = !toggle;
                    this.stream.data.put("subnet_toggle", toggle);
                    if (toggle) {
                        sender.sendMessage(this.msg("Subnet Banning Enabled."));
                        sender.sendMessage(this.msg("Users will be forbidded to join if their IP is subnet banned."));
                    } else {
                        sender.sendMessage(this.msg("Subnet Banning Disabled."));
                    }
                } else {
                    sender.sendMessage(this.msg("Command Usage for Subnet Banning"));
                    sender.sendMessage("Usage: /vpnguard subnet toggle");
                    sender.sendMessage("Usage: /vpnguard subnet <add/remove> <cidr notation>");
                    sender.sendMessage("Usage: /vpnguard subnet <view/clear>");
                }
            } else if (args.length < 3) {
                sender.sendMessage(this.msg("Usage: /vpnguard subnet <add/remove> <cidr notation>"));
            } else {
                String cidr = args[2];
                if (cidr.contains("/")) {
                    String[] part = cidr.split("/");
                    if (part.length != 2) {
                        sender.sendMessage(this.msg(cidr + " is not in valid CIDR Notation."));
                    } else if (isInteger(part[1]) && checkIPv4(part[0])) {
                        String ip = part[0];
                        int block = Integer.parseInt(part[1]);
                        if (block >= 0 && block <= 32) {
                            if (args[1].equalsIgnoreCase("add")) {
                                if (arrayList.contains(cidr)) {
                                    sender.sendMessage(this.msg(cidr + " is already subnet banned."));
                                } else if ((new SubnetManager(this, ip)).isBanned()) {
                                    sender.sendMessage(this.msg("You have a conflicting subnet rule, please remove it before you can add " + cidr));
                                } else {
                                    arrayList.add(cidr);
                                    this.stream.data.put("subnets", arrayList);
                                    sender.sendMessage(this.msg("Added: " + cidr + " to your subnet ban list."));
                                }
                            } else if (args[1].equalsIgnoreCase("remove")) {
                                if (arrayList.contains(cidr)) {
                                    arrayList.remove(cidr);
                                    this.stream.data.put("subnets", arrayList);
                                    sender.sendMessage(this.msg("Removed: " + cidr + " from your subnet ban list."));
                                } else {
                                    sender.sendMessage(this.msg(cidr + " is not subnet banned."));
                                }
                            }
                        } else {
                            sender.sendMessage(this.msg(block + " is not in valid CIDR Block."));
                        }
                    } else {
                        sender.sendMessage(this.msg(cidr + " is not in valid CIDR Notation."));
                    }
                } else {
                    sender.sendMessage(this.msg(cidr + " is not in valid CIDR Notation."));
                }
            }
        }

    }

    private void CMD_whitelist(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(this.msg("Command Usage for ISP Whitelisting"));
            sender.sendMessage("Usage: /vpnguard whitelist <add/remove> <Org Name>");
            sender.sendMessage("Usage: /vpnguard whitelist <view/clear>");
        } else {
            ArrayList<String> arrayList = (ArrayList)this.stream.data.get("allowed_orgs");
            if (!args[1].equalsIgnoreCase("add") && !args[1].equalsIgnoreCase("remove")) {
                if (args[1].equalsIgnoreCase("clear")) {
                    arrayList.clear();
                    this.stream.data.put("allowed_orgs", arrayList);
                    sender.sendMessage(this.msg("Cleared all Saved Whitelisting Rules!"));
                } else if (args[1].equalsIgnoreCase("view")) {
                    if (arrayList.isEmpty()) {
                        sender.sendMessage(this.msg("You have no organization whitelisted."));
                    } else {
                        sender.sendMessage(this.msg("The following ISP / Organizations are Whitelisted"));
                        Iterator var7 = arrayList.iterator();

                        while(var7.hasNext()) {
                            String orgs = (String)var7.next();
                            sender.sendMessage(this.msg(orgs));
                        }
                    }
                } else {
                    sender.sendMessage(this.msg("Command Usage for ISP/Org Whitelisting"));
                    sender.sendMessage("Usage: /vpnguard whitelist <add/remove> <Org Name>");
                    sender.sendMessage("Usage: /vpnguard whitelist <view/clear>");
                }
            } else if (args.length < 3) {
                sender.sendMessage(this.msg("Usage: /vpnguard whitelist <add/remove> <Org Name>"));
            } else {
                String org = "";

                for(int i = 2; i < args.length; ++i) {
                    String arg = args[i] + " ";
                    org = org + arg;
                }

                org = org.replaceAll("\\s+$", "");
                if (args[1].equalsIgnoreCase("add")) {
                    if (arrayList.contains(org)) {
                        sender.sendMessage(this.msg(org + " is already whitelisted."));
                    } else {
                        arrayList.add(org);
                        this.stream.data.put("allowed_orgs", arrayList);
                        sender.sendMessage(this.msg("Added: " + org + " to your organization whitelist."));
                    }
                } else if (args[1].equalsIgnoreCase("remove")) {
                    if (arrayList.contains(org)) {
                        arrayList.remove(org);
                        this.stream.data.put("allowed_orgs", arrayList);
                        sender.sendMessage(this.msg("Removed: " + org + " from your organization whitelist."));
                    } else {
                        sender.sendMessage(this.msg(org + " is not whitelisted."));
                    }
                }
            }
        }

    }

    private void CMD_lookup(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(this.msg("Usage: /vpnguard lookup <player or ipv4 address>"));
        } else if (checkIPv4(args[1])) {
            this.getServer().getScheduler().scheduleAsyncTask(this, new CallAPI(this, 1, (Player)null, sender, args[1]));
        } else {
            Player p = this.getServer().getPlayer(args[1]);
            if (p != null) {
                String ip = p.getAddress();
                this.getServer().getScheduler().scheduleAsyncTask(this, new CallAPI(this, 1, (Player)null, sender, ip));
            } else {
                sender.sendMessage(this.msg("You did not enter in a valid ipv4 address or the player username you entered is not online."));
            }
        }

    }

    private void CMD_about(CommandSender sender, String[] args) {
        sender.sendMessage(this.msg("VPNGuard v" + this.getServer().getPluginManager().getPlugin("VPNGuard").getDescription().getVersion()));
        sender.sendMessage("");
        if (this.configReader.getAPIKey().isEmpty()) {
            sender.sendMessage(this.msg("Using API Key?" + TextFormat.GRAY + " NO."));
            sender.sendMessage(this.msg("API Package: " + TextFormat.GRAY + "FREE"));
        } else {
            sender.sendMessage(this.msg("Using API Key?" + TextFormat.GREEN + " YES."));
            if (this.api_package != null) {
                sender.sendMessage(this.msg("API Package: " + TextFormat.GREEN + this.api_package.toUpperCase()));
            }
        }

        if (this.configReader.getAPICache()) {
            int numFiles = this.cache.get_total_cached();
            sender.sendMessage(this.msg("API Request Caching:" + TextFormat.GREEN + " ENABLED"));
            if (numFiles > 0) {
                sender.sendMessage(this.msg("API Requests Cached: " + TextFormat.AQUA + numFiles));
            }
        } else {
            sender.sendMessage(this.msg("API Request Caching:" + TextFormat.GRAY + " DISABLED"));
        }

        sender.sendMessage(this.msg("API Homepage:" + TextFormat.AQUA + " https://vpnblocker.net/"));
        sender.sendMessage("");
        sender.sendMessage(this.msg("All plugin inquiries should be sent to the Plugin Development page. All other inquiries regarding the API should be forwarded to: support@vpnblocker.net"));
    }

    private static boolean checkIPv4(String ip) {
        boolean isIPv4;
        try {
            InetAddress inet = InetAddress.getByName(ip);
            isIPv4 = inet.getHostAddress().equals(ip) && inet instanceof Inet4Address;
        } catch (UnknownHostException var3) {
            isIPv4 = false;
        }

        return isIPv4;
    }

    private static boolean isInteger(String s) {
        boolean isValidInteger = false;

        try {
            Integer.parseInt(s);
            isValidInteger = true;
        } catch (NumberFormatException var3) {
            isValidInteger = false;
        }

        return isValidInteger;
    }
}
