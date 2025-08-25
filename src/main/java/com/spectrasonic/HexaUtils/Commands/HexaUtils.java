package com.spectrasonic.HexaUtils.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import com.spectrasonic.HexaUtils.Utils.SoundUtils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CommandAlias("hexautils|hu")
public class HexaUtils extends BaseCommand {

    private final Main plugin;

    @Default
    @Subcommand("help")
    @CommandPermission("hexautils.hexautils")
    @Description("Shows the help menu")
    public void onHelp(CommandSender sender) {
        if (sender instanceof Player player) {
            SoundUtils.playerSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
        }
        sendHelpMessage(sender);
    }

    @Subcommand("version")
    @Description("Shows plugin version information")
    @CommandPermission("hexautils.hexautils")
    public void onVersion(CommandSender sender) {
        if (sender instanceof Player player) {
            SoundUtils.playerSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
        }

        MessageUtils.sendMessage((Player) sender,
                "<gray>Version: <light_purple>%s".formatted(plugin.getPluginMeta().getVersion()));
        MessageUtils.sendMessage((Player) sender,
                "<gray>Developed by: <red>%s".formatted(plugin.getPluginMeta().getAuthors()));
        MessageUtils.sendMessage((Player) sender, "<gray>A Plugin for <color:#EF3341><bold>Hexa Creators</bold>");
    }

    @Subcommand("reload")
    @Description("Reload HexaUtils configuration")
    @CommandPermission("hexautils.hexautils")
    public void onReload(CommandSender sender) {
        plugin.reloadConfigs();

        if (sender instanceof Player) {
            MessageUtils.sendMessage((Player) sender, "<green>HexaUtils configuration reloaded successfully!");
            SoundUtils.playerSound((Player) sender, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
        } else {
            sender.sendMessage("HexaUtils configuration reloaded successfully!");
        }
    }

    private void sendHelpMessage(CommandSender sender) {
        String[] helpMessages = {
                "<gray>=== <color:#EF3341><bold>HexaUtils <gold>Help <gray>===</gray>",
                "<gray>=== <gold>Available Commands <gray>===",
                "",
                "<green>/hexautils</green> - Main command",
                "<green>/warp</green> - Teleport to a warp",
                "<green>/setwarp</green> - Create a new warp",
                "<green>/delwarp</green> - Delete a warp",
                "<green>/gm0</green> or <green>/gmc</green> - Creative mode",
                "<green>/gm1</green> or <green>/gms</green> - Survival mode",
                "<green>/gm2</green> or <green>/gma</green> - Adventure mode",
                "<green>/gm3</green> or <green>/gmsp</green> - Spectator mode",
                "<green>/pluginhider</green> - Manage plugin visibility",
                "<green>/nv</green> - Toggle night vision effect",
                "<green>/fs</green> - Manage first spawn location",
                "<green>/sta</green> <gray> play <sound <vol> <pitch></gray> - Play global sound",
                "<green>/pointstick</green> or <green>/ps</green> - Get a block pointing stick",
                "<green>/flyspeed</green> or <green>/fspeed</green> - Modify fly speed",
                "<green>/movespeed</green> or <green>/mspeed</green> - Modify move speed",
                "<green>/invsee</green> or <green>/is</green> - See another player's inventory",
                "<green>/pvp</green> <gray>on|off</gray> - Toggle PVP",
                "",
                "<gray>=== <red>Note <gray>===",
                "Use <green>/hexautils help</green> <gray>to see this menu.",
                "<dark_gray>Developed by</dark_gray> <#EF3341>Spectrasonic</#EF3341>"
        };

        for (String message : helpMessages) {
            MessageUtils.sendMessage((Player) sender, message);
        }
    }
}