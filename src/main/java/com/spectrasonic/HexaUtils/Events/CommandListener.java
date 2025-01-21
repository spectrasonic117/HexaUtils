package com.spectrasonic.HexaUtils.Events;

import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import com.spectrasonic.HexaUtils.Manager.BlockcommandManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

/**
 * Listens for command execution attempts and blocks unauthorized access.
 */
public class CommandListener implements Listener {
    private final Main plugin;
    private final BlockcommandManager blockcommandManager;

    public CommandListener(Main plugin, BlockcommandManager blockcommandManager) {
        this.plugin = plugin;
        this.blockcommandManager = blockcommandManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().substring(1).split(" ")[0].toLowerCase();

        if (blockcommandManager.getBlockedCommands().contains(command)) {
            if (!event.getPlayer().isOp()) {
                event.setCancelled(true);
                MiniMessageUtils.sendMessage(event.getPlayer(), "<red>You can't use this command.");
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerCommand(ServerCommandEvent event) {
        // Allow console to execute all commands
    }
}