package at.flori4n.kitsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        KitData kitData = KitData.getInstance();
        Player  player = (Player) commandSender;
        switch (strings[0]){
            case "addKit":
                kitData.addKit(
                        new Kit(strings[1],player.getInventory())
                );
                break;
            case "addKitLocation":
                kitData.getKitByName(strings[1]).addLocation(player.getLocation());
                break;
            case "save":
                kitData.writeConf();
                break;
            default:
                player.sendMessage("wrong Command");
        }
        return false;
    }
}
