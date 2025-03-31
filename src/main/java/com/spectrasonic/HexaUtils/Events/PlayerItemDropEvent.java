package com.spectrasonic.HexaUtils.Events;

import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerItemDropEvent implements Listener {

    private final Main plugin;

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (plugin.itemDrop() && !player.hasPermission("hexautils.dropitem.bypass")) {
            event.setCancelled(true);
            MiniMessageUtils.sendMessage(player, "&cYou can't drop items.");
        }
    }
}