package com.spectrasonic.HexaUtils.Commands.Warps;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DelWarpCommand extends BaseCommand {
    private final Main plugin;


    @Default
    @CommandAlias("delwarp")
    @CommandPermission("hexautils.warpsystem.delwarp")
    @CommandCompletion("@warps")
    public void onDelWarpCommand(Player player, String[] args) {
            if (args.length == 1) {
                String warpName = args[0];
                plugin.getWarpManager().delWarp(warpName);
                MiniMessageUtils.sendMessage(player, "<red>Warp <yellow>" + warpName + " <red>deleted!");
            } else {
                MiniMessageUtils.sendMessage(player, "<red>Usage: <yellow>/delwarp <name>");
            }
        }
    }
