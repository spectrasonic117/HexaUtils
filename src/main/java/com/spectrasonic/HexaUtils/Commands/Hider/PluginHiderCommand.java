package com.spectrasonic.HexaUtils.Commands.Hider;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@CommandAlias("pluginhider|hider|ph")
@CommandPermission("hexautils.pluginhider")
@RequiredArgsConstructor
public class PluginHiderCommand extends BaseCommand {

    private final Main plugin;

        @Subcommand("reload")
        @CommandCompletion("reload")
        public void onReload(CommandSender sender) {
            plugin.reloadConfigs();
        if (sender instanceof Player) {
            MiniMessageUtils.sendMessage((Player) sender, "<green>PluginHider configuration reloaded.");
        } else {
            sender.sendMessage("PluginHider configuration reloaded.");
        }
    }
}
