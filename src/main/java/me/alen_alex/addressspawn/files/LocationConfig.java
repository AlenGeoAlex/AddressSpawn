package me.alen_alex.addressspawn.files;

import de.leonhard.storage.Json;
import me.alen_alex.addressspawn.AddressSpawn;
import me.alen_alex.addressspawn.object.SpawnLocations;

public class LocationConfig {
    private Json locationCache;
    private AddressSpawn plugin;

    public LocationConfig(AddressSpawn plugin) {
        this.plugin = plugin;
    }

    public void init(){
        locationCache = plugin.getFileUtils().createJSONFile("spawns.json","data");
        loadLocationFile();
    }

    public void reloadFile(){

    }

    public Json getLocationCache() {
        return locationCache;
    }

    public void loadLocationFile(){
        for(String names:locationCache.singleLayerKeySet()){
            locationCache.setPathPrefix(names);
            plugin.getPluginManager().getLocationConfigHashMap().put(names,new SpawnLocations(names,locationCache.getInt("x"),locationCache.getInt("y"),locationCache.getInt("z"),locationCache.getFloat("yaw"),locationCache.getFloat("pitch"),locationCache.getString("world")));
        }
        System.out.println(plugin.getPluginManager().getLocationConfigHashMap().size());
    }
}
