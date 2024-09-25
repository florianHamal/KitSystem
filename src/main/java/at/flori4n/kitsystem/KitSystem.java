package at.flori4n.kitsystem;

import org.bukkit.plugin.java.JavaPlugin;

public final class KitSystem extends JavaPlugin {

    public static KitSystem instance;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static KitSystem getPlugin(){
        return instance;
    }


}
