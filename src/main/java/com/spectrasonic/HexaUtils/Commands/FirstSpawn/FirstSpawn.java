package com.spectrasonic.HexaUtils.Commands.FirstSpawn;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class FirstSpawn extends BaseCommand implements Listener {
    
    private final JavaPlugin plugin;
    private Location firstSpawnLocation;
    private boolean enabled;
    private boolean debug;
    private String welcomeMessage;
    
    private void loadConfig() {
        FileConfiguration config = plugin.getConfig();
        enabled = config.getBoolean("FirstSpawn.enabled", true);
        debug = config.getBoolean("FirstSpawn.debug", false);
        welcomeMessage = config.getString("FirstSpawn.welcome-message", "");

        // Load spawn location from config
        if (config.contains("FirstSpawn.firstSpawn")) {
            try {
                firstSpawnLocation = new Location(
                    plugin.getServer().getWorld(config.getString("FirstSpawn.firstSpawn.world")),
                    config.getDouble("FirstSpawn.firstSpawn.x"),
                    config.getDouble("FirstSpawn.firstSpawn.y"),
                    config.getDouble("FirstSpawn.firstSpawn.z")
                );
                
                // Set direction if specified
                String direction = config.getString("FirstSpawn.firstSpawn.direction", "");
                if (!direction.isEmpty()) {
                    try {
                        switch (direction.toUpperCase()) {
                            case "NORTH":
                                firstSpawnLocation.setYaw(180f);
                                break;
                            case "EAST":
                                firstSpawnLocation.setYaw(270f);
                                break;
                            case "SOUTH":
                                firstSpawnLocation.setYaw(0f);
                                break;
                            case "WEST":
                                firstSpawnLocation.setYaw(90f);
                                break;
                            default:
                                logDebug("Invalid direction in config: " + direction);
                                break;
                        }
                    } catch (Exception e) {
                        logDebug("Error setting direction: " + e.getMessage());
                    }
                }
                
                logDebug("Loaded spawn location: " + formatLocationRaw(firstSpawnLocation));
            } catch (Exception e) {
                plugin.getLogger().warning("Error loading spawn location: " + e.getMessage());
                firstSpawnLocation = null;
            }
        }
    }

    private void logDebug(String message) {
        if (debug) {
            plugin.getLogger().info("[DEBUG] " + message);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!enabled) return;
        
        Player player = event.getPlayer();
        
        // Check if player has joined before
        if (!player.hasPlayedBefore() && firstSpawnLocation != null) {
            // Teleport player to first spawn location
            player.teleport(firstSpawnLocation);
            // Set their spawn point
            player.setBedSpawnLocation(firstSpawnLocation, true);
            
            // Send welcome message if configured
            if (!welcomeMessage.isEmpty()) {
                MiniMessageUtils.sendMessage(player, welcomeMessage);
            }
            
            logDebug("Teleported new player " + player.getName() + " to first spawn location");
        }
    }

    private String formatLocation(Location loc) {
        if (loc == null) return "<red>Not set</red>";
        return String.format("<green>%s</green>: <yellow>%.1f, %.1f, %.1f</yellow>", 
            loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ());
    }
    
    private String formatLocationRaw(Location loc) {
        if (loc == null) return "Not set";
        return String.format("%s: %.1f, %.1f, %.1f", 
            loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ());
    }

    @CommandAlias("firstspawn")
    @Description("FirstSpawn main command")
    @CommandPermission("hexautils.firstspawn")
    public void onFirstSpawn(Player player) {
        MiniMessageUtils.sendMessage(player, "<gold>FirstSpawn Commands:</gold>");
        MiniMessageUtils.sendMessage(player, "<yellow>/firstspawn set</yellow> <white>- Set spawn location</white>");
        MiniMessageUtils.sendMessage(player, "<yellow>/firstspawn status</yellow> <white>- Show current settings</white>");
        MiniMessageUtils.sendMessage(player, "<yellow>/firstspawn test</yellow> <white>- Test teleport to spawn</white>");
        MiniMessageUtils.sendMessage(player, "<yellow>/firstspawn toggle</yellow> <white>- Enable/disable plugin</white>");
        MiniMessageUtils.sendMessage(player, "<yellow>/firstspawn reload</yellow> <white>- Reload configuration</white>");
        MiniMessageUtils.sendMessage(player, "<yellow>/firstspawn debug</yellow> <white>- Toggle debug mode</white>");
    }

    @Subcommand("set")
    @Description("Set the first spawn location")
    @CommandPermission("hexautils.firstspawn.set")
    public void onSet(Player player) {
        Location location = player.getLocation();
        firstSpawnLocation = location;

        // Save to config
        FileConfiguration config = plugin.getConfig();
        config.set("FirstSpawn.firstSpawn.world", location.getWorld().getName());
        config.set("FirstSpawn.firstSpawn.x", location.getX());
        config.set("FirstSpawn.firstSpawn.y", location.getY());
        config.set("FirstSpawn.firstSpawn.z", location.getZ());
        
        // Save direction based on player's yaw
        float yaw = location.getYaw();
        String direction = "";
        if (yaw >= 135 && yaw < 225) {
            direction = "NORTH";
        } else if (yaw >= 225 && yaw < 315) {
            direction = "EAST";
        } else if ((yaw >= 315 && yaw <= 360) || (yaw >= 0 && yaw < 45)) {
            direction = "SOUTH";
        } else if (yaw >= 45 && yaw < 135) {
            direction = "WEST";
        }
        config.set("FirstSpawn.firstSpawn.direction", direction);
        
        plugin.saveConfig();
        logDebug("Set spawn location to: " + formatLocationRaw(location) + " facing " + direction);

        MiniMessageUtils.sendMessage(player, "<green>First spawn location has been set to your current location!</green>");
        MiniMessageUtils.sendMessage(player, "<yellow>Location: " + formatLocation(location) + 
                          (direction.isEmpty() ? "" : " <yellow>facing " + direction));
    }

    @Subcommand("status")
    @Description("Show current FirstSpawn settings")
    @CommandPermission("hexautils.firstspawn.status")
    public void onStatus(Player player) {
        MiniMessageUtils.sendMessage(player, "<gold>FirstSpawn Status:</gold>");
        MiniMessageUtils.sendMessage(player, "<yellow>Plugin enabled: " + 
            (enabled ? "<green>Yes</green>" : "<red>No</red>"));
        MiniMessageUtils.sendMessage(player, "<yellow>Debug mode: " + 
            (debug ? "<green>Enabled</green>" : "<red>Disabled</red>"));
        MiniMessageUtils.sendMessage(player, "<yellow>Current spawn location: " + 
            formatLocation(firstSpawnLocation));
        MiniMessageUtils.sendMessage(player, "<yellow>Welcome message: " + 
            (welcomeMessage.isEmpty() ? "<red>None</red>" : 
             "<green>" + welcomeMessage + "</green>"));
    }

    @Subcommand("test")
    @Description("Test teleport to the first spawn location")
    @CommandPermission("hexautils.firstspawn.test")
    public void onTest(Player player) {
        if (firstSpawnLocation == null) {
            MiniMessageUtils.sendMessage(player, "<red>Spawn location is not set!</red>");
            return;
        }
        
        player.teleport(firstSpawnLocation);
        MiniMessageUtils.sendMessage(player, "<green>Teleported to first spawn location!</green>");
        
        // Show welcome message in test mode too
        if (!welcomeMessage.isEmpty()) {
            MiniMessageUtils.sendMessage(player, welcomeMessage);
            MiniMessageUtils.sendMessage(player, "<yellow>Sent welcome message: <green>" + welcomeMessage + "</green>");
        }
    }

    @Subcommand("toggle")
    @Description("Toggle FirstSpawn functionality on/off")
    @CommandPermission("hexautils.firstspawn.toggle")
    public void onToggle(Player player) {
        enabled = !enabled;
        FileConfiguration config = plugin.getConfig();
        config.set("FirstSpawn.enabled", enabled);
        plugin.saveConfig();
        MiniMessageUtils.sendMessage(player, "<yellow>FirstSpawn is now " + 
            (enabled ? "<green>enabled</green>" : "<red>disabled</red>"));
    }
    
    @Subcommand("debug")
    @Description("Toggle debug mode")
    @CommandPermission("hexautils.firstspawn.debug")
    public void onDebug(Player player) {
        debug = !debug;
        FileConfiguration config = plugin.getConfig();
        config.set("FirstSpawn.debug", debug);
        plugin.saveConfig();
        MiniMessageUtils.sendMessage(player, "<yellow>Debug mode is now " + 
            (debug ? "<green>enabled</green>" : "<red>disabled</red>"));
    }

    @Subcommand("reload")
    @Description("Reload FirstSpawn configuration")
    @CommandPermission("hexautils.firstspawn.reload")
    public void onReload(Player player) {
        plugin.reloadConfig();
        loadConfig();
        MiniMessageUtils.sendMessage(player, "<green>Configuration reloaded!</green>");
    }
}