package com.spectrasonic.HexaUtils.Manager;

import com.spectrasonic.HexaUtils.Main;

import java.util.HashSet;
import java.util.Set;

public class BlockcommandManager {
    
    private final Main plugin;
    private Set<String> blockedCommands;

    public BlockcommandManager(Main plugin) {
        this.plugin = plugin;
        loadBlockedCommands();
        
    }

    public void loadBlockedCommands() {
        blockedCommands = new HashSet<>(plugin.getConfig().getStringList("Blocked_Commands"));
    }

    public Set<String> getBlockedCommands() {
        return blockedCommands;
    }

    public boolean isBlockedCommand(String command) {
        return blockedCommands.contains(command.toLowerCase());
    }
}
