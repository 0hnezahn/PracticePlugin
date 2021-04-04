package de.legoshi.practicepluginv2.listener;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.manager.ItemClickManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerBlockPlaceListener implements Listener {

    @EventHandler
    private void onPlace(BlockPlaceEvent e) {
        ItemClickManager iM = Main.getInstance().itemClickManager;

        if(iM.isCorrectItem(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
