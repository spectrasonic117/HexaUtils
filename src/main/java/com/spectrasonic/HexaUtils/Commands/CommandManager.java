package com.spectrasonic.HexaUtils.Commands;

import co.aikar.commands.PaperCommandManager;
import com.spectrasonic.HexaUtils.Commands.FirstSpawn.FirstSpawn;
import com.spectrasonic.HexaUtils.Commands.GameModeSwitch.GameModeCommand;
import com.spectrasonic.HexaUtils.Commands.Hider.PluginHiderCommand;
import com.spectrasonic.HexaUtils.Commands.ItemDrop.ItemDropSwitcher;
import com.spectrasonic.HexaUtils.Commands.NightVision.NightVisionCommand;
import com.spectrasonic.HexaUtils.Commands.Operator.OperatorCommand;
import com.spectrasonic.HexaUtils.Commands.PointStick.PointStick;
import com.spectrasonic.HexaUtils.Commands.SoundToAll.SoundToAllCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.DelWarpCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.SetWarpCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.WarpCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.WarpSystem;
import com.spectrasonic.HexaUtils.Commands.Speed.FlySpeedCommand;
import com.spectrasonic.HexaUtils.Commands.Speed.MoveSpeedCommand;
import com.spectrasonic.HexaUtils.Commands.InvSee.InvSeeCommand;
import com.spectrasonic.HexaUtils.Commands.Pvp.PVPCommand;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Manager.BlockcommandManager;
import com.spectrasonic.HexaUtils.Manager.FirstSpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class CommandManager {

    private final Main plugin;
    @Getter
    private final PaperCommandManager commandManager;
    private final BlockcommandManager blockcommandManager;
    private final FirstSpawnManager firstSpawnManager;

    public CommandManager(Main plugin, BlockcommandManager blockcommandManager, FirstSpawnManager firstSpawnManager) {
        this.plugin = plugin;
        this.blockcommandManager = blockcommandManager;
        this.firstSpawnManager = firstSpawnManager;
        this.commandManager = new PaperCommandManager(plugin);

        registerCommands();
        registerCompletions();
    }

    private void registerCommands() {
        commandManager.registerCommand(new HexaUtils(plugin));
        commandManager.registerCommand(new DelWarpCommand(plugin));
        commandManager.registerCommand(new SetWarpCommand(plugin));
        commandManager.registerCommand(new WarpCommand(plugin));
        commandManager.registerCommand(new WarpSystem(plugin));
        commandManager.registerCommand(new OperatorCommand(plugin));
        commandManager.registerCommand(new PluginHiderCommand(plugin, blockcommandManager));
        commandManager.registerCommand(new GameModeCommand(plugin));
        commandManager.registerCommand(new NightVisionCommand(plugin));
        commandManager.registerCommand(new FirstSpawn(firstSpawnManager, plugin));
        commandManager.registerCommand(new ItemDropSwitcher(plugin));
        commandManager.registerCommand(new SoundToAllCommand(plugin));
        commandManager.registerCommand(new PointStick(plugin));
        commandManager.registerCommand(new FlySpeedCommand(plugin));
        commandManager.registerCommand(new MoveSpeedCommand(plugin));
        commandManager.registerCommand(new InvSeeCommand(plugin));
        commandManager.registerCommand(new PVPCommand());
    }

    private void registerCompletions() {
        commandManager.getCommandCompletions().registerCompletion("warps", c -> plugin.getWarpManager().getWarpNames());
        commandManager.getCommandCompletions().registerCompletion("players", c -> {
            List<String> playerNames = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames;
        });

        commandManager.getCommandCompletions().registerCompletion("sounds", c -> {
            List<String> soundNames = new ArrayList<>();
            for (Sound sound : Sound.values()) {
                soundNames.add(sound.name());
            }
            return soundNames;
        });
    }
}
