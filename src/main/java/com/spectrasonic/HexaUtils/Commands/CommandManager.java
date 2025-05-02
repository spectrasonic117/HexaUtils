package com.spectrasonic.HexaUtils.Commands;

import co.aikar.commands.PaperCommandManager;
import com.spectrasonic.HexaUtils.Commands.FirstSpawn.FirstSpawn;
import com.spectrasonic.HexaUtils.Commands.GameModeSwitch.GameModeCommand;
import com.spectrasonic.HexaUtils.Commands.Hider.PluginHiderCommand;
import com.spectrasonic.HexaUtils.Commands.ItemDrop.ItemDropSwitcher;
import com.spectrasonic.HexaUtils.Commands.NightVision.NightVisionCommand;
import com.spectrasonic.HexaUtils.Commands.Operator.OperatorCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.DelWarpCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.SetWarpCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.WarpCommand;
import com.spectrasonic.HexaUtils.Commands.Warps.WarpSystem;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Manager.BlockcommandManager;
import com.spectrasonic.HexaUtils.Manager.FirstSpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Clase encargada de gestionar todos los comandos del plugin
 */
public class CommandManager {

    private final Main plugin;
    @Getter
    private final PaperCommandManager commandManager;
    private final BlockcommandManager blockcommandManager;
    private final FirstSpawnManager firstSpawnManager;

    /**
     * Constructor de CommandManager
     * 
     * @param plugin              Instancia del plugin principal
     * @param blockcommandManager Gestor de comandos bloqueados
     * @param firstSpawnManager   Gestor del primer spawn
     */
    public CommandManager(Main plugin, BlockcommandManager blockcommandManager, FirstSpawnManager firstSpawnManager) {
        this.plugin = plugin;
        this.blockcommandManager = blockcommandManager;
        this.firstSpawnManager = firstSpawnManager;
        this.commandManager = new PaperCommandManager(plugin);

        registerCommands();
        registerCompletions();
    }

    /**
     * Registra todos los comandos del plugin
     */
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
    }

    /**
     * Registra los completadores de comandos
     */
    private void registerCompletions() {
        commandManager.getCommandCompletions().registerCompletion("warps", c -> plugin.getWarpManager().getWarpNames());
        commandManager.getCommandCompletions().registerCompletion("players", c -> {
            List<String> playerNames = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames;
        });
    }
}