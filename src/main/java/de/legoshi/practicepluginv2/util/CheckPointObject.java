package de.legoshi.practicepluginv2.util;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.manager.CheckPointManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CheckPointObject {

    Player player;
    Location location;

    public CheckPointObject(Player player) {
        this.player = player;
        this.location = null;
    }

    public CheckPointObject() {

    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void joinPlayer(Player p) {
        CheckPointManager checkPointManager = Main.getInstance().checkPointManager;

    }
}
