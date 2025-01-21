package com.spectrasonic.HexaUtils.Commands.Warps;

import com.spectrasonic.HexaUtils.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WarpTabCompleter implements TabCompleter {

    private final Main plugin;

    public WarpTabCompleter(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("warp")) {
            if (args.length == 1) {
                return new ArrayList<>(plugin.getWarpManager().getWarpNames());
            } else if (args.length == 2) {
                List<String> playerNames = plugin.getServer().getOnlinePlayers().stream()
                        .map(Player::getName)
                        .collect(Collectors.toList());
                playerNames.add("all");
                return playerNames;
            }
        } else if (command.getName().equalsIgnoreCase("setwarp") || command.getName().equalsIgnoreCase("delwarp")) {
            if (args.length == 1) {
                return new ArrayList<>(plugin.getWarpManager().getWarpNames());
            }
        }
        return Collections.emptyList();
    }
}
