package com.spectrasonic.HexaUtils.Manager;

import com.spectrasonic.HexaUtils.Main;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfigManager {

    private final Main plugin;
    private final BlockcommandManager blockCommandManager;
    private final FirstSpawnManager firstSpawnManager;

    /**
     * Recarga todas las configuraciones del plugin
     */
    public void reloadAll() {
        // Recargar config.yml principal
        plugin.reloadConfig();

        // Recargar configuraciones específicas
        blockCommandManager.loadBlockedCommands();
        firstSpawnManager.load();

        // Recargar warps (si es necesario)
        if (plugin.getWarpManager() != null) {
            plugin.getWarpManager().loadWarps();
        }
    }

    /**
     * Recarga solo la configuración de PluginHider
     */
    public void reloadPluginHider() {
        blockCommandManager.loadBlockedCommands();
    }

    /**
     * Recarga solo la configuración de FirstSpawn
     */
    public void reloadFirstSpawn() {
        firstSpawnManager.load();
    }

    /**
     * Recarga solo la configuración de Operator
     */
    public void reloadOperator() {
        plugin.reloadConfig();
    }

    /**
     * Recarga solo la configuración de Warps
     */
    public void reloadWarps() {
        if (plugin.getWarpManager() != null) {
            plugin.getWarpManager().loadWarps();
        }
    }
}
