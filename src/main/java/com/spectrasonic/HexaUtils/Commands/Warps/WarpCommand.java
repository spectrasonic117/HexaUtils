package com.spectrasonic.HexaUtils.Commands.Warps;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import com.spectrasonic.HexaUtils.Utils.SoundUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarpCommand extends BaseCommand {
    private final Main plugin;

    @Default
    @CommandAlias("warp")
    @CommandPermission("hexautils.warpsystem.warp")
    @CommandCompletion("@warps @players")
    public void onWarpCommand(Player player, String[] args) {
        if (args.length == 1) {
                String warpName = args[0];
                Location location = plugin.getWarpManager().getWarp(warpName);
                if (location != null) {
                    player.teleport(location);
                    SoundUtils.playerSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 0.5f);
                    MiniMessageUtils.sendMessage(player, "<green>Teleported to <gold>" + warpName + "<green>.");
                } else {
                    MiniMessageUtils.sendMessage(player, "<red>Warp not found!");
                }
        } else if (args.length == 2) {
            String warpName = args[0];
            if ("all".equalsIgnoreCase(args[1])) {
                Location location = plugin.getWarpManager().getWarp(warpName);
                if (location != null) {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.teleport(location);
                        MiniMessageUtils.sendMessage(onlinePlayer, "<green>Teleported to <gold>" + warpName + "<green>.");
                    }
                } else {
                    MiniMessageUtils.sendMessage(player, "<red>Warp not found!");
                }
            } else {
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target != null) {
                    Location location = plugin.getWarpManager().getWarp(warpName);
                    if (location != null) {
                        target.teleport(location);
                        MiniMessageUtils.sendMessage(player, "<green>Teleported <gold>" + args[1] + "<green> to <gold>" + warpName + "<green>.");
                        MiniMessageUtils.sendMessage(target, "<green>Teleported to <gold>" + warpName + "<green>.");
                    } else {
                        MiniMessageUtils.sendMessage(player, "<red>Warp not found!");
                    }
                } else {
                    MiniMessageUtils.sendMessage(player, "<red>Player not found!");
                }
            }
        }
    }
}
