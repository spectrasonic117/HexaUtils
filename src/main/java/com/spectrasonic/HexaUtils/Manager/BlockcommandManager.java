package com.spectrasonic.HexaUtils.Manager;

import com.spectrasonic.HexaUtils.Main;
import org.bukkit.command.Command;

import java.util.Set;

/**
 * Manages the blocked commands and provides utility methods for command handling.
 */
public class BlockcommandManager {
    private final Main plugin;

    private final Set<String> blockedCommands = Set.of(
            "minecraft:plugins",
            "minecraft:pl",
            "plugins",
            "pl",
            "version",
            "minecraft:version",
            "ver",
            "minecraft:ver",
            "?",
            "help",
            "minecraft:help",
            "minecraft:?"
    );

    public BlockcommandManager(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Gets the set of blocked commands.
     *
     * @return The set of blocked commands
     */
    public Set<String> getBlockedCommands() {
        return blockedCommands;
    }

    /**
     * Checks if a command should be blocked.
     *
     * @param command The command to check
     * @return true if the command should be blocked
     */
    public boolean isBlockedCommand(Command command) {
        return blockedCommands.contains(command.getName().toLowerCase());
    }
}
