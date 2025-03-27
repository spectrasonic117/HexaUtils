package com.spectrasonic.HexaUtils.Manager;

import com.spectrasonic.HexaUtils.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.World;
import lombok.RequiredArgsConstructor;
import lombok.Getter;

@RequiredArgsConstructor
public class FirstSpawnManager {

    @Getter
    private final Main plugin;
    private Location firstSpawn;
    private boolean enabled;
    private boolean debug;
    private String welcomeMessage;

    public void load() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        // Cargar valores básicos
        enabled = config.getBoolean("FirstSpawn.enabled", true);
        debug = config.getBoolean("FirstSpawn.debug", false);
        welcomeMessage = config.getString("FirstSpawn.welcome-message",
                "<gradient:gold:yellow>¡Bienvenido a nuestro servidor!</gradient> <gray>Esperamos que disfrutes tu estancia.");

        // Cargar ubicación desde la estructura correcta
        if (config.contains("FirstSpawn.FirstSpawn_location")) {
            String worldName = config.getString("FirstSpawn.FirstSpawn_location.world");
            World world = plugin.getServer().getWorld(worldName);
            if (world != null) {
                double x = config.getDouble("FirstSpawn.FirstSpawn_location.x");
                double y = config.getDouble("FirstSpawn.FirstSpawn_location.y");
                double z = config.getDouble("FirstSpawn.FirstSpawn_location.z");
                float yaw = getYawFromDirection(config.getString("FirstSpawn.FirstSpawn_location.direction", "SOUTH"));

                firstSpawn = new Location(world, x, y, z, yaw, 0);
            }
        }
    }

    public void save() {
        FileConfiguration config = plugin.getConfig();

        // Guardar valores básicos en la estructura correcta
        config.set("FirstSpawn.enabled", enabled);
        config.set("FirstSpawn.debug", debug);
        config.set("FirstSpawn.welcome-message", welcomeMessage);

        // Guardar ubicación en la estructura correcta
        if (firstSpawn != null && firstSpawn.getWorld() != null) {
            config.set("FirstSpawn.FirstSpawn_location.world", firstSpawn.getWorld().getName());
            config.set("FirstSpawn.FirstSpawn_location.x", firstSpawn.getX());
            config.set("FirstSpawn.FirstSpawn_location.y", firstSpawn.getY());
            config.set("FirstSpawn.FirstSpawn_location.z", firstSpawn.getZ());
            config.set("FirstSpawn.FirstSpawn_location.direction", getDirectionFromYaw(firstSpawn.getYaw()));
        }

        plugin.saveConfig();
    }

    private float getYawFromDirection(String direction) {
        return switch (direction.toUpperCase()) {
            case "SOUTH" -> 0;
            case "WEST" -> 90;
            case "NORTH" -> 180;
            case "EAST" -> 270;
            default -> 0;
        };
    }

    private String getDirectionFromYaw(float yaw) {
        if (yaw < 45)
            return "SOUTH";
        if (yaw < 135)
            return "WEST";
        if (yaw < 225)
            return "NORTH";
        if (yaw < 315)
            return "EAST";
        return "SOUTH";
    }

    // Getters y setters
    public Location getFirstSpawn() {
        return firstSpawn;
    }

    public void setFirstSpawn(Location loc) {
        this.firstSpawn = loc;
        save(); // Guardar automáticamente al establecer nueva ubicación
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggleEnabled() {
        this.enabled = !enabled;
        save();
    }

    public boolean isDebug() {
        return debug;
    }

    public void toggleDebug() {
        this.debug = !debug;
        save();
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String message) {
        this.welcomeMessage = message;
        save();
    }
}
