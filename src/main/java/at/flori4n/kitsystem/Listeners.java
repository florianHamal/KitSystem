package at.flori4n.kitsystem;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;


public class Listeners implements Listener {
    KitData kitData = KitData.getInstance();

    @EventHandler
    public void onPlayerInteractEntity(EntityDamageByEntityEvent e) {
        if (!e.getEntity().hasMetadata("flori4n.kit"))return;
        e.setCancelled(true);
        if (!(e.getDamager() instanceof Player)) return;
        Player p = (Player) e.getDamager();
        Kit kit = kitData.getKitByName(e.getEntity().getCustomName());
        kitData.addPlayerToKit(p,kit);
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent e){
        if (e.getRightClicked().hasMetadata("flori4n.kit"))e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        System.out.println("t");
        kitData.getPlayerKit(p).applyKit(p);
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        event.setCancelled(true);
    }
}
