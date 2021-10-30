package me.alen_alex.addressspawn.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.enums.EnumUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ChatUtils {

    private final JavaPlugin plugin;
    private String pluginPrefix;

    public ChatUtils(JavaPlugin plugin) {
        this.plugin = plugin;
        pluginPrefix = "";
    }

    public ChatUtils(JavaPlugin plugin, String pluginPrefix) {
        this.plugin = plugin;
        this.pluginPrefix = pluginPrefix;
    }

    public void setPluginPrefix(@NotNull String pluginPrefix) {
        this.pluginPrefix = pluginPrefix;
    }

    public final String parseColorCodes(@NotNull String message){
        return ChatColor.translateAlternateColorCodes('&',message);
    }

    public final String formatString(String message){
        if(StringUtils.isBlank(message))
            return "";

        return parseColorCodes(this.pluginPrefix+" "+message);
    }

    public void sendMessage(@NotNull Player player,String message){
        if(StringUtils.isBlank(message))
            return;

        player.sendMessage(formatString(message));
    }

    public void sendMessage(@NotNull CommandSender player, String message){
        if(StringUtils.isBlank(message))
            return;

        player.sendMessage(formatString(message));
    }

    public void sendMessageWithSound(@NotNull Player player, String message, @NotNull Sound sound){
        if(StringUtils.isBlank(message))
            return;

        player.sendMessage(formatString(message));
        player.playSound(player.getLocation(),sound,1.0F,1.0F);
    }

    public void sendJsonSuggestion(@NotNull Player player,String message,@NotNull String suggestionCommand,String hoverText){
        if(StringUtils.isBlank(message))
            return;

        final String parsedMessage = formatString(message);
        final String parsedHoverText = parseColorCodes(hoverText);
        final TextComponent tc = new TextComponent();

        tc.setText(parsedMessage);
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,suggestionCommand));
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(parsedHoverText).create()));
        player.spigot().sendMessage(tc);
    }

    public void sendJsonCommand(@NotNull Player player,String message,@NotNull String commandWithoutSlash,String hoverText){
        if(StringUtils.isBlank(message))
            return;

        final String parsedMessage = formatString(message);
        final String parsedHoverText = parseColorCodes(hoverText);
        final String parsedCommand = "/"+commandWithoutSlash;
        final TextComponent tc = new TextComponent();

        tc.setText(parsedMessage);
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,parsedCommand));
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(parsedHoverText).create()));
        player.spigot().sendMessage(tc);
    }

    public void sendJsonBroadcastCommand(String message,@NotNull String commandWithoutSlash,String hoverText){
        plugin.getServer().getOnlinePlayers().forEach((player -> {
            sendJsonCommand(player,message,commandWithoutSlash,hoverText);
        }));
    }

    public String stripColorCodes(@NotNull String message){
        return ChatColor.stripColor(message);
    }

}
