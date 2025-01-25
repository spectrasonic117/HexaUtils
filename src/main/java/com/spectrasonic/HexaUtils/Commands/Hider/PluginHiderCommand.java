package com.spectrasonic.HexaUtils.Commands.Hider;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PluginHiderCommand extends BaseCommand {

    private final Main plugin;

    public PluginHiderCommand(Main plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("pluginhider")
    @CommandPermission("pluginhider.reload")
    public class PluginHiderSubCommands {

        @Subcommand("reload")
        public void onReload(CommandSender sender) {
            plugin.reloadConfigs();
            MiniMessageUtils.sendMessage((Player) sender, "<green>PluginHider configuration reloaded.");
        }
    }
}
