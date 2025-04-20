package com.spectrasonic.HexaUtils.Commands.Warps;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.spectrasonic.HexaUtils.Main;
// --- Use MessageUtils ---
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class SetWarpCommand extends BaseCommand {
    private final Main plugin;

    @Default
    @CommandAlias("setwarp")
    @CommandPermission("hexautils.warpsystem.setwarp")
    @CommandCompletion("@warps")
    public void onSetWarpCommand(Player player, String[] args) {
        if (args.length == 1) {
            String warpName = args[0];
            if (!warpName.matches("^[a-zA-Z0-9_]+$")) {
                MessageUtils.sendMessage(player, "<red>Invalid warp name. Use only letters, numbers, and underscores.");
                return;
            }
            if (plugin.getWarpManager().getWarp(warpName) != null) {
                MessageUtils.sendMessage(player, "<red>The warp <gold>" + warpName + "<red> already exists!");
                return;
            }

            Location location = player.getLocation();
            plugin.getWarpManager().setWarp(warpName, location);
            MessageUtils.sendMessage(player, "<green>Warp <gold>" + warpName + "<green> set!");
        } else {
            MessageUtils.sendMessage(player, "<red>Usage: /setwarp <name>");
        }
    }
}
