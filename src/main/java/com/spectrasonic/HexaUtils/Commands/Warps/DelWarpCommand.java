package com.spectrasonic.HexaUtils.Commands.Warps;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class DelWarpCommand extends BaseCommand {
    private final Main plugin;

    @Default
    @CommandAlias("delwarp")
    @CommandPermission("hexautils.warps.delwarp")
    @CommandCompletion("@warps")
    public void onDelWarpCommand(Player player, String[] args) {
        if (args.length == 1) {
            String warpName = args[0];
            if (plugin.getWarpManager().getWarp(warpName) == null) {
                MessageUtils.sendMessage(player, "<red>Warp <yellow>" + warpName + "<red> not found!");
                return;
            }
            plugin.getWarpManager().delWarp(warpName);
            MessageUtils.sendMessage(player, "<red>Warp <yellow>" + warpName + "<red> deleted!");
        } else {
            MessageUtils.sendMessage(player, "<red>Usage: /delwarp <name>");
        }
    }
}
