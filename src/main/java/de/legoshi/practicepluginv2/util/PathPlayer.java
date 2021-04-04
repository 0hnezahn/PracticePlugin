package de.legoshi.practicepluginv2.util;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PathPlayer {
    private Player player;
    private ArrayList<ArrayList<LocArmObject>> path;
    private double movement;

    public PathPlayer(Player player) {
        this.player = player;
        this.path = new ArrayList<ArrayList<LocArmObject>>();
        this.movement = 0.100;
    }

    public PathPlayer() {
        this.movement = 0.100;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setMovement(double movement) {
        this.movement = movement;
    }

    public double getMovement() { return this.movement; }

    public ArrayList<ArrayList<LocArmObject>> getPath() {
        return this.path;
    }

}
