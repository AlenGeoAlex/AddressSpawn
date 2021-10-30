package me.alen_alex.addressspawn.listener;

import me.alen_alex.addressspawn.AddressSpawn;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginEventListener implements Listener {

    private AddressSpawn plugin;

    public PlayerLoginEventListener(AddressSpawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event){
        final Player player = event.getPlayer();
        if(plugin.getPluginManager().getAddressCache().containsKey(player.getUniqueId()))
            plugin.getPluginManager().getAddressCache().remove(player.getUniqueId());

        String host = event.getHostname().split(":")[0].toLowerCase();
        if (host.endsWith(".")) {
            host = host.substring(0, host.length() - 1);
        }
        String[] args = host.split("\\.");
        final String subDomain = args[0];
        plugin.getPluginManager().getAddressCache().put(event.getPlayer().getUniqueId(),subDomain);
        plugin.getLogger().info(player.getName()+" joined through the address "+event.getHostname());
    }

}
