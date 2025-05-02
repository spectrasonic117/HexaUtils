package com.spectrasonic.HexaUtils;

import co.aikar.commands.PaperCommandManager;

// --- Managers ---
import com.spectrasonic.HexaUtils.Manager.WarpManager;
import com.spectrasonic.HexaUtils.Manager.BlockcommandManager;
import com.spectrasonic.HexaUtils.Manager.FirstSpawnManager;
import com.spectrasonic.HexaUtils.Manager.ConfigManager;

import com.spectrasonic.HexaUtils.Commands.CommandManager;
import com.spectrasonic.HexaUtils.Events.EventsManager;

// --- Utils ---
import com.spectrasonic.HexaUtils.Utils.MessageUtils;

import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;

public class Main extends JavaPlugin {

    @Getter
    private WarpManager warpManager;
    @Getter
    private CommandManager commandManager;
    @Getter
    private BlockcommandManager blockcommandManager;
    @Getter
    private FirstSpawnManager firstSpawnManager;
    @Getter
    private ConfigManager configManager;
    @Getter
    private EventsManager eventsManager;
    @Getter
    private boolean canPlayersDropItems = true;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        initializeManagers();

        MessageUtils.sendStartupMessage(this);
        MessageUtils.sendHexaStartup(this);
    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }

    private void initializeManagers() {
        warpManager = new WarpManager(this);
        blockcommandManager = new BlockcommandManager(this);

        firstSpawnManager = new FirstSpawnManager(this);
        firstSpawnManager.load();

        configManager = new ConfigManager(this, blockcommandManager, firstSpawnManager);
        eventsManager = new EventsManager(this, blockcommandManager, firstSpawnManager);
        commandManager = new CommandManager(this, blockcommandManager, firstSpawnManager);
    }

    public boolean itemDrop() {
        return canPlayersDropItems;
    }

    public void setPreventDrop(boolean itemDrop) {
        this.canPlayersDropItems = itemDrop;
    }

    public void reloadConfigs() {
        configManager.reloadAll();
    }

    public PaperCommandManager getPaperCommandManager() {
        return commandManager.getCommandManager();
    }

}
