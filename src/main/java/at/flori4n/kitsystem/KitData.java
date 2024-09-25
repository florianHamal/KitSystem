package at.flori4n.kitsystem;


import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

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
        ConfigurationSection s = config.getConfigurationSection("kits");
    }
    private void writeConf(){
        FileConfiguration config = KitSystem.getPlugin().getConfig();
        ConfigurationSection kitSection = config.createSection("kits");
        for (Kit kit : kits){
            kitSection.set("name",kit.getKitName());
            kitSection.set("locations",kit.getKitLocations());
            kitSection.set("items",kit.getItems());
        }
    }



    private static KitData getInstance(){
        if (instance == null)instance = new KitData();
        return instance;
    }

}
