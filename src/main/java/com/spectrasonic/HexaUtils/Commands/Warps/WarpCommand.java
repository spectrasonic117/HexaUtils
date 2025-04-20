package com.spectrasonic.HexaUtils.Commands.Warps;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import com.spectrasonic.HexaUtils.Utils.SoundUtils;
import com.spectrasonic.HexaUtils.Utils.TeleportEffectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class WarpCommand extends BaseCommand {

    private final Main plugin;
    // Define constants for effect parameters for better readability and maintenance
    private static final double TELEPORT_EFFECT_HEIGHT = 3.0;
    private static final int TELEPORT_EFFECT_DURATION = 20; // 60 ticks = 3 seconds

    @Default
    @CommandAlias("warp")
    @CommandPermission("hexautils.warpsystem.warp")
    @CommandCompletion("@warps @players")
    public void onWarpCommand(Player player, String[] args) {
        if (args.length == 0) {
            // Handle case where no warp name is provided
            MessageUtils.sendMessage(player, "<red>Usage: /warp <warpName> [playerName|all]");
            // Optionally, list available warps here
            // String availableWarps = String.join(", ",
            // plugin.getWarpManager().getWarpNames());
            // MessageUtils.sendMessage(player, "<gray>Available warps: <yellow>" +
            // availableWarps);
            return;
        }

        String warpName = args[0];
        Location location = plugin.getWarpManager().getWarp(warpName);

        if (location == null) {
            MessageUtils.sendMessage(player, "<red>Warp <yellow>" + warpName + "<red> not found!");
            return;
        }

        if (args.length == 1) {
            // Teleport the command sender
            teleportPlayerWithEffect(player, location, warpName);
        } else if (args.length == 2) {
            String targetArg = args[1];
            if ("all".equalsIgnoreCase(targetArg)) {
                // Teleport all online players
                if (!player.hasPermission("hexautils.warpsystem.warp.all")) {
                    MessageUtils.sendPermissionMessage(player);
                    return;
                }
                MessageUtils.sendMessage(player, "<green>Teleporting all players to <gold>" + warpName + "<green>...");
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    teleportPlayerWithEffect(onlinePlayer, location, warpName);
                }
            } else {
                // Teleport a specific player
                if (!player.hasPermission("hexautils.warpsystem.warp.other")) {
                    MessageUtils.sendPermissionMessage(player);
                    return;
                }
                Player target = Bukkit.getPlayerExact(targetArg);
                if (target != null) {
                    teleportPlayerWithEffect(target, location, warpName);
                    MessageUtils.sendMessage(player, "<green>Teleported <gold>" + target.getName() + "<green> to <gold>"
                            + warpName + "<green>.");
                } else {
                    MessageUtils.sendMessage(player, "<red>Player <yellow>" + targetArg + "<red> not found!");
                }
            }
        } else {
            // Handle incorrect usage if more than 2 arguments are provided
            MessageUtils.sendMessage(player, "<red>Usage: /warp <warpName> [playerName|all]");
        }
    }

    /**
     * Helper method to teleport a player and apply the visual effect.
     * 
     * @param targetPlayer The player to teleport.
     * @param destination  The location to teleport to.
     * @param warpName     The name of the warp for messaging.
     */
    private void teleportPlayerWithEffect(Player targetPlayer, Location destination, String warpName) {
        targetPlayer.teleport(destination);
        SoundUtils.playerSound(targetPlayer, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 0.5f);
        // --- Add particle effect call ---
        TeleportEffectUtils.createDNAHelix(plugin, destination, TELEPORT_EFFECT_HEIGHT, TELEPORT_EFFECT_DURATION);
        MessageUtils.sendMessage(targetPlayer, "<green>Teleported to <gold>" + warpName + "<green>.");
    }
}
