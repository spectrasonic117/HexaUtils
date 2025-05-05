package com.spectrasonic.HexaUtils.Commands.PointStick;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.ItemBuilder;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CommandAlias("pointstick|ps|pstick")
@CommandPermission("hexautils.pointstick")
public class PointStick extends BaseCommand {

    private final Main plugin;
    private final NamespacedKey pointerKey;

    public PointStick(Main plugin) {
        this.plugin = plugin;
        this.pointerKey = new NamespacedKey(plugin, "pointer_stick");
    }

    @Default
    @Description("Obtén un palo para señalar bloques con partículas")
    public void onPointStick(Player player) {
        ItemStack pointerStick = ItemBuilder.setMaterial("STICK")
                .setName("<gradient:#FF5555:#FFAA00>Palo Señalador</gradient>")
                .setLore(
                        "<gray>Clic derecho para señalar",
                        "<gray>un lugar con particulas")
                .setCustomModelData(1001)
                .build();

        // Añadir metadatos para identificar el item
        var meta = pointerStick.getItemMeta();
        meta.getPersistentDataContainer().set(pointerKey, PersistentDataType.BYTE, (byte) 1);
        pointerStick.setItemMeta(meta);

        player.getInventory().addItem(pointerStick);
        MessageUtils.sendMessage(player,
                "<green>¡Has recibido un <gradient:#FF5555:#FFAA00>Palo Señalador</gradient><green>!");
    }
}