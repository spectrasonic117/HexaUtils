package com.spectrasonic.HexaUtils.Commands.Warps;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import com.spectrasonic.HexaUtils.Utils.SoundUtils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@CommandAlias("warpsystem|ws")
@CommandPermission("hexautils.warps")
@RequiredArgsConstructor
public class WarpSystem extends BaseCommand {

    private final Main plugin;

    @Default
    public void onDefault(CommandSender sender) {
        if (sender instanceof Player player) {
            SoundUtils.playerSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }
        sendHelpMessage(sender);
    }

    @Subcommand("reload")
    @Description("Recarga la configuración del sistema de warps")
    public void onReloadCommand(CommandSender sender) {
        plugin.getConfigManager().reloadWarps();

        if (sender instanceof Player player) {
            MessageUtils.sendMessage(player, "<green>Configuración de warps recargada!");
        } else {
            sender.sendMessage("Configuración de warps recargada!");
        }
    }

    @Subcommand("help")
    @Description("Muestra ayuda sobre el sistema de warps")
    public void onHelpCommand(CommandSender sender) {
        sendHelpMessage(sender);
    }

    private void sendHelpMessage(CommandSender sender) {
        String[] helpMessages = {
                "<gray>=== <color:#EF3341><bold>Sistema de Warps <gold>Ayuda <gray>===",
                "",
                "<yellow>/warpsystem <gray>- Muestra este menú de ayuda",
                "<yellow>/warpsystem reload <gray>- Recarga la configuración",
                "<yellow>/warpsystem help <gray>- Muestra esta ayuda",
                "",
                "<yellow>/warp <nombre> <gray>- Teletransporta a un warp",
                "<yellow>/warp <nombre> <jugador> <gray>- Teletransporta a un jugador al warp",
                "<yellow>/warp <nombre> all <gray>- Teletransporta a todos al warp",
                "<yellow>/setwarp <nombre> <gray>- Crea un nuevo warp",
                "<yellow>/delwarp <nombre> <gray>- Elimina un warp existente",
                "",
                "<gray>Usa <yellow><click:suggest_command:'/warp '>/warp</click> <gray>para ver la lista de warps disponibles"
        };

        for (String message : helpMessages) {
            if (sender instanceof Player) {
                MessageUtils.sendMessage((Player) sender, message);
            } else {
                MessageUtils.sendConsoleMessage(message);
            }
        }
    }
}
