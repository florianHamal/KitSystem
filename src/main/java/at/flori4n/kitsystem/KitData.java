package at.flori4n.kitsystem;


import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KitData {
    private static KitData instance;

    private List<Kit> kits = new ArrayList<>();


    private KitData(){

    }

    private void readConf(){
        FileConfiguration config = KitSystem.getPlugin().getConfig();
        ConfigurationSection kitsSelection = config.getConfigurationSection("kits");
        for (String s:kitsSelection.getKeys(false)){
            ConfigurationSection kitSelection = kitsSelection.getConfigurationSection(s);
            Kit kit = new Kit(kitSelection.getName());
            //kit.setLocations((List<Location>) kitSelection.getList("locations"));
            //Bukkit.createInventory(null,kit.getName());
        }


    }
    public void writeConf(){
        FileConfiguration config = KitSystem.getPlugin().getConfig();
        ConfigurationSection kitSection = config.createSection("kits");
        for (Kit kit : kits){
            kitSection.set("name",kit.getName());
            kitSection.set("locations",kit.getLocations());
            kitSection.set("items",kit.getInvContents());
            kitSection.set("amor",kit.getAmorContents());

        }
        KitSystem.getPlugin().saveConfig();
    }

    public void addKit(Kit kit){
        kits.add(kit);
    }
    public Kit getKitByName(String name){
        return kits.stream().filter(k -> k.getName().equals(name)).findFirst().get();
    }

    public static KitData getInstance(){
        if (instance == null)instance = new KitData();
        return instance;
    }

}
