package me.alen_alex.addressspawn;

import de.leonhard.storage.Json;
import me.alen_alex.addressspawn.commands.ReloadCommand;
import me.alen_alex.addressspawn.commands.RemoveCommand;
import me.alen_alex.addressspawn.commands.SetCommand;
import me.alen_alex.addressspawn.files.Configuration;
import me.alen_alex.addressspawn.listener.PlayerJoinEventListener;
import me.alen_alex.addressspawn.listener.PlayerLoginEventListener;
import me.alen_alex.addressspawn.utils.ChatUtils;
import me.alen_alex.addressspawn.utils.FileUtils;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AddressSpawn extends JavaPlugin{

    private static AddressSpawn plugin;
    private ChatUtils chatUtils;
    private FileUtils fileUtils;
    private Configuration configuration;
    private PluginManager pluginManager;
    @Override
    public void onEnable() {
        plugin = this;
        chatUtils = new ChatUtils(this,"&7[&bA&eS&7] »");

        fileUtils = new FileUtils(this);
        configuration = new Configuration(this);
        pluginManager = new PluginManager(this);
        configuration.initOthers();
        getServer().getPluginManager().registerEvents(new PlayerLoginEventListener(this),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(this),this);
        new CommandHandler(this,"as","as.admin",
                new SetCommand(this),
                new RemoveCommand(this),
                new ReloadCommand(this)).register();
    }


    @Override
    public void onDisable() {

    }

    public ChatUtils getChatUtils() {
        return chatUtils;
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public static AddressSpawn getPlugin() {
        return plugin;
    }
}
