package me.alen_alex.addressspawn.commands;

import me.alen_alex.addressspawn.AddressSpawn;
import me.alen_alex.addressspawn.object.SpawnLocations;
import org.bukkit.entity.Player;

import java.util.List;

public class SetCommand extends Subcommands{

    public SetCommand(AddressSpawn addressSpawn) {
        super(addressSpawn);
    }

    @Override
    public String getCommandName() {
        return "set";
    }

    @Override
    public String getCommandPermission() {
        return "as.admin.set";
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
        return false;
    }

    @Override
    public String getDescription() {
        return "Set a spawn with the current location for specific address";
    }

    @Override
    public String getSyntax() {
        return "/as set [address]";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if(args.length < 2 || args.length > 2) {
            getPlugin().getLogger().info(String.valueOf(args.length));
            unknownCommand(player);
        }else {
            final String addressName = args[1];
            if(getPlugin().getPluginManager().getLocationConfigHashMap().containsKey(addressName)){
                sendMessage(player,"&cThis location for address already exist, remove the current one to add this");
                getPlugin().getChatUtils().sendJsonSuggestion(player,"&6Click on this message to remove the current location","/as remove "+addressName,"This is a suggestion message.");
                return;
            }

            final SpawnLocations locations = new SpawnLocations(addressName,player.getLocation());
            locations.save(getPlugin().getConfiguration().getLocationConfig().getLocationCache());
            getPlugin().getPluginManager().getLocationConfigHashMap().put(addressName,locations);
            sendMessage(player,"&aAdded "+addressName+" with your current location");
        }
    }

    @Override
    public List<String> getAllowedArgs() {
        return null;
    }
}
