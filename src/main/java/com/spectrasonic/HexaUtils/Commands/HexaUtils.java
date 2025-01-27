package com.spectrasonic.HexaUtils.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import com.spectrasonic.HexaUtils.Utils.SoundUtils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("hexautils|hu")
public class HexaUtils extends BaseCommand {

    private final Main plugin;

    public HexaUtils(Main plugin) {
        this.plugin = plugin;
    }

    @Default
    @Subcommand("help")
    @Description("Shows the help menu")
    public void onHelp(CommandSender sender) {
        if (sender instanceof Player player) {
            SoundUtils.playerSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
        }
        sendHelpMessage(sender);
    }

    @Subcommand("version")
    @Description("Shows plugin version information")
    public void onVersion(CommandSender sender) {
        if (sender instanceof Player player) {
            SoundUtils.playerSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
        }
        
        MiniMessageUtils.sendSingleMessage(sender, "<gray>Version: <light_purple>%s".formatted(plugin.getDescription().getVersion()), null);
        MiniMessageUtils.sendSingleMessage(sender, "<gray>Developed by: <red>%s".formatted(plugin.getDescription().getAuthors()), null);
        MiniMessageUtils.sendSingleMessage(sender,"<gray>A Plugin for <color:#EF3341><bold>Hexa Creators</bold>", null);
    }

    private void sendHelpMessage(CommandSender sender) {
        String[] helpMessages = {
                "<gray>=== <green>HexaUtils <gold>Help <gray>===</gray>",
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
                "",
                "<gray>=== <red>Note <gray>===",
                "Use <green>/hexautils help</green> <gray>to see this menu."
        };

        for (String message : helpMessages) {
            MiniMessageUtils.sendSingleMessage(sender, message, null);
        }
    }
}