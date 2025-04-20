package com.spectrasonic.HexaUtils.Events;

import com.spectrasonic.HexaUtils.Manager.FirstSpawnManager;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

@RequiredArgsConstructor
public class FirstJoinListener implements Listener {

    private final FirstSpawnManager config;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!config.isEnabled() || e.getPlayer().hasPlayedBefore())
            return;

        Player player = e.getPlayer();
        if (config.getFirstSpawn() != null) {
            player.teleport(config.getFirstSpawn());
            String welcomeMsg = config.getWelcomeMessage();
            if (welcomeMsg != null && !welcomeMsg.isEmpty()) {
                MessageUtils.sendMessage(player, welcomeMsg);
            }
        }
    }
}
