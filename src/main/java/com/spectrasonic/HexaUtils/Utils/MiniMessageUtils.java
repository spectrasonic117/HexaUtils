package com.spectrasonic.HexaUtils.Utils;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MiniMessageUtils {

    public static final String DIVIDER = "<gray>----------------------------------------</gray>";
    public static final String PREFIX = "<gray>[<color:#EF3341> HexaUtils </color>]</gray> <gold>»</gold> ";

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void sendMessage(Player player, String s) {
        player.sendMessage(miniMessage.deserialize(PREFIX + s));
    }

    private void MessageUtils() {
        // Private constructor to prevent instantiation
    }

    public static void sendMessage(CommandSender sender, String message, String warpName, String arg) {
        sender.sendMessage(miniMessage.deserialize(PREFIX + message));
    }
    public static void sendSingleMessage(CommandSender sender, String message, String arg) {
        sender.sendMessage(miniMessage.deserialize(PREFIX + message));
    }

    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(PREFIX + message));
    }

    public static void sendPermissionMessage(CommandSender sender) {
        sender.sendMessage(miniMessage.deserialize(PREFIX + "<red>You do not have permission to use this command!</red>"));
    }

    public static void sendStartupMessage(JavaPlugin plugin) {
        String[] messages = {
                DIVIDER,
                PREFIX + "<white>" + plugin.getDescription().getName() + "</white> <green>Plugin Enabled!</green>",
                PREFIX + "<light_purple>Version: </light_purple>" + plugin.getDescription().getVersion(),
                PREFIX + "<white>Developed by: </white><red>" + plugin.getDescription().getAuthors() + "</red>",
                DIVIDER
        };

        for (String message : messages) {
            Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(message));
        }
    }

    public static void sendVeiMessage(JavaPlugin plugin) {
        String[] messages = {
                PREFIX + "⣇⣿⠘⣿⣿⣿⡿⡿⣟⣟⢟⢟⢝⠵⡝⣿⡿⢂⣼⣿⣷⣌⠩⡫⡻⣝⠹⢿⣿⣷",
                PREFIX + "⡆⣿⣆⠱⣝⡵⣝⢅⠙⣿⢕⢕⢕⢕⢝⣥⢒⠅⣿⣿⣿⡿⣳⣌⠪⡪⣡⢑⢝⣇",
                PREFIX + "⡆⣿⣿⣦⠹⣳⣳⣕⢅⠈⢗⢕⢕⢕⢕⢕⢈⢆⠟⠋⠉⠁⠉⠉⠁⠈⠼⢐⢕⢽",
                PREFIX + "⡗⢰⣶⣶⣦⣝⢝⢕⢕⠅⡆⢕⢕⢕⢕⢕⣴⠏⣠⡶⠛⡉⡉⡛⢶⣦⡀⠐⣕⢕",
                PREFIX + "⡝⡄⢻⢟⣿⣿⣷⣕⣕⣅⣿⣔⣕⣵⣵⣿⣿⢠⣿⢠⣮⡈⣌⠨⠅⠹⣷⡀⢱⢕",
                PREFIX + "⡝⡵⠟⠈⢀⣀⣀⡀⠉⢿⣿⣿⣿⣿⣿⣿⣿⣼⣿⢈⡋⠴⢿⡟⣡⡇⣿⡇⡀⢕",
                PREFIX + "⡝⠁⣠⣾⠟⡉⡉⡉⠻⣦⣻⣿⣿⣿⣿⣿⣿⣿⣿⣧⠸⣿⣦⣥⣿⡇⡿⣰⢗⢄",
                PREFIX + "⠁⢰⣿⡏⣴⣌⠈⣌⠡⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣬⣉⣉⣁⣄⢖⢕⢕⢕",
                PREFIX + "⡀⢻⣿⡇⢙⠁⠴⢿⡟⣡⡆⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣵⣵⣿",
                PREFIX + "⡻⣄⣻⣿⣌⠘⢿⣷⣥⣿⠇⣿⣿⣿⣿⣿⣿⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿",
                PREFIX + "⣷⢄⠻⣿⣟⠿⠦⠍⠉⣡⣾⣿⣿⣿⣿⣿⣿⢸⣿⣦⠙⣿⣿⣿⣿⣿⣿⣿⣿⠟",
                PREFIX + "⡕⡑⣑⣈⣻⢗⢟⢞⢝⣻⣿⣿⣿⣿⣿⣿⣿⠸⣿⠿⠃⣿⣿⣿⣿⣿⣿⡿⠁⣠",
                PREFIX + "⡝⡵⡈⢟⢕⢕⢕⢕⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣿⣿⣿⣿⣿⠿⠋⣀⣈⠙",
                PREFIX + "⡝⡵⡕⡀⠑⠳⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⢉⡠⡲⡫⡪⡪⡣",
        };

        for (String message : messages) {
            Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(message));
        }
    }

    public static void sendBroadcastMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(miniMessage.deserialize(message));
        }
    }

    public static void sendShutdownMessage(JavaPlugin plugin) {
        String[] messages = {
                DIVIDER,
                PREFIX + "<red>" + plugin.getDescription().getName() + " plugin Disabled!</red>",
                DIVIDER
        };

        for (String message : messages) {
            Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(message));
        }
    }
}
