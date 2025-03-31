package com.spectrasonic.HexaUtils.Commands.ItemDrop;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Utils.MiniMessageUtils;
import org.bukkit.entity.Player;

@CommandAlias("itemdrop|id")
@CommandPermission("hexautils.itemdrop")
public class ItemDropSwitcher extends BaseCommand {

    private final Main plugin;

    public ItemDropSwitcher(Main plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("true|false")
    public void onCommand(Player sender, @Optional String state) {
        if (state == null) {
            MiniMessageUtils.sendMessage(sender, "<yellow>Use: <reset>/itemdrop <true|false>");
            return;
        }

        if (state.equalsIgnoreCase("false")) {
            plugin.setPreventDrop(true);
            MiniMessageUtils.sendMessage(sender, "<red><b>ItemDrop Disabled");
        } else if (state.equalsIgnoreCase("true")) {
            plugin.setPreventDrop(false);
            MiniMessageUtils.sendMessage(sender, "<green><b>ItemDrop Enabled");
        } else {
            MiniMessageUtils.sendMessage(sender, "<yellow>Use: <reset>/itemdrop <true|false>");
        }
    }
}
