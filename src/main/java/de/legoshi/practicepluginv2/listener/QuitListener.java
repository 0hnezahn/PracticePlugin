package de.legoshi.practicepluginv2.listener;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.manager.CheckPointManager;
import de.legoshi.practicepluginv2.manager.InventoryObjectManager;
import de.legoshi.practicepluginv2.manager.PathPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        PathPlayerManager ppm = Main.getInstance().pathPlayerManager;
        CheckPointManager cpo = Main.getInstance().checkPointManager;
        InventoryObjectManager iom = Main.getInstance().inventoryObjectManager;

        Player p = event.getPlayer();

        ppm.leavePlayer(p);

        iom.getInventoryObjectManager().remove(p);
        cpo.getCheckpoint().remove(p);
    }
}
