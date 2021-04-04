package de.legoshi.practicepluginv2.manager;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.util.LocArmObject;
import de.legoshi.practicepluginv2.util.PathPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PathPlayerManager {

    private HashMap<Player, PathPlayer> pathPlayers;

    public PathPlayerManager() {
        this.pathPlayers = new HashMap<Player, PathPlayer>();
    }

    public HashMap<Player, PathPlayer> getPathPlayers() {
        return this.pathPlayers;
    }

    public void clearPlayerPath(Player p, int i) {
        PathPlayer pp = Main.getInstance().pathPlayerManager.getPathPlayers().get(p);

        if(!(pp.getPath().isEmpty())) {
            while(!(pp.getPath().get(i-1).isEmpty())) {
                if(!(pp.getPath().get(i-1).get(0).getEntity() == null)) {
                    pp.getPath().get(i-1).get(0).getEntity().remove();
                }
                pp.getPath().get(i-1).remove(0);
            }
        }
    }

    public void joinPlayer(Player player) {

        PathPlayerManager ppm = Main.getInstance().pathPlayerManager;

        ArrayList<LocArmObject> loaList1 = new ArrayList<LocArmObject>();
        ArrayList<LocArmObject> loaList2 = new ArrayList<LocArmObject>();
        ArrayList<LocArmObject> loaList3 = new ArrayList<LocArmObject>();

        ppm.getPathPlayers().put(player, new PathPlayer(player));
        ppm.getPathPlayers().get(player).getPath().add(loaList1);
        ppm.getPathPlayers().get(player).getPath().add(loaList2);
        ppm.getPathPlayers().get(player).getPath().add(loaList3);

    }

    public void leavePlayer(Player p) {
        PathPlayerManager ppm = Main.getInstance().pathPlayerManager;

        ppm.clearPlayerPath(p, 1);
        ppm.clearPlayerPath(p, 2);
        ppm.clearPlayerPath(p, 3);

        if(!(ppm.getPathPlayers().isEmpty())) {
            ppm.getPathPlayers().remove(p);
        }

    }
}
