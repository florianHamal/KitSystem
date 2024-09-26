package at.flori4n.kitsystem;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class KitSystem extends JavaPlugin {

    public static KitSystem instance;
    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new Listeners(),this);
        getCommand("kits").setExecutor(new Commands());
        KitData.getInstance().getKits().forEach(Kit::spawnArmorStands);

    }

    @Override
    public void onDisable() {
        KitData.getInstance().getKits().forEach(Kit::armorStandCleanup);
    }
    public static KitSystem getPlugin(){
        return instance;
    }


}
