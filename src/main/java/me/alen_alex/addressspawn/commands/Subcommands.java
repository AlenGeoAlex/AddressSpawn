package me.alen_alex.addressspawn.commands;

import me.alen_alex.addressspawn.AddressSpawn;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Subcommands {

    private AddressSpawn plugin;

    public Subcommands(AddressSpawn plugin) {
        this.plugin = plugin;
    }

    public abstract String getCommandName();

    public abstract String getCommandPermission();

    public abstract boolean registerTabCompleter();

    public abstract List<String> getAliases();

    public abstract boolean doRunFromConsole();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void onCommand(Player player, String[] args);

    public abstract List<String> getAllowedArgs();

    public void unknownCommand(Player sender){
        plugin.getChatUtils().sendMessage(sender, "&cUnknown command");
    }

    public void noPermission(Player sender) {
        plugin.getChatUtils().sendMessage(sender, "&cYou don't have enough permission to run this command!");
    }

    public void cantRunFromConsole(Player sender) {
        sender.sendMessage("This command cannot be run from console!");
    }

    public void sendMessage(Player sender,String message){
        plugin.getChatUtils().sendMessage(sender,message);
    }

    public AddressSpawn getPlugin() {
        return plugin;
    }
}
