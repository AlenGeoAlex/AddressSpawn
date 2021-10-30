package me.alen_alex.addressspawn;

import me.alen_alex.addressspawn.files.LocationConfig;
import me.alen_alex.addressspawn.map.SelfExpiringHashMap;
import me.alen_alex.addressspawn.object.SpawnLocations;

import java.util.HashMap;
import java.util.UUID;

public class PluginManager {

    private AddressSpawn plugin;
    private SelfExpiringHashMap<UUID,String> addressCache;
    private HashMap<String, SpawnLocations> locationConfigHashMap;

    public PluginManager(AddressSpawn plugin) {
        this.plugin = plugin;
        addressCache = new SelfExpiringHashMap<UUID,String>(plugin.getConfiguration().getSecToClearCacheInLong());
        locationConfigHashMap = new HashMap<String,SpawnLocations>();
    }

    public SelfExpiringHashMap<UUID, String> getAddressCache() {
        return addressCache;
    }

    public HashMap<String, SpawnLocations> getLocationConfigHashMap() {
        return locationConfigHashMap;
    }
}
