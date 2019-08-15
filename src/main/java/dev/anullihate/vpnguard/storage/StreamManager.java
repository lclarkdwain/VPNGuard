package dev.anullihate.vpnguard.storage;

import dev.anullihate.vpnguard.VPNGuard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class StreamManager extends Stream {
    public HashMap<String, Object> data;

    public StreamManager(VPNGuard plugin, String dataFile) {
        super(plugin, dataFile);
    }

    public void initDataStream() {
        VPNGuard plugin = super.getPlugin();

        try {
            super.init();
            this.data = super.getMap();
            this.populateDefaultData();
        } catch (ClassNotFoundException | IOException var3) {
            plugin.getLogger().info("Creating new data file: {0}");
            this.data = new HashMap<>();
            this.populateDefaultData();
            this.saveDataStream();
        }

    }

    public void saveDataStream() {
        VPNGuard plugin = super.getPlugin();
        super.setMap(this.data);

        try {
            super.save();
        } catch (IOException var3) {
            plugin.getLogger().critical("Unable to save: {0}");
            var3.printStackTrace();
        }

    }

    private void populateDefaultData() {
        ArrayList allowed_orgs;
        if (!this.data.containsKey("countries")) {
            allowed_orgs = new ArrayList();
            this.data.put("countries", allowed_orgs);
        }

        if (!this.data.containsKey("country_toggle")) {
            this.data.put("country_toggle", true);
        }

        if (!this.data.containsKey("subnet_toggle")) {
            this.data.put("subnet_toggle", true);
        }

        if (!this.data.containsKey("subnets")) {
            allowed_orgs = new ArrayList();
            this.data.put("subnets", allowed_orgs);
        }

        if (!this.data.containsKey("notify")) {
            allowed_orgs = new ArrayList();
            this.data.put("notify", allowed_orgs);
        }

        if (!this.data.containsKey("allowed_orgs")) {
            allowed_orgs = new ArrayList();
            this.data.put("allowed_orgs", allowed_orgs);
        }

        if (!this.data.containsKey("history")) {
            HashMap history = new HashMap();
            this.data.put("history", history);
        }

    }
}
