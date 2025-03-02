package com.spectrasonic.HexaUtils.Manager;

import com.spectrasonic.HexaUtils.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockcommandManager {
    private final Main plugin;
    private Set<String> blockedCommands;

    public BlockcommandManager(Main plugin) {
        this.plugin = plugin;
        loadBlockedCommands();
        
    }

    public void loadBlockedCommands() {
        FileConfiguration config = plugin.getConfig();
        List<String> blockedCommandsList = config.getStringList("Blocked_Commands");
        blockedCommands = new HashSet<>(blockedCommandsList);
    }

    public Set<String> getBlockedCommands() {
        return blockedCommands;
    }

    public boolean isBlockedCommand(String command) {
        return blockedCommands.contains(command.toLowerCase());
    }

    public void reloadBlockedCommands() {
    // Clear the current list
    blockedCommands.clear();
    
    // Reload the list from config
    // This depends on how your config is structured, but might look something like:
    blockedCommands.addAll(plugin.getConfig().getStringList("blocked-commands"));
}
}
