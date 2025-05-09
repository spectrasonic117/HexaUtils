package com.spectrasonic.HexaUtils.Commands.Speed;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import org.bukkit.entity.Player;
import com.spectrasonic.HexaUtils.Main;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CommandAlias("flyspeed|fspeed")
@CommandPermission("hexautils.flyspeed")
public class FlySpeedCommand extends BaseCommand {

    private static final float DEFAULT_FLY_SPEED = 0.1f;
    private final Main plugin;

    @Default
    @CommandCompletion("0|1|2|3|4|5")
    public void onFlySpeed(Player player, @Optional String speedArg) {

        float speed;
        if (speedArg == null || speedArg.equals("0")) {
            speed = DEFAULT_FLY_SPEED;
        } else {
            try {
                int speedInt = Integer.parseInt(speedArg);
                if (speedInt < 0 || speedInt > 5) {
                    MessageUtils.sendMessage(player, "<red>El valor debe estar entre 0 y 5.</red>");
                    return;
                }
                speed = speedInt * 0.2f;
            } catch (NumberFormatException e) {
                MessageUtils.sendMessage(player, "<red>El valor debe ser un número entre 0 y 5.</red>");
                return;
            }
        }

        player.setFlySpeed(speed);
        MessageUtils.sendMessage(player, "<green>Velocidad de vuelo en " + speed + ".</green>");
    }
}