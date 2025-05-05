package com.spectrasonic.HexaUtils.Commands.SoundToAll;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@CommandAlias("soundtoall|sta")
@RequiredArgsConstructor
public class SoundToAllCommand extends BaseCommand {

    private final Main plugin;

    @Default
    @Subcommand("play")
    @CommandPermission("hexautils.soundtoall")
    @CommandCompletion("@sounds")
    @Description("Reproduce un sonido a todos los jugadores en el canal ambient")
    public void onSoundToAll(CommandSender sender, Sound sound, float volume, float pitch) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            player.playSound(player, sound, SoundCategory.AMBIENT, volume, pitch);
        }

        int playerCount = plugin.getServer().getOnlinePlayers().size();
        String jugadoresText = playerCount == 1 ? "jugador" : "jugadores";

        if (sender instanceof Player) {
            Player player = (Player) sender;
            MessageUtils.sendMessage(player,
                    "<green>Reproduciendo sonido a <yellow>" + playerCount + "</yellow> " + jugadoresText + ".");
        } else {
            sender.sendMessage("Reproduciendo sonido a " + playerCount + " " + jugadoresText + ".");
        }
    }
}