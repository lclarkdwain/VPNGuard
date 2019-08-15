package dev.anullihate.vpnguard;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.util.List;

public class ConfigReader {
    private final VPNGuard instance;
    private static Config config;
    private static Integer timeout = null;
    private static Integer apicachetime = null;
    private static Integer commandsdelay = null;
    private static Boolean logging = null;
    private static Boolean bypassCheck = null;
    private static Boolean bypassOps = null;
    private static Boolean apicache = null;
    private static Boolean reportlogging = null;
    private static List<String> commands = null;
    private static String prefix = null;
    private static String api = null;
    private static String bypassMessage = null;
    private static String countryDenyMessage = null;
    private static String subnetMessage = null;

    public ConfigReader(VPNGuard instance) {
        this.instance = instance;
        config = instance.getConfig();
    }

    public String getPrefix() {
        if (prefix != null) {
            return prefix;
        } else {
            prefix = TextFormat.colorize('&', config.getString("prefix"));
            return prefix;
        }
    }

    public String getAPIKey() {
        if (api != null) {
            return api;
        } else {
            api = config.getString("api-key");
            return api;
        }
    }

    public List<String> getCommands() {
        if (commands != null) {
            return commands;
        } else {
            commands = config.getStringList("commands");
            return commands;
        }
    }

    public int getCommandsDelay() {
        if (commandsdelay != null) {
            return commandsdelay;
        } else {
            commandsdelay = config.getInt("commands-delay");
            return commandsdelay;
        }
    }

    public boolean getAPICache() {
        if (apicache != null) {
            return apicache;
        } else {
            apicache = config.getBoolean("api-cache");
            return apicache;
        }
    }

    public int getAPICacheTime() {
        if (apicachetime != null) {
            return apicachetime;
        } else {
            apicachetime = config.getInt("api-cache-time");
            return apicachetime;
        }
    }

    public boolean getReportLogging() {
        if (reportlogging != null) {
            return reportlogging;
        } else {
            reportlogging = config.getBoolean("report-logging");
            return reportlogging;
        }
    }

    public String getCountryDenyMessage() {
        if (countryDenyMessage != null) {
            return countryDenyMessage;
        } else {
            countryDenyMessage = TextFormat.colorize('&', config.getString("country-deny-message"));
            return countryDenyMessage;
        }
    }

    public String getSubnetMessage() {
        if (subnetMessage != null) {
            return subnetMessage;
        } else {
            subnetMessage = TextFormat.colorize('&', config.getString("subnet-banned-message"));
            return subnetMessage;
        }
    }

    public int getTimeout() {
        if (timeout != null) {
            return timeout;
        } else {
            timeout = config.getInt("timeout");
            return timeout;
        }
    }

    public boolean getLogging() {
        if (logging != null) {
            return logging;
        } else {
            logging = config.getBoolean("logging");
            return logging;
        }
    }

    public boolean getBypassCheck() {
        if (bypassCheck != null) {
            return bypassCheck;
        } else {
            bypassCheck = config.getBoolean("bypass-check");
            return bypassCheck;
        }
    }

    public boolean getBypassOps() {
        if (bypassOps != null) {
            return bypassOps;
        } else {
            bypassOps = config.getBoolean("bypass-ops");
            return bypassOps;
        }
    }

    public String getBypassMessage() {
        if (bypassMessage != null) {
            return bypassMessage;
        } else {
            bypassMessage = TextFormat.colorize('&', config.getString("bypass-message"));
            return bypassMessage;
        }
    }

    public void onDisable() {
        prefix = null;
        config = null;
        commands = null;
        commandsdelay = null;
        countryDenyMessage = null;
        reportlogging = null;
        timeout = null;
        logging = null;
        bypassCheck = null;
        bypassOps = null;
        apicache = null;
        bypassMessage = null;
        apicachetime = null;
    }
}
