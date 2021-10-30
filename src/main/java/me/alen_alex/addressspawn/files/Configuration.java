package me.alen_alex.addressspawn.files;

import de.leonhard.storage.Config;
import me.alen_alex.addressspawn.AddressSpawn;

public class Configuration {

    private AddressSpawn plugin;
    private Config pluginConfig;
    private LocationConfig locationConfig;
    //
    private int secToClearCache,teleportDelay;
    private boolean teleportOnFirstJoin;
    public Configuration(AddressSpawn plugin) {
        this.plugin = plugin;
        init();
    }

    private void init(){
        pluginConfig = plugin.getFileUtils().createConfiguration();
        loadConfig();

    }

    public void initOthers(){
        locationConfig = new LocationConfig(this.plugin);
        locationConfig.init();
    }

    public void reload(){
        init();
        locationConfig.init();
    }

    private void loadConfig(){
        secToClearCache = pluginConfig.getInt("clear-login-cache-in-sec");
        teleportOnFirstJoin = pluginConfig.getBoolean("teleport-only-on-first-join");
        teleportDelay = pluginConfig.getInt("teleport-after-delay-ticks");
    }

    public int getSecToClearCache() {
        return secToClearCache;
    }

    public boolean isTeleportOnFirstJoin() {
        return teleportOnFirstJoin;
    }

    public LocationConfig getLocationConfig() {
        return locationConfig;
    }

    public long getSecToClearCacheInLong(){
        return secToClearCache*1000;
    }

    public int getTeleportDelay() {
        return teleportDelay;
    }
}
