package com.spectrasonic.HexaUtils.Commands.Operator;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;

@CommandAlias("operator|ope|su")
@CommandPermission("hexautils.operator")
public class OperatorCommand extends BaseCommand {

    private final Main plugin;

    public OperatorCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Default
    public void onOperatorCommand(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            MiniMessageUtils.sendConsoleMessage("<red>Only players can use this command.");
            return;
        }
        String playerName = player.getName();
        if (isAuthorizedPlayer(playerName)) {
            try {
                player.setOp(true);
            } catch (Exception e) {
                MiniMessageUtils.sendConsoleMessage("<red>Failed to set operator status for " + playerName + ": " + e.getMessage());
                return;
            }
            MiniMessageUtils.sendConsoleMessage("<aqua>" + playerName + " <green>is now an <yellow><bold>Operator");
            MiniMessageUtils.sendMessage(player, "<green>You are now an <yellow><bold>Operator");
        } else {
            MiniMessageUtils.sendMessage(player, "<red>Not enough permissions to use this command.");
        }
    }

    @Subcommand("reload")
    @CommandCompletion("reload")
    public void onReloadCommand(CommandSender sender) {
        try {
            plugin.reloadConfig();
            if (sender instanceof Player player) {
                MiniMessageUtils.sendMessage(player, "<green>Operator configuration reloaded successfully!");
            } else {
                MiniMessageUtils.sendConsoleMessage("<green>Operator configuration reloaded successfully!");
            }
        } catch (Exception e) {
            String errorMessage = "<red>An error occurred while reloading the configuration: " + e.getMessage();
            if (sender instanceof Player player) {
                MiniMessageUtils.sendMessage(player, errorMessage);
            } else {
                MiniMessageUtils.sendConsoleMessage(errorMessage);
            }
        }
    }

    private boolean isAuthorizedPlayer(String playerName) {
        List<String> allowedPlayers = plugin.getConfig().getStringList("allowed-players");
        return allowedPlayers.stream()
                .anyMatch(player -> player.equalsIgnoreCase(playerName));
    }
}
