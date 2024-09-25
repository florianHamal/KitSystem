package at.flori4n.kitsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class Kit {

    private String kitName;

    private List<ItemStack> items;

    private List<Location> kitLocations = new ArrayList<>();


    public void addLocation(Location location) {
        kitLocations.add(location);
    }

}
