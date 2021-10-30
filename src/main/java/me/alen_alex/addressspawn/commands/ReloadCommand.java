package me.alen_alex.addressspawn.commands;

import me.alen_alex.addressspawn.AddressSpawn;
import org.bukkit.entity.Player;

import java.util.List;

public class ReloadCommand extends Subcommands{

    public ReloadCommand(AddressSpawn plugin) {
        super(plugin);
    }

    @Override
    public String getCommandName() {
        return "reload";
    }

    @Override
    public String getCommandPermission() {
        return "as.admin";
    }

    @Override
    public boolean registerTabCompleter() {
        return true;
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public boolean doRunFromConsole() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Reloads the plugin";
    }

    @Override
    public String getSyntax() {
        return "/as reload";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        getPlugin().getConfiguration().reload();
        sendMessage(player,"&aThe plugin has been reloaded!");
    }

    @Override
    public List<String> getAllowedArgs() {
        return null;
    }
}
