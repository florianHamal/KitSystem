package at.flori4n.kitsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        KitData kitData = KitData.getInstance();
        Player player = (Player) commandSender;
        if (!player.hasPermission("setup"))return false;
        Kit kit;
        try {
            switch (strings[0]) {
                case "addKit":
                    kit = new Kit(strings[1]);
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
                case "select":
                    kit = kitData.getKitByName(strings[1]);
                    player.getInventory().setArmorContents(kit.getArmorContents());
                    player.getInventory().setContents(kit.getInvContents());
                    player.updateInventory();
                    break;
                case "printKits":
                    player.sendMessage(
                            kitData.getKits().stream().map(Kit::getName).collect(Collectors.joining())
                    );
                    break;
                case "removeKit":
                    kitData.removeKit(kitData.getKitByName(strings[1]));
                    break;
                case "updateKit":
                    kit = kitData.getKitByName(strings[1]);
                    kit.setInvContents(player.getInventory().getContents());
                    kit.setArmorContents(player.getInventory().getArmorContents());
                    //kitData.addKit(kit);
                    break;
                default:
                    player.sendMessage("wrong Command");
            }
        }catch (RuntimeException e){
            player.sendMessage(e.getMessage());
        }
        return false;
    }
}
