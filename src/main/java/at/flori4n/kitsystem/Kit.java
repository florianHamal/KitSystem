package at.flori4n.kitsystem;

import lombok.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Data
public class Kit {
    private String name;
    //private PlayerInventory inventory;
    private ItemStack[] invContents;
    private ItemStack[] armorContents;
    private List<Location> locations = new ArrayList<>();
    private List<ArmorStand> armorStands = new ArrayList<>();


    public Kit (String name){
        this.name = name;
    }
    public void addLocation(Location location){
        locations.add(location);
    }
    private void removeLocation(int index){
        locations.remove(index);
    }
    public void spawnArmorStands(){

        armorStandCleanup();
        for (Location location : locations){
            System.out.println("Spawning armor stands...");
            ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location,EntityType.ARMOR_STAND);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName(name);
            armorStand.setGravity(false);
            armorStand.setArms(true);
            armorStand.setBoots(armorContents[0]);
            armorStand.setLeggings(armorContents[1]);
            armorStand.setChestplate(armorContents[2]);
            armorStand.setHelmet(armorContents[3]);
            armorStand.setItemInHand(invContents[0]);
            armorStand.setMetadata("flori4n.kit",new FixedMetadataValue(KitSystem.getPlugin(),"flori4n.kit"));
            armorStands.add(armorStand);
        }
    }
    public void armorStandCleanup(){
        armorStands.forEach(ArmorStand::remove);
        armorStands.clear();
        for (Location location : locations) {
            //cleanup #2
            location.getWorld()
                    .getNearbyEntities(location, 5, 5, 5).stream()
                    .filter(e -> e.hasMetadata("flori4n.kit")&&e.getName()==name)
                    .collect(Collectors.toList())
                    .forEach(entity ->
                    {
                        ((ArmorStand)entity).setBoots(new ItemStack(Material.AIR));
                        ((ArmorStand)entity).setLeggings(new ItemStack(Material.AIR));
                        ((ArmorStand)entity).setChestplate(new ItemStack(Material.AIR));
                        ((ArmorStand)entity).setHelmet(new ItemStack(Material.AIR));
                        ((ArmorStand)entity).setItemInHand(new ItemStack(Material.AIR));
                        entity.remove();
                    });
        }
    }
    public void applyKit(Player player){
        player.getInventory().setArmorContents(armorContents);
        player.getInventory().setContents(invContents);
        player.updateInventory();
    }

}

