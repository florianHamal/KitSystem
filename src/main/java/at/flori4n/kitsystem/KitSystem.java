package at.flori4n.kitsystem;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class KitSystem extends JavaPlugin {

    public static KitSystem instance;
    @Override
    public void onEnable() {
        instance = this;
        getCommand("kits").setExecutor(new Commands());
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
