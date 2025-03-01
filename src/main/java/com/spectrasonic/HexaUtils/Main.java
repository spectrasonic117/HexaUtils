package com.spectrasonic.HexaUtils;

import co.aikar.commands.PaperCommandManager;

import com.spectrasonic.HexaUtils.Commands.HexaUtils;
import com.spectrasonic.HexaUtils.Commands.Hider.PluginHiderCommand;
import com.spectrasonic.HexaUtils.Commands.Operator.OperatorCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.*;
import com.spectrasonic.HexaUtils.Commands.GameModeSwitch.GameModeCommand;
import com.spectrasonic.HexaUtils.Commands.NightVision.NightVisionCommand;
import com.spectrasonic.HexaUtils.Commands.FirstSpawn.FirstSpawn;

import com.spectrasonic.HexaUtils.Manager.WarpManager;
import com.spectrasonic.HexaUtils.Manager.BlockcommandManager;

import com.spectrasonic.HexaUtils.Events.CommandListener;

import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    private WarpManager warpManager;
    private PaperCommandManager commandManager;
    private BlockcommandManager blockcommandManager;
    private FirstSpawn firstSpawnCommand;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        initializeManagers();
        registerCommands();
        registerEvents();
        MiniMessageUtils.sendStartupMessage(this);
        MiniMessageUtils.sendHexaStartup(this);
    }

    @Override
    public void onDisable() {
        MiniMessageUtils.sendShutdownMessage(this);
    }

    private void initializeManagers() {
        warpManager = new WarpManager(this);
        blockcommandManager = new BlockcommandManager(this);
        getServer().getPluginManager().registerEvents(new CommandListener(this, blockcommandManager), this);
    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);
        firstSpawnCommand = new FirstSpawn(this);

        commandManager.registerCommand(new HexaUtils(this));
        commandManager.registerCommand(new DelWarpCommand(this));
        commandManager.registerCommand(new SetWarpCommand(this));
        commandManager.registerCommand(new WarpCommand(this));
        commandManager.registerCommand(new WarpSystem(this));
        commandManager.registerCommand(new OperatorCommand(this));
        commandManager.registerCommand(new PluginHiderCommand(this));
        commandManager.getCommandCompletions().registerCompletion("warps", c -> warpManager.getWarpNames());
        commandManager.getCommandCompletions().registerCompletion("players", c -> {
            List<String> playerNames = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames;
        });
        commandManager.registerCommand(new GameModeCommand(this));
        commandManager.registerCommand(new NightVisionCommand(this));
        commandManager.registerCommand(firstSpawnCommand);
        
    }

    private void registerEvents() {
            getServer().getPluginManager().registerEvents(firstSpawnCommand, this);
    }

    public void reloadConfigs() {
        blockcommandManager.loadBlockedCommands();
        warpManager.reloadWarpsConfig();
        reloadConfig();
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }
}
