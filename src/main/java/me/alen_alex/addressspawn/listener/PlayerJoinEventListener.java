package me.alen_alex.addressspawn.listener;

import me.alen_alex.addressspawn.AddressSpawn;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventListener implements Listener {

    private AddressSpawn plugin;

    public PlayerJoinEventListener(AddressSpawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        final Player player = event.getPlayer();

        if(player.hasPermission("as.bypass.*"))
            return;

        if(!plugin.getPluginManager().getAddressCache().containsKey(player.getUniqueId()))
            return;

        if(!player.hasPermission("as.bypass."+plugin.getPluginManager().getAddressCache().get(player.getUniqueId())))
            return;

        if(!plugin.getPluginManager().getLocationConfigHashMap().containsKey(plugin.getPluginManager().getAddressCache().get(player.getUniqueId())))
            return;

        plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                plugin.getPluginManager().getLocationConfigHashMap().get(plugin.getPluginManager().getAddressCache().get(player.getUniqueId())).teleport(player);
            }
        },(long) plugin.getConfiguration().getTeleportDelay());

    }
}
