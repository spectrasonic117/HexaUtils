package com.spectrasonic.HexaUtils.Commands.Pvp;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import com.spectrasonic.HexaUtils.Utils.SoundUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

@CommandAlias("pvp")
@Description("Controla el estado PVP en todos los mundos")
public class PVPCommand extends BaseCommand {

    @Subcommand("on")
    @CommandPermission("hexautils.pvp.toggle")
    @Description("Activa el PVP en todos los mundos")
    @CommandCompletion("true")
    public void onEnable(CommandSender sender) {
        setPVPForAllWorlds(true);
        MessageUtils.sendBroadcastMessage(
                "<red><bold>⚔️ PVP ACTIVADO</bold></red> <gray>- El combate entre jugadores está ahora <red><bold>HABILITADO</bold></red> en todos los mundos!");
        SoundUtils.broadcastPlayerSound(Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1.0f, 1.0f);
        MessageUtils.sendMessage(sender,
                "PVP <red><bold>ACTIVADO</bold></red> exitosamente en todos los mundos.");
    }

    @Subcommand("off")
    @CommandPermission("hexautils.pvp.toggle")
    @Description("Desactiva el PVP en todos los mundos")
    @CommandCompletion("false")
    public void onDisable(CommandSender sender) {
        setPVPForAllWorlds(false);
        MessageUtils.sendBroadcastMessage(
                "<green><bold>🕊️ PVP DESACTIVADO</bold></green> <gray>- El combate entre jugadores está ahora <green><bold>DESHABILITADO</bold></green> en todos los mundos!");
        SoundUtils.broadcastPlayerSound(Sound.BLOCK_BEACON_DEACTIVATE, 1.0f, 1.0f);
        MessageUtils.sendMessage(sender,
                "PVP <green><bold>DESACTIVADO</bold></green> exitosamente en todos los mundos.");
    }

    @Default
    @HelpCommand
    @Syntax("[on|off] - Activa o desactiva el PVP")
    public void onHelp(CommandSender sender) {
        MessageUtils.sendMessage(sender, "Uso: <gray>/pvp <on|off></gray>");
    }

    private void setPVPForAllWorlds(boolean pvpEnabled) {
        for (World world : Bukkit.getWorlds()) {
            world.setPVP(pvpEnabled);
        }
    }
}
