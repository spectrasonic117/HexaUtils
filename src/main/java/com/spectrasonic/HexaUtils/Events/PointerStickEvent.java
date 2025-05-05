package com.spectrasonic.HexaUtils.Events;

import com.spectrasonic.HexaUtils.Main;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PointerStickEvent implements Listener {

    private final Main plugin;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        // Verificar si es clic derecho con un ítem
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        // Verificar si el ítem es el palo señalador
        if (item == null || !item.hasItemMeta()) {
            return;
        }

        NamespacedKey pointerKey = new NamespacedKey(plugin, "pointer_stick");
        if (!item.getItemMeta().getPersistentDataContainer().has(pointerKey, PersistentDataType.BYTE)) {
            return;
        }

        // Obtener el bloque al que apunta el jugador
        Block targetBlock = player.getTargetBlockExact(100);
        if (targetBlock == null) {
            return;
        }

        // Crear cubo de partículas 3x3
        createParticleCube(targetBlock.getLocation(), 1);

        // Prevenir la acción normal del ítem
        event.setCancelled(true);
    }

    private void createParticleCube(Location center, int radius) {
        // Subir la ubicación central 2 bloques para mejor visibilidad
        Location adjustedCenter = center.clone().add(0, 2, 0);

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    // Solo crear partículas en los bordes del cubo
                    if (Math.abs(x) == radius || Math.abs(y) == radius || Math.abs(z) == radius) {
                        Location particleLoc = adjustedCenter.clone().add(x, y, z);
                        adjustedCenter.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, particleLoc, 1, 0, 0, 0, 0);
                    }
                }
            }
        }
    }
}