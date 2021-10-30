package me.alen_alex.addressspawn;

import me.alen_alex.addressspawn.commands.Subcommands;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandHandler extends org.bukkit.command.Command {

    private final AddressSpawn plugin;
    private final String commandName, commandPermission;
    private HashMap<String, Subcommands> subCommandsHashMap;
    private HashMap<String, String> aliases;

    public CommandHandler(AddressSpawn plugin, String commandName, String commandPermission) {
        super(commandName);
        this.plugin = plugin;
        this.commandName = commandName;
        this.commandPermission = commandPermission;
        subCommandsHashMap = new HashMap<String, Subcommands>();
        aliases = new HashMap<String, String>();
        plugin.getLogger().info("Registered command: " + commandName);
    }

    public CommandHandler(AddressSpawn plugin, String commandName, String commandPermission,Subcommands... subcommands) {
        super(commandName);
        this.plugin = plugin;
        this.commandName = commandName;
        this.commandPermission = commandPermission;
        subCommandsHashMap = new HashMap<String, Subcommands>();
        aliases = new HashMap<String, String>();
        plugin.getLogger().info("Registered command: " + commandName);
        Arrays.stream(subcommands).forEach((s) -> {
            registerSubCommand(s.getCommandName(),s);
        });
    }

    public void register() {
        Field commandField = null;
        try {
            commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
            commandField.setAccessible(true);
            CommandMap commandMap = null;
            commandMap = (CommandMap) commandField.get(plugin.getServer());
            commandMap.register(commandName, this);

        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void registerSubCommand(String commandName, Subcommands subCommand) {
        subCommandsHashMap.put(commandName, subCommand);
        aliases.put(commandName,commandName);
        if(subCommand.getAliases() != null) {
            subCommand.getAliases().forEach((a) -> {
                aliases.put(a, commandName);
            });
        }
        plugin.getLogger().info("Registered Subcommand for " + this.commandName + ": " + subCommand.getCommandName());
    }


    public void noPermission(CommandSender sender) {
        plugin.getChatUtils().sendMessage(sender, "&cYou don't have enough permission to run this command!");
    }

    public void cantRunFromConsole(CommandSender sender) {
        sender.sendMessage("This command cannot be run from console!");
    }

    public void unknownCommand(CommandSender sender) {
        plugin.getChatUtils().sendMessage(sender, "&cUnknown command");
        helpMessage(sender);
    }

    public void helpMessage(CommandSender sender) {
        subCommandsHashMap.forEach((key, value) -> {
            plugin.getChatUtils().sendMessage(sender, "&a" + value.getSyntax() + " &7- &b" + value.getDescription() + " &8- &6" + value.getCommandPermission());
        });
        plugin.getChatUtils().sendMessage(sender, "&b&l-----&6&l-----&b&l-----&6&l-----&b&l-----&6&l-----&b&l-----&6&l-----");
    }

    public List<String> parsePlayers(String regexCharacter) {
        List<String> tabCompletion = new ArrayList<String>();
        plugin.getServer().getOnlinePlayers().forEach((playerName) -> {
            tabCompletion.add(playerName.getName());
        });
        return tabCompletion;
    }


    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length == 0) {
            helpMessage(sender);
        } else {
            if (aliases.containsKey(args[0])) {
                final Subcommands currentCommand = subCommandsHashMap.get(aliases.get(args[0]));
                if (!(sender instanceof Player) && !currentCommand.doRunFromConsole()) {
                    cantRunFromConsole(sender);
                    return true;
                }
                if (!sender.hasPermission(currentCommand.getCommandPermission())) {
                    noPermission(sender);
                    return true;
                }
                currentCommand.onCommand((Player) sender, args);
            } else {
                unknownCommand(sender);
                return true;
            }
        }
        return true;
    }
}