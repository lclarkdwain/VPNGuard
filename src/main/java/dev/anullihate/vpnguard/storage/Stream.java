package dev.anullihate.vpnguard.storage;

import dev.anullihate.vpnguard.VPNGuard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;

public class Stream {
    private VPNGuard plugin;
    private String dataFile;
    private HashMap<String, Object> map;

    public Stream(VPNGuard plugin, String dataFile) {
        this.plugin = plugin;
        this.dataFile = dataFile;
    }

    public void init() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.plugin.getLogger().info("Attempting to load: {0}");
        FileInputStream fis = new FileInputStream(this.dataFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.map = (HashMap)ois.readObject();
        ois.close();
        this.plugin.getLogger().info("{0} has been loaded!");
    }

    public void save() throws IOException {
        this.plugin.getLogger().info("Attempting to save: {0}");
        FileOutputStream fos = new FileOutputStream(this.dataFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.map);
        oos.close();
        this.plugin.getLogger().info("{0} has been saved!");
    }

    public VPNGuard getPlugin() {
        return this.plugin;
    }

    public String getDataFileName() {
        return (new File(this.dataFile)).getName();
    }

    public HashMap<String, Object> getMap() {
        return this.map;
    }

    public void setMap(HashMap map) {
        this.map = map;
    }
}
