package com.spectrasonic.HexaUtils.Manager;

import com.spectrasonic.HexaUtils.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WarpManager {

    private final Main plugin;
    private final File configFile;
    private FileConfiguration config;

    private final Map<String, Location> warps;

    public WarpManager(Main plugin) {
        this.plugin = plugin;
        this.warps = new HashMap<>();
        this.configFile = new File(plugin.getDataFolder(), "warps.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(configFile);
        loadWarps();
    }

    public void setWarp(String name, Location location) {
        warps.put(name, location);
        config.set(name + ".world", location.getWorld().getName());
        config.set(name + ".x", location.getX());
        config.set(name + ".y", location.getY());
        config.set(name + ".z", location.getZ());
        config.set(name + ".yaw", location.getYaw());
        config.set(name + ".pitch", location.getPitch());
        saveConfig();
    }

    public Location getWarp(String name) {
        return warps.get(name);
    }

    public void delWarp(String name) {
        warps.remove(name);
        config.set(name, null);
        saveConfig();
    }

    public Set<String> getWarpNames() {
        return warps.keySet();
    }

    private void loadWarps() {
        for (String key : config.getKeys(false)) {
            String world = config.getString(key + ".world");
            double x = config.getDouble(key + ".x");
            double y = config.getDouble(key + ".y");
            double z = config.getDouble(key + ".z");
            float yaw = (float) config.getDouble(key + ".yaw");
            float pitch = (float) config.getDouble(key + ".pitch");
            assert world != null;
            Location location = new Location(plugin.getServer().getWorld(world), x, y, z, yaw, pitch);
            warps.put(key, location);
        }
    }

    private void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadWarpsConfig() {
        this.config = YamlConfiguration.loadConfiguration(configFile);
        warps.clear();
        loadWarps();
    }
}
