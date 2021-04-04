package de.legoshi.practicepluginv2.manager;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.util.CheckPointObject;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CheckPointManager {

    HashMap<Player, CheckPointObject> checkpoint;

    public CheckPointManager() {
        this.checkpoint = new HashMap<Player, CheckPointObject>();
    }

    public HashMap<Player, CheckPointObject> getCheckpoint() {
        return this.checkpoint;
    }

    public void joinPlayer(Player player) {
        CheckPointManager checkPointManager = Main.getInstance().checkPointManager;
        checkPointManager.getCheckpoint().put(player, new CheckPointObject(player));
    }
}
