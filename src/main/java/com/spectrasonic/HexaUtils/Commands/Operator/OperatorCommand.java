package com.spectrasonic.HexaUtils.Commands.Operator;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;

public class OperatorCommand extends BaseCommand {

    private final Main plugin;

    public OperatorCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandAlias("operator|ope|su")
    public void onOperatorCommand(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            MiniMessageUtils.sendMessage((Player) sender, "<red>Only players can use this command.");
            return;
        }

        String playerName = player.getName();
        if (isAuthorizedPlayer(playerName)) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + playerName);
            MiniMessageUtils.sendConsoleMessage("<aqua>" + playerName + " <green>is now an <yellow><bold>Operator");
            MiniMessageUtils.sendMessage(player, "<green>You are now an <yellow><bold>Operator");
        } else {
            MiniMessageUtils.sendMessage((Player) sender, "<red>Not enough permissions to use this command.");
        }
    }

    @Subcommand("reload")
    @CommandCompletion("reload")
    @CommandPermission("hexautils.command.operator")
    public void onReloadCommand(CommandSender sender) {
        plugin.reloadConfig();
        MiniMessageUtils.sendMessage((Player) sender, "<green>Config Reloaded!");
    }

    private boolean isAuthorizedPlayer(String playerName) {
        List<String> allowedPlayers = plugin.getConfig().getStringList("allowed-players");
        return allowedPlayers.stream()
                .anyMatch(player -> player.equalsIgnoreCase(playerName));
    }
}
