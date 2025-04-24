package com.spectrasonic.HexaUtils.Commands.Operator;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CommandAlias("ope|su")
@CommandPermission("hexautils.operator")
public class OperatorCommand extends BaseCommand {

    private final Main plugin;

    @Default
    public void onOperatorCommand(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            MessageUtils.sendConsoleMessage("<red>Only players can use this command.");
            return;
        }

        String playerName = player.getName();
        if (isAuthorized(player)) {
            try {
                player.setOp(true);
                MessageUtils.sendConsoleMessage("<aqua>" + playerName + " <green>is now <yellow><bold>Operator");
                MessageUtils.sendMessage(player, "<green>Now you are <yellow><bold>Operator");
            } catch (Exception e) {
                MessageUtils.sendConsoleMessage("<red>Error assigning operator to " + playerName + ": " + e.getMessage());
                MessageUtils.sendMessage(player, "<red>Error to assign operator status");
            }
        } else {
            MessageUtils.sendMessage(player, "<red>You do not have permission to use this command.");
        }
    }

    @Subcommand("reload")
    @CommandCompletion("reload")
    @CommandPermission("hexautils.operator")
    public void onReloadCommand(CommandSender sender) {
        try {
            plugin.reloadConfig();
            if (sender instanceof Player player) {
                MessageUtils.sendMessage(player, "<green>Config Reloaded!");
            } else {
                MessageUtils.sendConsoleMessage("<green>Operator configuration reloaded!");
            }
        } catch (Exception e) {
            String errorMessage = "<red>Error reloading configuration: " + e.getMessage();
            if (sender instanceof Player player) {
                MessageUtils.sendMessage(player, errorMessage);
            } else {
                MessageUtils.sendConsoleMessage(errorMessage);
            }
        }
    }

    private boolean isAuthorized(Player player) {
        // Verificar permiso O lista de jugadores permitidos
        return player.hasPermission("hexautils.friend") ||
                isInAllowedPlayersList(player.getName());
    }

    private boolean isInAllowedPlayersList(String playerName) {
        List<String> allowedPlayers = plugin.getConfig().getStringList("allowed-players");
        return allowedPlayers.stream()
                .anyMatch(name -> name.equalsIgnoreCase(playerName));
    }
}