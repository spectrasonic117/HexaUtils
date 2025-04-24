package com.spectrasonic.HexaUtils.Utils;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class TeleportEffectUtils {

    private TeleportEffectUtils() {}

    public static void createDNAHelix(JavaPlugin plugin, Location destination, double height, int duration) {
        Location location = destination.clone();
        World world = location.getWorld();

        final Particle.DustOptions whiteDust = new Particle.DustOptions(Color.fromRGB(255, 255, 255), 1.0f);
        final Particle.DustOptions pinkDust = new Particle.DustOptions(Color.fromRGB(255, 48, 63), 1.0f);
        new BukkitRunnable() {
            double y = 0;
            final double maxY = height;
            final double yIncrement = maxY / duration;

            @Override
            public void run() {
                if (y >= maxY) {
                    this.cancel();
                    return;
                }
                createHelixStep(world, location, y, Particle.DUST, whiteDust, 0);
                createHelixStep(world, location, y, Particle.DUST, pinkDust, Math.PI / 2);
                createHelixStep(world, location, y, Particle.DUST, whiteDust, Math.PI);
                createHelixStep(world, location, y, Particle.DUST, pinkDust, 3 * Math.PI / 2);
                y += yIncrement;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private static void createHelixStep(World world, Location center, double y, Particle particle, Particle.DustOptions dustOptions, double offset) {
        double radius = 0.6;
        double rotationSpeed = 2.0;
        double angle = y * rotationSpeed + offset;
        double x = radius * Math.cos(angle);
        double z = radius * Math.sin(angle);

        int mainCount = 1;
        double spread = 0.01;

        world.spawnParticle(
            particle,
            center.getX() + x,
            center.getY() + y,
            center.getZ() + z,
            mainCount,
            spread, spread, spread,
            0.0,
            dustOptions
        );
        for (int i = 0; i < 3; i++) {
            double smallOffset = 0.01;
            world.spawnParticle(
                particle,
                center.getX() + x + (Math.random() * smallOffset - smallOffset / 2),
                center.getY() + y + (Math.random() * smallOffset - smallOffset / 2),
                center.getZ() + z + (Math.random() * smallOffset - smallOffset / 2),
                1,
                0, 0, 0,
                0,
                dustOptions
            );
        }
    }
}
