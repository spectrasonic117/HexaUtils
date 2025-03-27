package com.spectrasonic.HexaUtils;

import co.aikar.commands.PaperCommandManager;

// --- Commands ---
import com.spectrasonic.HexaUtils.Commands.HexaUtils;
import com.spectrasonic.HexaUtils.Commands.Hider.PluginHiderCommand;
import com.spectrasonic.HexaUtils.Commands.Operator.OperatorCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.*;
import com.spectrasonic.HexaUtils.Commands.GameModeSwitch.GameModeCommand;
import com.spectrasonic.HexaUtils.Commands.NightVision.NightVisionCommand;
import com.spectrasonic.HexaUtils.Commands.FirstSpawn.FirstSpawn;

// --- Managers ---
import com.spectrasonic.HexaUtils.Manager.WarpManager;
import com.spectrasonic.HexaUtils.Manager.BlockcommandManager;
import com.spectrasonic.HexaUtils.Manager.FirstSpawnManager;
import com.spectrasonic.HexaUtils.Manager.ConfigManager;

// --- Events ---
import com.spectrasonic.HexaUtils.Events.CommandListener;
import com.spectrasonic.HexaUtils.Events.FirstJoinListener;

// --- Utils ---
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Main extends JavaPlugin {

    @Getter
    private WarpManager warpManager;
    @Getter
    private PaperCommandManager commandManager;
    @Getter
    private BlockcommandManager blockcommandManager;
    @Getter
    private FirstSpawnManager firstSpawnManager;
    @Getter
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        initializeManagers();
        registerCommands();
        registerEvents();
        MiniMessageUtils.sendStartupMessage(this);

        configManager = new ConfigManager(this, blockcommandManager, firstSpawnManager);
        MiniMessageUtils.sendHexaStartup(this);
    }

    @Override
    public void onDisable() {
        MiniMessageUtils.sendShutdownMessage(this);
    }

    private void initializeManagers() {
        warpManager = new WarpManager(this);
        blockcommandManager = new BlockcommandManager(this);

        firstSpawnManager = new FirstSpawnManager(this);
        firstSpawnManager.load();
    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);
        BlockcommandManager blockcommandManager = new BlockcommandManager(this);

        commandManager.registerCommand(new HexaUtils(this));
        commandManager.registerCommand(new DelWarpCommand(this));
        commandManager.registerCommand(new SetWarpCommand(this));
        commandManager.registerCommand(new WarpCommand(this));
        commandManager.registerCommand(new WarpSystem(this));
        commandManager.registerCommand(new OperatorCommand(this));
        commandManager.registerCommand(new PluginHiderCommand(this, blockcommandManager));
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
        commandManager.registerCommand(new FirstSpawn(firstSpawnManager, this));

    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new CommandListener(this, blockcommandManager), this);
        getServer().getPluginManager().registerEvents(new FirstJoinListener(firstSpawnManager), this);
    }

    public void reloadConfigs() {
        configManager.reloadAll();
    }

}
