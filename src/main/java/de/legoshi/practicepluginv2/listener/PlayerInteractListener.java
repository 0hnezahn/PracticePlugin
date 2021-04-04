package de.legoshi.practicepluginv2.listener;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.manager.ItemClickManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onItemInHand(PlayerInteractEvent e) {

        Player p = (Player) e.getPlayer();
        Action a = e.getAction();
        ItemClickManager iM = Main.getInstance().itemClickManager;

        if((Action.RIGHT_CLICK_AIR == a || Action.RIGHT_CLICK_BLOCK == a) && iM.isCorrectItem(p)) {

            iM.grayGreenDyeRight(e);
            iM.blazeRodRight(e);
            iM.redDyeRight(e);
            iM.skullForward(e);
            iM.skullBackwards(e);
            iM.obsidianRight(e);
            iM.gunPowderRight(e);

        } else if((Action.LEFT_CLICK_AIR == a || Action.LEFT_CLICK_BLOCK == a) && iM.isCorrectItem(p)) {

            iM.blazeRodLeft(e);
            iM.redDyeLeft(e);
            iM.obsidianLeft(e);
            iM.grayGreenDyeLeft(e);

        }
    }
}
