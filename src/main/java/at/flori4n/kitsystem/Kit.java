package at.flori4n.kitsystem;

import lombok.*;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;


@Data
public class Kit {
    private String name;
    //private PlayerInventory inventory;
    private ItemStack[] invContents;
    private ItemStack[] amorContents;
    private List<Location> locations = new ArrayList<>();


    public Kit (String name){
        this.name = name;
    }
    public void addLocation(Location location){
        locations.add(location);
    }
    private void removeLocation(int index){
        locations.remove(index);
    }
}
