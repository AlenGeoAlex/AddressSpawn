package me.alen_alex.addressspawn.commands;

import me.alen_alex.addressspawn.AddressSpawn;
import org.bukkit.entity.Player;

import java.util.List;

public class RemoveCommand extends Subcommands{

    public RemoveCommand(AddressSpawn plugin) {
        super(plugin);
    }

    @Override
    public String getCommandName() {
        return "remove";
    }

    @Override
    public String getCommandPermission() {
        return "as.admin.remove";
    }

    @Override
    public boolean registerTabCompleter() {
        return false;
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
        return "Remove a specific location from the cache file";
    }

    @Override
    public String getSyntax() {
        return "/as remove [address]";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if(args.length >2 || args.length <2){
            final String addressName = args[1];
            if(getPlugin().getPluginManager().getLocationConfigHashMap().containsKey(addressName)){
                getPlugin().getPluginManager().getLocationConfigHashMap().remove(addressName);
                getPlugin().getConfiguration().getLocationConfig().getLocationCache().remove(addressName);
            }else {
                sendMessage(player,"&cLocation with this address does not exist");
            }
        }else unknownCommand(player);
    }

    @Override
    public List<String> getAllowedArgs() {
        return null;
    }
}
