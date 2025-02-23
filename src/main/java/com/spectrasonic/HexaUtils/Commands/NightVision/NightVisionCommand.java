package com.spectrasonic.HexaUtils.Commands.NightVision;

import com.spectrasonic.HexaUtils.Main;
import com.spectrasonic.HexaUtils.Manager.NightVisionManager;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

@CommandAlias("nightvision|nv")
@RequiredArgsConstructor
public class NightVisionCommand extends BaseCommand {
    
    private final Main plugin;
    private final NightVisionManager nightVisionManager;
    
    public NightVisionCommand(Main plugin) {
        this.plugin = plugin;
        this.nightVisionManager = new NightVisionManager();
    }
    
    @Default
    @CommandPermission("hexautils.nightvision")
    @Description("Toggle night vision effect")
    public void onNightVision(Player player) {
        NightVisionManager.toggleNightVision(player);
    }
}