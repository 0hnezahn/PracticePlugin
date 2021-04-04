package de.legoshi.practicepluginv2.listener;

import de.legoshi.practicepluginv2.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        Main.getInstance().inventoryObjectManager.joinPlayer(player);
        Main.getInstance().pathPlayerManager.joinPlayer(player);
        Main.getInstance().checkPointManager.joinPlayer(player);
        Main.getInstance().playerObjectManager.joinPlayer(player);
        Main.getInstance().inventoryObject.setBuild(player);

    }
}
