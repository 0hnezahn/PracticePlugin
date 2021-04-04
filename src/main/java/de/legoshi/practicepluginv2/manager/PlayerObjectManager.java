package de.legoshi.practicepluginv2.manager;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.util.PlayerObject;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerObjectManager {

    HashMap<Player, PlayerObject> playerObject;

    public PlayerObjectManager() {
        this.playerObject = new HashMap<Player, PlayerObject>();
    }

    public HashMap<Player, PlayerObject> getPlayerObject() {
        return this.playerObject;
    }

    public void joinPlayer(Player player) {
        PlayerObjectManager playerObjectManager = Main.getInstance().playerObjectManager;
        playerObjectManager.playerObject.put(player, new PlayerObject(player));
    }

}
