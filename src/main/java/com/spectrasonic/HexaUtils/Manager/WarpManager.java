package com.spectrasonic.HexaUtils.Manager;

import com.spectrasonic.HexaUtils.Main;
import org.bukkit.Location;
import org.bukkit.World;
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
        World world = location.getWorld();
        if (world == null) {
            return;
        }

        warps.put(name, location);
        config.set(name + ".world", world.getName());
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

    public void loadWarps() {
        for (String key : config.getKeys(false)) {
            String worldName = config.getString(key + ".world"); 
            double x = config.getDouble(key + ".x");
            double y = config.getDouble(key + ".y");
            double z = config.getDouble(key + ".z");
            float yaw = (float) config.getDouble(key + ".yaw");
            float pitch = (float) config.getDouble(key + ".pitch");
            
            World world = plugin.getServer().getWorld(worldName);
            if (world == null) {
                plugin.getLogger().warning("World " + worldName + " not found for warp " + key);
                continue;
            }
            
            Location location = new Location(world, x, y, z, yaw, pitch);
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
