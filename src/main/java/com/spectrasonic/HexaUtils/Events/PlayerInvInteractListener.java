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

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class PlayerInvInteractListener implements Listener {
    private final Main plugin;
    private final Map<Inventory, Player> invSeeMap = new HashMap<>();

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking() && player.hasPermission("invsee.use") && event.getRightClicked() instanceof Player) {
            Player target = (Player) event.getRightClicked();
            Inventory targetInventory = target.getInventory();
            Inventory viewerInventory = Bukkit.createInventory(null, 54,
                    Component.text(target.getName() + "'s inventory"));
            for (int i = 0; i < targetInventory.getSize(); i++) {
                viewerInventory.setItem(i, targetInventory.getItem(i));
            }
            invSeeMap.put(viewerInventory, target);
            player.openInventory(viewerInventory);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory closed = event.getInventory();
        if (invSeeMap.containsKey(closed)) {
            Player target = invSeeMap.get(closed);
            Inventory targetInv = target.getInventory();
            for (int i = 0; i < targetInv.getSize(); i++) {
                targetInv.setItem(i, closed.getItem(i));
            }
            invSeeMap.remove(closed);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clicked = event.getInventory();
        if (invSeeMap.containsKey(clicked)) {
            Player target = invSeeMap.get(clicked);
            Inventory targetInv = target.getInventory();
            int slot = event.getRawSlot();
            if (slot >= 0 && slot < targetInv.getSize()) {
                targetInv.setItem(slot, event.getCurrentItem());
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Inventory dragged = event.getInventory();
        if (invSeeMap.containsKey(dragged)) {
            Player target = invSeeMap.get(dragged);
            Inventory targetInv = target.getInventory();
            event.getNewItems().forEach((slot, item) -> {
                if (slot >= 0 && slot < targetInv.getSize()) {
                    targetInv.setItem(slot, item);
                }
            });
        }
    }
}