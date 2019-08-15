package dev.anullihate.vpnguard.report;

import cn.nukkit.IPlayer;
import cn.nukkit.OfflinePlayer;
import cn.nukkit.command.CommandSender;
import cn.nukkit.scheduler.AsyncTask;
import dev.anullihate.vpnguard.VPNGuard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.Map.Entry;

public class GenerateReport extends AsyncTask {
    private String REPORTS_FOLDER;
    private static final DateFormat SDF = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private String plugin_version;
    private CommandSender sender;
    private VPNGuard plugin;

    public GenerateReport(VPNGuard plugin, CommandSender sender) {
        this.plugin = plugin;
        this.sender = sender;
        this.REPORTS_FOLDER = plugin.getDataFolder() + File.separator + "reports" + File.separator;
        if (!(new File(this.REPORTS_FOLDER)).isDirectory()) {
            (new File(this.REPORTS_FOLDER)).mkdirs();
        }

        this.plugin_version = plugin.getServer().getPluginManager().getPlugin("VPNGuard").getDescription().getVersion();
    }

    public void onRun() {
        try {
            Date date = new Date();
            String filename = "report_" + SDF.format(date) + ".html";
            File f = new File(this.REPORTS_FOLDER + filename);
            this.sender.sendMessage(this.plugin.msg("Generating Report..." + filename));
            String table = this.generateUserTable();
            InputStream in = this.getClass().getResourceAsStream("/com/xioax/plugins/report/html/main.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "utf-8"));

            String line;
            while((line = reader.readLine()) != null) {
                line = line.replace("$FILENAME$", filename);
                line = line.replace("$VERSION$", this.plugin_version);
                line = line.replace("$TABLE$", table);
                writer.write(line);
            }

            reader.close();
            writer.close();
            HashMap<Object, Object> history = (HashMap)this.plugin.stream.data.get("history");
            history.clear();
            this.sender.sendMessage(this.plugin.msg("Report Generated: " + this.REPORTS_FOLDER + filename));
        } catch (Exception var10) {
            this.sender.sendMessage(this.plugin.msg("Unable to generate the report. Check Console for Errors."));
            var10.printStackTrace();
        }

    }

    private String generateUserTable() {
        StringBuilder out = new StringBuilder();
        out.append("<table class=\"table table-striped\"><thead><tr><th></th><th>Player</th><th>IP Addresses</th></tr></thead><tbody>");
        HashMap<Object, Object> history = (HashMap)this.plugin.stream.data.get("history");
        Iterator var3 = history.entrySet().iterator();

        while(var3.hasNext()) {
            Entry<Object, Object> entry = (Entry)var3.next();
            UUID uuid = (UUID)entry.getKey();
            IPlayer player = this.plugin.getServer().getOfflinePlayer(uuid);
            String playerUsername = player.getName();
            ArrayList<String> nested = (ArrayList)entry.getValue();
            StringBuilder ipAppender = new StringBuilder();

            String ip;
            String api;
            for(Iterator var10 = nested.iterator(); var10.hasNext(); ipAppender.append("<a target=\"blank\" href=\"" + api + "\">" + ip + "</a>&nbsp;")) {
                ip = (String)var10.next();
                if (this.plugin.apiKey == null) {
                    api = "http://api.vpnblocker.net/v2/html/" + ip;
                } else if (!this.plugin.api_package.equals("Basic") && !this.plugin.api_package.equals("Professional")) {
                    api = "http://api.vpnblocker.net/v2/html/" + ip + "/" + this.plugin.apiKey;
                } else {
                    api = "https://api.vpnblocker.net/v2/html/" + ip + "/" + this.plugin.apiKey;
                }
            }

            out.append("<tr><td><img src=\"https://minotar.net/helm/" + playerUsername + "/64.png\"></td>" + "<td>Username: " + playerUsername + "<br>UUID: " + uuid.toString() + "</td>" + "<td>" + ipAppender.toString() + "</td>" + "</tr>");
            System.out.println();
        }

        out.append("</tbody></table>");
        return out.toString();
    }
}
