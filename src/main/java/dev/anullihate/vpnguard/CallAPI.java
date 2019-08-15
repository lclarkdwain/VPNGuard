package dev.anullihate.vpnguard;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.TextFormat;
import dev.anullihate.vpnguard.storage.SimpleCache;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CallAPI extends AsyncTask {
    private int mode;
    private VPNGuard plugin;
    private final Player player;
    private CommandSender sender;
    private String ip;

    public CallAPI(VPNGuard plugin, int i, Player player, CommandSender sender, String ip) {
        this.plugin = plugin;
        this.mode = i;
        this.player = player;
        this.sender = sender;
        this.ip = ip;
    }

    public void onRun() {
        try {
            String rawJSON;
            JSONObject result;
            String subdivision;
            if (this.mode == 0) {
                rawJSON = this.getJSON(this.player.getAddress());
                result = new JSONObject(rawJSON);
                ArrayList<UUID> notifyPlayers = (ArrayList)this.plugin.stream.data.get("notify");
                ArrayList<String> allowed_orgs = (ArrayList)this.plugin.stream.data.get("allowed_orgs");
                if (result.get("status").equals("success")) {
                    if (this.plugin.cache instanceof SimpleCache && !this.plugin.isCached) {
                        this.plugin.cache.set_cache(this.player.getAddress(), rawJSON);
                    }

                    String api_package = result.getString("package");
                    if (!this.plugin.isCached) {
                        this.plugin.api_package = api_package;
                    }

                    String org = result.getString("org");
                    boolean isHost = result.getBoolean("host-ip");
                    final String country = api_package.equals("Professional") ? !result.getJSONObject("country").isNull("name") ? result.getJSONObject("country").getString("name") : null  : null;
                    String cc = null;
                    subdivision = null;
                    String city = null;
                    String postal = null;
                    long lat = 0L;
                    long lon = 0L;
                    if (api_package.equals("Professional")) {
//                        if (!result.getJSONObject("country").isNull("name")) {
//                            country = result.getJSONObject("country").getString("name");
//                        }

                        if (!result.getJSONObject("country").isNull("code")) {
                            cc = result.getJSONObject("country").getString("code");
                        }

                        if (!result.getJSONObject("subdivision").isNull("name")) {
                            subdivision = result.getJSONObject("subdivision").getString("name");
                        }

                        if (!result.isNull("city")) {
                            city = result.getString("city");
                        }

                        if (!result.isNull("postal")) {
                            postal = result.getString("postal");
                        }

                        if (!result.getJSONObject("location").isNull("lat")) {
                            lat = result.getJSONObject("location").getLong("lat");
                        }

                        if (!result.getJSONObject("location").isNull("long")) {
                            lon = result.getJSONObject("location").getLong("long");
                        }
                    }

                    int remaining_requests;
                    ArrayList countryList;
                    if (!allowed_orgs.contains(org) && isHost) {
                        if (this.plugin.configReader.getReportLogging()) {
                            HashMap history = (HashMap)this.plugin.stream.data.get("history");
                            if (history.containsKey(this.player.getUniqueId())) {
                                countryList = (ArrayList)history.get(this.player.getUniqueId());
                                if (!countryList.contains(this.player.getAddress())) {
                                    countryList.add(this.player.getAddress());
                                }
                            } else {
                                countryList = new ArrayList();
                                countryList.add(this.player.getAddress());
                                history.put(this.player.getUniqueId(), countryList);
                            }
                        }

                        (new NukkitRunnable() {
                            public void run() {
                                try {
                                    Thread.sleep((long)CallAPI.this.plugin.configReader.getCommandsDelay());
                                    Iterator iterator = CallAPI.this.plugin.configReader.getCommands().iterator();

                                    while(iterator.hasNext() && CallAPI.this.player != null) {
                                        String command = (String)iterator.next();
                                        command = command.replace("%p", CallAPI.this.player.getName());
                                        command = command.replace("%ip", CallAPI.this.player.getAddress());
                                        command = TextFormat.colorize('&', command);
                                        CallAPI.this.plugin.getServer().dispatchCommand(CallAPI.this.plugin.getServer().getConsoleSender(), command);
                                    }
                                } catch (InterruptedException var3) {
                                    Logger.getLogger(CallAPI.class.getName()).log(Level.SEVERE, (String)null, var3);
                                }

                            }
                        }).runTask(this.plugin);
                        Iterator var33 = notifyPlayers.iterator();

                        while(var33.hasNext()) {
                            UUID uuid = (UUID)var33.next();
                            Optional<Player> notifyPlayer = this.plugin.getServer().getPlayer(uuid);
                            Player player = notifyPlayer.get();
                            if (player != null && player.isOnline()) {
                                player.sendMessage(this.plugin.msg(this.player.getName() + " tried connecting with a anonymizer."));
                            }
                        }

                        if (this.plugin.configReader.getLogging()) {
                            if (api_package.equals("Professional")) {
                                this.plugin.getLogger().info("{0} is using a anonymizer: IP Details -> Org: {1}, Country: {2}, Subdivision: {3}, City: {4}");
                            } else {
                                this.plugin.getLogger().info("{0} is using a anonymizer: IP Details -> Org: {1}");
                                if (!this.plugin.isCached && api_package.equals("Free")) {
                                    remaining_requests = result.getInt("remaining_requests");
                                    this.plugin.getLogger().info("You have {0} API Requests remaining on your Free Package");
                                }
                            }
                        }
                    } else if (allowed_orgs.contains(org)) {
                        if (this.plugin.configReader.getLogging()) {
                            this.plugin.getLogger().info("{0} connected from a whitelisted ISP/Organization.");
                        }

                        if (!this.plugin.isCached && api_package.equals("Free")) {
                            remaining_requests = result.getInt("remaining_requests");
                            this.plugin.getLogger().info("You have {0} API Requests remaining on your Free Package.");
                        }
                    } else if (api_package.equals("Professional")) {
                        Boolean country_toggle = (Boolean)this.plugin.stream.data.get("country_toggle");
                        countryList = (ArrayList)this.plugin.stream.data.get("countries");
                        String type = country_toggle ? "Blacklist" : "Whitelist";
                        if (country_toggle) {
                            if (countryList.contains(cc)) {
                                (new NukkitRunnable() {
                                    public void run() {
                                        try {
                                            Thread.sleep((long)CallAPI.this.plugin.configReader.getCommandsDelay());
                                            String command = CallAPI.this.plugin.configReader.getCountryDenyMessage();
                                            command = command.replace("%p", CallAPI.this.player.getName());
                                            if (country != null) {
                                                command = command.replace("%countryname", country);
                                            } else {
                                                command = command.replace("%countryname", "Unknown");
                                            }

                                            CallAPI.this.player.kick(command);
                                        } catch (InterruptedException var2) {
                                            Logger.getLogger(CallAPI.class.getName()).log(Level.SEVERE, (String)null, var2);
                                        }

                                    }
                                }).runTask(this.plugin);
                                if (this.plugin.configReader.getLogging()) {
                                    this.plugin.getLogger().info("{0} was kicked because of the country {1}");
                                }
                            } else if (this.plugin.configReader.getLogging()) {
                                this.plugin.getLogger().info("{0} has passed VPNGuard checks.");
                            }
                        } else if (!countryList.contains(cc)) {
                            (new NukkitRunnable() {
                                public void run() {
                                    try {
                                        Thread.sleep((long)CallAPI.this.plugin.configReader.getCommandsDelay());
                                        String command = CallAPI.this.plugin.configReader.getCountryDenyMessage();
                                        command = command.replace("%p", CallAPI.this.player.getName());
                                        if (country != null) {
                                            command = command.replace("%countryname", country);
                                        } else {
                                            command = command.replace("%countryname", "Unknown");
                                        }

                                        CallAPI.this.player.kick(command);
                                    } catch (InterruptedException var2) {
                                        Logger.getLogger(CallAPI.class.getName()).log(Level.SEVERE, (String)null, var2);
                                    }

                                }
                            }).runTask(this.plugin);
                            if (this.plugin.configReader.getLogging()) {
                                this.plugin.getLogger().info("{0} was kicked because of the country {1}");
                            }
                        } else if (this.plugin.configReader.getLogging()) {
                            this.plugin.getLogger().info("{0} has passed VPNGuard checks.");
                        }
                    } else {
                        if (this.plugin.configReader.getLogging()) {
                            this.plugin.getLogger().info("{0} has passed VPNGuard checks.");
                        }

                        if (!this.plugin.isCached && api_package.equals("Free")) {
                            remaining_requests = result.getInt("remaining_requests");
                            this.plugin.getLogger().info("You have {0} API Requests remaining on your Free Package.");
                        }
                    }
                } else if (result.get("status").equals("failed")) {
                    if (!result.getString("msg").contains("Invalid API Key") && !result.getString("msg").contains("Payment Overdue")) {
                        if (!this.plugin.configReader.getBypassCheck()) {
                            (new NukkitRunnable() {
                                public void run() {
                                    CallAPI.this.player.kick(CallAPI.this.plugin.configReader.getBypassMessage());
                                }
                            }).runTask(this.plugin);
                        }
                    } else {
                        this.plugin.getLogger().critical("API Server Returned Error Message: {0} Shutting down server to prevent blacklisting on API Database");
                        this.plugin.getServer().shutdown();
                    }

                    Iterator var24 = notifyPlayers.iterator();

                    while(var24.hasNext()) {
                        UUID uuid = (UUID)var24.next();
                        Optional<Player> notifyPlayer = this.plugin.getServer().getPlayer(uuid);
                        Player player = notifyPlayer.get();
                        if (player != null && player.isOnline()) {
                            player.sendMessage(this.plugin.msg("API Server Returned Error: " + result.getString("msg")));
                        }
                    }

                    this.plugin.getLogger().critical("API Server Returned Error Message: {0} when {1} tried to connect");
                }
            } else if (this.mode == 1) {
                rawJSON = this.getJSON(this.ip);
                result = new JSONObject(rawJSON);
                if (result.get("status").equals("success")) {
                    if (this.plugin.cache instanceof SimpleCache && !this.plugin.isCached) {
                        this.plugin.cache.set_cache(this.ip, rawJSON);
                    }

                    String api_package = result.getString("package");
                    if (!this.plugin.isCached) {
                        this.plugin.api_package = api_package;
                    }

                    String org = result.getString("org");
                    boolean isHost = result.getBoolean("host-ip");
                    long lat = 0L;
                    long lon = 0L;
                    this.sender.sendMessage(this.plugin.msg("IP Address: " + this.ip));
                    this.sender.sendMessage(this.plugin.msg("Organization: " + org));
                    if (api_package.equals("Professional")) {
                        if (!result.getJSONObject("country").isNull("name")) {
                            subdivision = result.getJSONObject("country").getString("name");
                            this.sender.sendMessage(this.plugin.msg("Country: " + subdivision));
                        }

                        if (!result.getJSONObject("subdivision").isNull("name")) {
                            subdivision = result.getJSONObject("subdivision").getString("name");
                            this.sender.sendMessage(this.plugin.msg("Subdivision: " + subdivision));
                        }

                        if (!result.isNull("city")) {
                            subdivision = result.getString("city");
                            this.sender.sendMessage(this.plugin.msg("City: " + subdivision));
                        }

                        if (!result.isNull("postal")) {
                            subdivision = result.getString("postal");
                            this.sender.sendMessage(this.plugin.msg("Postal Code: " + subdivision));
                        }

                        if (!result.getJSONObject("location").isNull("lat")) {
                            lat = result.getJSONObject("location").getLong("lat");
                        }

                        if (!result.getJSONObject("location").isNull("long")) {
                            lon = result.getJSONObject("location").getLong("long");
                        }
                    }

                    if (lat != 0L && lon != 0L) {
                        this.sender.sendMessage(this.plugin.msg("Latitude: " + lat + " Longitude: " + lon));
                    }

                    if (!this.plugin.isCached && api_package.equals("Free")) {
                        int remaining_requests = result.getInt("remaining_requests");
                        this.sender.sendMessage(this.plugin.msg("You have " + remaining_requests + " API Requests Remaining on your Free Package."));
                    }

                    if (isHost) {
                        this.sender.sendMessage(this.plugin.msg(this.ip + " belongs to a hosting organization."));
                    } else {
                        this.sender.sendMessage(this.plugin.msg(this.ip + " does not seem to belong to a hosting organization. If you believe this is an error please report it to the API provider to have it fixed."));
                    }
                } else if (result.get("status").equals("failed")) {
                    this.sender.sendMessage("I'm sorry, API server returned the following error message: " + result.get("msg"));
                }
            }
        } catch (JSONException | IOException var21) {
            if (this.mode == 0) {
                if (!this.plugin.configReader.getBypassCheck()) {
                    (new NukkitRunnable() {
                        public void run() {
                            try {
                                Thread.sleep((long)CallAPI.this.plugin.configReader.getCommandsDelay());
                                CallAPI.this.player.kick(CallAPI.this.plugin.configReader.getBypassMessage());
                            } catch (InterruptedException var2) {
                                Logger.getLogger(CallAPI.class.getName()).log(Level.SEVERE, (String)null, var2);
                            }

                        }
                    }).runTask(this.plugin);
                }

                var21.printStackTrace();
                this.plugin.getLogger().critical("Unable to perform anonymization checks when {0} tried to connect.");
            } else if (this.mode == 1) {
                this.plugin.getLogger().critical("Error: {0}", var21);
                this.sender.sendMessage(this.plugin.msg("Unable to grab data from API Server, check console for error logs."));
            }
        }

    }

    public String getJSON(String ip) throws IOException {
        String api;
        if (this.plugin.apiKey == null) {
            api = "http://api.vpnblocker.net/v2/json/" + ip;
        } else {
            api = "http://api.vpnblocker.net/v2/json/" + ip + "/" + this.plugin.apiKey;
        }

        int timeout = this.plugin.configReader.getTimeout();
        String userAgent = "VPNGuard v" + this.plugin.getServer().getPluginManager().getPlugin("VPNGuard").getDescription().getVersion() + " (Bukkit:" + this.plugin.getServer().getVersion() + ") on " + this.plugin.getServer().getPort();
        StringBuilder response = new StringBuilder();
        URLConnection connection = (new URL(api)).openConnection();
        connection.setRequestProperty("User-Agent", userAgent);
        connection.setConnectTimeout(timeout);
        if (this.plugin.cache instanceof SimpleCache) {
            if (this.plugin.cache.is_cached(ip)) {
                this.plugin.isCached = true;
                response.append(this.plugin.cache.get_cache(ip));
            } else {
                this.plugin.isCached = false;
                response.append(this.plugin.cache.grab_url(api, this.plugin.configReader.getTimeout(), userAgent));
            }
        } else {
            response.append((new SimpleCache()).grab_url(api, this.plugin.configReader.getTimeout(), userAgent));
        }

        return response.toString();
    }
}
