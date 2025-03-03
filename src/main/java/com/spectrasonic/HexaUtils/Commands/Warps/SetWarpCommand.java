package com.spectrasonic.HexaUtils.Commands.Warps;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
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
                if (plugin.getWarpManager().getWarp(warpName) != null) {
                    MiniMessageUtils.sendMessage(player, "<red>El warp <gold>" + warpName + "<red> ya existe!");
                return;
                }

                Location location = player.getLocation();
                plugin.getWarpManager().setWarp(warpName, location);
                MiniMessageUtils.sendMessage(player, "<green>Warp <gold>" + warpName + "<green> set!");
            } else {
                MiniMessageUtils.sendMessage(player, "<red>You must specify a name.");
            }
        }
    }
