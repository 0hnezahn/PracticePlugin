package de.legoshi.practicepluginv2.listener;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.manager.ItemClickManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBlockDestroyListener implements Listener {

    @EventHandler
    public void onDestroy(BlockBreakEvent e) {
        ItemClickManager iM = Main.getInstance().itemClickManager;

        if(iM.isCorrectItem(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
