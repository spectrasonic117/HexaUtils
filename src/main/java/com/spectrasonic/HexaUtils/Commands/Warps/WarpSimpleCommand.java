package com.spectrasonic.HexaUtils.Commands.Warps;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpSimpleCommand extends BaseCommand {

    private final Main plugin;

    public WarpSimpleCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Subcommand("hiderRreload")
    @CommandAlias("warpsimple")
    @CommandPermission("hexautils.command.warpsimple.hiderRreload")
    public void onReloadCommand(CommandSender sender) {
                plugin.reloadConfigs();
        MiniMessageUtils.sendMessage((Player) sender, "<green>Configurations reloaded!");
            }

    @Subcommand("help")
    @CommandAlias("warpsimple")
    public void onHelpCommand(CommandSender sender) {
        MiniMessageUtils.sendMessage((Player) sender, "<yellow>Usage: /warpsimple hiderRreload");
    }
}
