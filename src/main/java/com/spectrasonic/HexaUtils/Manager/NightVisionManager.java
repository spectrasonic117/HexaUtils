package com.spectrasonic.HexaUtils.Manager;

import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVisionManager {

    public static void toggleNightVision(Player player) {
        if (hasNightVision(player)) {
            removeNightVision(player);
        } else {
            applyNightVision(player);
        }
    }

    private static void applyNightVision(Player player) {
        PotionEffect nightVision = new PotionEffect(
                PotionEffectType.NIGHT_VISION,
                Integer.MAX_VALUE,
                0,
                false,
                false);

        player.addPotionEffect(nightVision);

        MessageUtils.sendMessage(player,
                "<yellow>Night Vision <green><bold>Enabled!");
    }

    private static void removeNightVision(Player player) {
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);

        MessageUtils.sendMessage(player,
                "<yellow>Night Vision <red><bold>Disabled!");
    }

    public static boolean hasNightVision(Player player) {
        return player.hasPotionEffect(PotionEffectType.NIGHT_VISION);
    }
}