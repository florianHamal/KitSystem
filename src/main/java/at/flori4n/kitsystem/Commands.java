package at.flori4n.kitsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        KitData kitData = KitData.getInstance();
        Player  player = (Player) commandSender;
        switch (strings[0]){
            case "addKit":
                Kit kit = new Kit(strings[1]);
                kit.setInvContents(player.getInventory().getContents());
                kit.setArmorContents(player.getInventory().getArmorContents());
                kitData.addKit(kit);

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
