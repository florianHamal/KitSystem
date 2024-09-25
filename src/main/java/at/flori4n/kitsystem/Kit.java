package at.flori4n.kitsystem;

import lombok.*;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class Kit {
    private String name;
    //private PlayerInventory inventory;
    private ItemStack[] invContents;
    private ItemStack[] armorContents;
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

