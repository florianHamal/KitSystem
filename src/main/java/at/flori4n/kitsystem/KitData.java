package at.flori4n.kitsystem;


import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class KitData {
    private static KitData instance;
    @Getter
    private List<Kit> kits = new ArrayList<>();
    private Map<Player,Kit> playerKits = new HashMap<>();


    private KitData(){
        readConf();
    }

    private void readConf(){
        FileConfiguration config = KitSystem.getPlugin().getConfig();
        ConfigurationSection section = config.getConfigurationSection("kits");
        if(section==null)return;
        for (String s:section.getKeys(false)){
            ConfigurationSection kitSelection = section.getConfigurationSection(s);
            Kit kit = new Kit(kitSelection.getName());
            ConfigurationSection itemSelection = kitSelection.getConfigurationSection("items");
            ItemStack[] items = new ItemStack[itemSelection.getKeys(false).size()];
            System.out.println(itemSelection.getKeys(false).size());
            for (int i = 0; i< items.length;i++){
                items[i] = itemSelection.getItemStack("item"+i);
            }
            kit.setInvContents(items);
            ConfigurationSection armorSection = kitSelection.getConfigurationSection("armor");
            ItemStack[] armor = new ItemStack[armorSection.getKeys(false).size()];
            for (int i = 0; i< armor.length;i++){
                armor[i] = armorSection.getItemStack("armor"+i);
            }
            kit.setArmorContents(armor);
            List<Location> locations = new ArrayList<>();
            locations = (List<Location>) kitSelection.getList("locations");
            kit.setLocations(locations);
            kits.add(kit);
        }
    }
    public void writeConf(){
        FileConfiguration config = KitSystem.getPlugin().getConfig();
        ConfigurationSection section = config.createSection("kits");
        for (Kit kit : kits){
            ConfigurationSection kitSection = section.createSection(kit.getName());
            kitSection.set("locations",kit.getLocations());
            System.out.println(kit.getInvContents().length);
            ConfigurationSection itemSection= kitSection.createSection("items");
            for (int i = 0; i<kit.getInvContents().length;i++){
                System.out.println("loopTest");
                if(kit.getInvContents()[i]!=null){
                    itemSection.set("item"+i,kit.getInvContents()[i]);
                }else {
                    itemSection.set("item"+i,"-");
                }
            }
            ConfigurationSection armorSection = kitSection.createSection("armor");
            for (int i = 0; i<kit.getArmorContents().length;i++){
                armorSection.set("armor"+i,kit.getArmorContents()[i]);
            }
        }
        KitSystem.getPlugin().saveConfig();
    }

    public void addKit(Kit kit){
       if (kits.stream().anyMatch(k->k.getName().equals(kit.getName())))throw new RuntimeException("kit already exists");
       kits.add(kit);
    }
    public Kit getKitByName(String name){
        System.out.println("--> searching for kit " + name);
        kits.forEach(k-> System.out.println(k.getName()));
        for (Kit k : kits){
            if (k.getName().equals(name))
            {
                return k;
            }
        }
        throw new RuntimeException("no such kit");
    }

    public void removeKit(Kit kit){
        kits.remove(kit);
    }

    public static KitData getInstance(){
        if (instance == null)instance = new KitData();
        return instance;
    }

    public void addPlayerToKit(Player player, Kit kit){
        playerKits.put(player,kit);
        kit.applyKit(player);
    }
    public Kit getPlayerKit(Player player){
        return playerKits.get(player);
    }
}
