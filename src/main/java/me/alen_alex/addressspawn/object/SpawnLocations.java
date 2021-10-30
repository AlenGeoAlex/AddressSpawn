package me.alen_alex.addressspawn.object;

import com.google.common.base.Objects;
import de.leonhard.storage.Json;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpawnLocations{

    private String name;
    private Location location;

    public SpawnLocations(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public SpawnLocations(String name,int x,int y,int z,float yaw,float pitch,String world){
        final World tempWorld = Bukkit.getWorld(world);
        if(tempWorld == null){
            Bukkit.getLogger().warning("A world with the name "+world+" does not exist, Skipping location for "+name);
            return;
        }
        this.location = new Location(tempWorld, x,y ,z ,yaw ,pitch );
    }

    public void teleport(Player player){
        player.teleport(location);
    }

    public void save(Json jsonFile){
        jsonFile.set(name+".x",(int) this.location.getX());
        jsonFile.set(name+".y",(int)this.location.getY());
        jsonFile.set(name+".z",(int) this.location.getZ());
        jsonFile.set(name+".yaw",(int) this.location.getYaw());
        jsonFile.set(name+".pitch",this.location.getPitch());
        jsonFile.set(name+".world",this.location.getWorld().getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpawnLocations that = (SpawnLocations) o;
        return Objects.equal(name, that.name) && Objects.equal(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, location);
    }
}


