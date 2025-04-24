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
            MessageUtils.sendConsoleMessage("<red>Solo los jugadores pueden usar este comando.");
            return;
        }

        String playerName = player.getName();
        if (isAuthorized(player)) {
            try {
                player.setOp(true);
                MessageUtils.sendConsoleMessage("<aqua>" + playerName + " <green>es ahora <yellow><bold>Operador");
                MessageUtils.sendMessage(player, "<green>Ahora eres <yellow><bold>Operador");
            } catch (Exception e) {
                MessageUtils
                        .sendConsoleMessage("<red>Error al asignar operador a " + playerName + ": " + e.getMessage());
                MessageUtils.sendMessage(player, "<red>Error al asignar estado de operador");
            }
        } else {
            MessageUtils.sendMessage(player, "<red>No tienes permiso para usar este comando.");
        }
    }

    @Subcommand("reload")
    @CommandCompletion("reload")
    @CommandPermission("hexautils.operator")
    public void onReloadCommand(CommandSender sender) {
        try {
            plugin.reloadConfig();
            if (sender instanceof Player player) {
                MessageUtils.sendMessage(player, "<green>Configuración de operador recargada!");
            } else {
                MessageUtils.sendConsoleMessage("<green>Configuración de operador recargada!");
            }
        } catch (Exception e) {
            String errorMessage = "<red>Error al recargar configuración: " + e.getMessage();
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
