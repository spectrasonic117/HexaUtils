package com.spectrasonic.HexaUtils.Commands.FirstSpawn;

import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Manager.FirstSpawnManager;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("firstspawn|fs")
@CommandPermission("firstspawn.admin")
@RequiredArgsConstructor
public class FirstSpawn extends BaseCommand {

    private final FirstSpawnManager config;
    private final Main plugin;

    @Subcommand("set")
    @Description("Establece la ubicación del spawn")
    public void onSet(Player player) {
        config.setFirstSpawn(player.getLocation());
        MessageUtils.sendMessage(player, "<green>Spawn establecido en tu ubicación actual!");
    }

    @Subcommand("toggle")
    @Description("Activa/desactiva el plugin")
    public void onToggle(CommandSender sender) {
        config.toggleEnabled();
        MessageUtils.sendMessage(sender, "<yellow>Estado: " +
                (config.isEnabled() ? "<green>Activado" : "<red>Desactivado"), "", "");
        config.save();
    }

    @Subcommand("reload")
    @Description("Recarga la configuración")
    public void onReload(CommandSender sender) {
        plugin.getConfigManager().reloadFirstSpawn();

        if (sender instanceof Player) {
            MessageUtils.sendMessage((Player) sender, "<green>Configuración recargada!");
        } else {
            sender.sendMessage("Configuración recargada!");
        }
    }

    @Subcommand("debug")
    @Description("Modo debug")
    public void onDebug(CommandSender sender) {
        config.toggleDebug();
        MessageUtils.sendMessage(sender, "<yellow>Debug: " +
                (config.isDebug() ? "<green>Activado" : "<red>Desactivado"), "", "");
        config.save();
    }

    @Subcommand("test")
    @Description("Teletransporta al spawn")
    public void onTest(Player player) {
        if (config.getFirstSpawn() == null) {
            MessageUtils.sendMessage(player, "<red>Spawn no establecido!");
            return;
        }
        player.teleport(config.getFirstSpawn());
        MessageUtils.sendMessage(player, "<green>Teletransportado!");
    }

    @Subcommand("status")
    @Description("Muestra el estado actual")
    public void onStatus(CommandSender sender) {
        Location spawn = config.getFirstSpawn();
        String loc = spawn != null ? String.format("Mundo: %s, X: %.1f, Y: %.1f, Z: %.1f",
                spawn.getWorld().getName(), spawn.getX(), spawn.getY(), spawn.getZ()) : "<red>No establecido";

        MessageUtils.sendMessage(sender, "<gold>Estado de FirstSpawn:", "", "");
        MessageUtils.sendMessage(sender, "<yellow>• Activado: " +
                (config.isEnabled() ? "<green>Sí" : "<red>No"), "", "");
        MessageUtils.sendMessage(sender, "<yellow>• Ubicación: " + loc, "", "");
        MessageUtils.sendMessage(sender, "<yellow>• Debug: " +
                (config.isDebug() ? "<green>Activado" : "<red>Desactivado"), "", "");
    }

    @Subcommand("setmessage|setmsg")
    @Description("Establece el mensaje de bienvenida")
    public void onSetMessage(CommandSender sender, String message) {
        config.setWelcomeMessage(message);
        MessageUtils.sendMessage((Player) sender, "<green>Mensaje de bienvenida actualizado!");
    }
}