package com.spectrasonic.HexaUtils.Commands.GameModeSwitch;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class GameModeCommand extends BaseCommand {

    @Default
    @CommandAlias("gmc|gm0")
    @CommandPermission("hexautils.gamemode.creative")
    public void onCreative(Player player) {
                player.setGameMode(GameMode.CREATIVE);
                MiniMessageUtils.sendMessage(player, "<green>Changed to <yellow>Creative <green>mode!");
    }

    @CommandAlias("gms|gm1")
    @CommandPermission("hexautils.gamemode.survival")
    public void onSurvival(Player player) {
                player.setGameMode(GameMode.SURVIVAL);
                MiniMessageUtils.sendMessage(player, "<green>Changed to <yellow>Survival <green>mode!");
    }

    @CommandAlias("gma|gm2")
    @CommandPermission("hexautils.gamemode.adventure")
    public void onAdventure(Player player) {
                player.setGameMode(GameMode.ADVENTURE);
                MiniMessageUtils.sendMessage(player, "<green>Changed to <yellow>Adventure <green>mode!");
    }

    @CommandAlias("gmsp|gm3")
    @CommandPermission("hexautils.gamemode.spectator")
    public void onSpectator(Player player) {
                player.setGameMode(GameMode.SPECTATOR);
                MiniMessageUtils.sendMessage(player, "<green>Changed to <yellow>Spectator <green>mode!");
        }
    }
