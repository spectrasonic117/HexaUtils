package com.spectrasonic.HexaUtils.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import com.spectrasonic.HexaUtils.Utils.SoundUtils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;


@CommandAlias("hexautils|hu")
@RequiredArgsConstructor
public class HexaUtils extends BaseCommand {

    private final Main plugin;

    @Default
    @Subcommand("help")
    @CommandPermission("hexautils.hexautils")
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
        
        MiniMessageUtils.sendMessage((Player)sender, "<gray>Version: <light_purple>%s".formatted(plugin.getDescription().getVersion()));
        MiniMessageUtils.sendMessage((Player)sender, "<gray>Developed by: <red>%s".formatted(plugin.getDescription().getAuthors()));
        MiniMessageUtils.sendMessage((Player)sender,"<gray>A Plugin for <color:#EF3341><bold>Hexa Creators</bold>");
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
                "",
                "<gray>=== <red>Note <gray>===",
                "Use <green>/hexautils help</green> <gray>to see this menu."
        };

        for (String message : helpMessages) {
            MiniMessageUtils.sendMessage((Player)sender, message);
        }
    }
}