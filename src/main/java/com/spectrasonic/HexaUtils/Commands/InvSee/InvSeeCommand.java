package com.spectrasonic.HexaUtils.Commands.InvSee;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import com.spectrasonic.HexaUtils.Main;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CommandAlias("invsee")
@CommandPermission("hexautils.invsee")
public class InvSeeCommand extends BaseCommand {

    private final Main plugin;

    @Subcommand("player")
    public void onInvSee(Player sender, String targetName) {
        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            sender.sendMessage(Component.text("Jugador no encontrado."));
            return;
        }

        Inventory targetInventory = target.getInventory();
        Inventory viewerInventory = Bukkit.createInventory(null, 54, Component.text(target.getName() + "'s inventory"));

        for (int i = 0; i < targetInventory.getSize(); i++) {
            viewerInventory.setItem(i, targetInventory.getItem(i));
        }

        sender.openInventory(viewerInventory);
    }
}
