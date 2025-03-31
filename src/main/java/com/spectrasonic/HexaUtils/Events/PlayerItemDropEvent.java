package com.spectrasonic.HexaUtils.Events;

import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.entity.Player;

public class PlayerItemDropEvent implements Listener {

    private final Main plugin;

    public PlayerItemDropEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (plugin.itemDrop() && !player.hasPermission("hexautils.itemdrop.bypass")) {
            event.setCancelled(true);
            MiniMessageUtils.sendMessage(player, "<red>You can't drop items.");
        }
    }
}