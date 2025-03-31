package com.spectrasonic.HexaUtils.Commands.ItemDrop;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Single;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@CommandAlias("itemdrop|id")
@CommandPermission("hexautils.itemdrop")
@RequiredArgsConstructor
public class ItemDropSwitcher extends BaseCommand {

    @Dependency
    private final Main plugin;

    @CommandCompletion("true|false")
    public void onCommand(Player sender, @Single String state) {
        if (state.equalsIgnoreCase("false")) {
            plugin.setPreventDrop(true);
            MiniMessageUtils.sendMessage(sender, "&cItemDrop Disabled");
        } else if (state.equalsIgnoreCase("true")) {
            plugin.setPreventDrop(false);
            MiniMessageUtils.sendMessage(sender, "&aItemDrop enabled");
        }
    }
}