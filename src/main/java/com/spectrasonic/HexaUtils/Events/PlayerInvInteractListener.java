package com.spectrasonic.HexaUtils.Events;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import com.spectrasonic.HexaUtils.Main;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerInvInteractListener implements Listener {
    
    private final Main plugin;

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking() && player.hasPermission("invsee.use") && event.getRightClicked() instanceof Player) {
            Player target = (Player) event.getRightClicked();
            Inventory targetInventory = target.getInventory();
            Inventory viewerInventory = Bukkit.createInventory(null, 54, Component.text(target.getName() + "'s inventory"));

            for (int i = 0; i < targetInventory.getSize(); i++) {
                viewerInventory.setItem(i, targetInventory.getItem(i));
            }

            player.openInventory(viewerInventory);
        }
    }
}