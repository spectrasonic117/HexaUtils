package com.spectrasonic.HexaUtils.Commands.Speed;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.spectrasonic.HexaUtils.Utils.MessageUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import com.spectrasonic.HexaUtils.Main;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CommandAlias("movespeed|mspeed")
@CommandPermission("hexautils.movespeed")
public class MoveSpeedCommand extends BaseCommand {

    private static final double DEFAULT_MOVE_SPEED = 0.1;
    private final Main plugin;

    @Default
    @CommandCompletion("0|1|2|3|4|5")
    public void onMoveSpeed(Player player, @Optional String speedArg) {
        AttributeInstance moveSpeed = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (moveSpeed == null) {
            MessageUtils.sendMessage(player, "<red>Este jugador no tiene atributo de velocidad de movimiento.</red>");
            return;
        }

        double speed;
        if (speedArg == null || speedArg.equals("0")) {
            speed = DEFAULT_MOVE_SPEED;
        } else {
            try {
                int speedInt = Integer.parseInt(speedArg);
                if (speedInt < 0 || speedInt > 5) {
                    MessageUtils.sendMessage(player, "<red>El valor debe estar entre 0 y 5.</red>");
                    return;
                }
                speed = speedInt * 0.1;
            } catch (NumberFormatException e) {
                MessageUtils.sendMessage(player, "<red>El valor debe ser un número entre 0 y 5.</red>");
                return;
            }
        }

        moveSpeed.setBaseValue(speed);
        MessageUtils.sendMessage(player, "<green>Velocidad de movimiento en " + speed + ".</green>");
    }
}