package com.spectrasonic.HexaUtils.Events;

import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Manager.BlockcommandManager;
import com.spectrasonic.HexaUtils.Manager.FirstSpawnManager;
import org.bukkit.event.Listener;
import lombok.Getter;

public class EventsManager {

    private final Main plugin;
    @Getter
    private final CommandListener commandListener;
    @Getter
    private final FirstJoinListener firstJoinListener;
    @Getter
    private final PlayerItemDropEvent playerItemDropEvent;

    public EventsManager(Main plugin, BlockcommandManager blockcommandManager, FirstSpawnManager firstSpawnManager) {
        this.plugin = plugin;

        this.commandListener = new CommandListener(plugin, blockcommandManager);
        this.firstJoinListener = new FirstJoinListener(firstSpawnManager);
        this.playerItemDropEvent = new PlayerItemDropEvent(plugin);

        registerEvents();
    }

    private void registerEvents() {
        registerListener(commandListener);
        registerListener(firstJoinListener);
        registerListener(playerItemDropEvent);
    }

    private void registerListener(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}