package com.spectrasonic.HexaUtils;

import co.aikar.commands.PaperCommandManager;
import com.spectrasonic.HexaUtils.Commands.Operator.OperatorCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.*;
import com.spectrasonic.HexaUtils.Commands.GameModeSwitch.GameModeCommand;
import com.spectrasonic.HexaUtils.Manager.WarpManager;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import com.spectrasonic.HexaUtils.Events.CommandListener;
import com.spectrasonic.HexaUtils.Manager.BlockcommandManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    private WarpManager warpManager;
    private PaperCommandManager commandManager;
    private BlockcommandManager blockcommandManager;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        initializeManagers();
        registerCommands();
        MiniMessageUtils.sendStartupMessage(this);
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
        commandManager.registerCommand(new DelWarpCommand(this));
        commandManager.registerCommand(new SetWarpCommand(this));
        commandManager.registerCommand(new WarpCommand(this));
        commandManager.registerCommand(new WarpSimpleCommand(this));
        commandManager.registerCommand(new OperatorCommand(this));
        commandManager.getCommandCompletions().registerCompletion("warps", c -> warpManager.getWarpNames());
        commandManager.getCommandCompletions().registerCompletion("players", c -> {
            List<String> playerNames = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames;
        });
        commandManager.registerCommand(new GameModeCommand(this));
    }


    public void reloadConfigs() {
        warpManager.reloadWarpsConfig();
        reloadConfig();
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }

}
