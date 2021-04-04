package de.legoshi.practicepluginv2.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerObject {

    Player player;
    ArrayList<Location> locationList;
    String direction;
    int jumps;
    int finalJumps;
    double tierHeight;
    int finalTier;
    int finalTier2;
    boolean strafe45;
    boolean extraCheck;
    boolean betterIsOnGround;
    boolean onGroundCheck;
    boolean diagonal;
    boolean hasjumped;
    boolean beforeJump;
    boolean afterJump;

    public PlayerObject() {}

    public PlayerObject(Player player) {
        this.locationList = new ArrayList<Location>();
        this.betterIsOnGround = true;
        this.player = player;
        this.hasjumped = false;
        this.beforeJump = false;
        this.extraCheck = false;
        this.onGroundCheck = true;
        this.diagonal = false;
        this.strafe45 = false;
        this.jumps = 0;
        this.finalTier = 0;
        this.finalTier2 = 0;
        this.finalJumps = 0;
        this.tierHeight = 0;
        this.direction = "-";
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<Location> getLocationList() {
        return this.locationList;
    }

    public boolean getHasJumped() {
        return this.hasjumped;
    }

    public boolean getBeforeJump() {
        return this.beforeJump;
    }

    public boolean getExtraCheck() {
        return this.extraCheck;
    }

    public void setExtraCheck(boolean extraCheck) { this.extraCheck = extraCheck; }

    public void setBeforeJump(boolean beforeJump) {
        this.beforeJump = beforeJump;
    }

    public boolean getAfterJump() {
        return this.afterJump;
    }

    public void setAfterJump(boolean afterJump) {
        this.afterJump = afterJump;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getJumps() {
        return this.jumps;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public int getFinalTier() {
        return this.finalTier;
    }

    public void setFinalTier(int finalTier) {
        this.finalTier = finalTier;
    }

    public int getFinalTier2() {
        return this.finalTier2;
    }

    public void setFinalTier2(int finalTier2) {
        this.finalTier2 = finalTier2;
    }

    public boolean getbetterIsOnGround() {
        return this.betterIsOnGround;
    }

    public void setBetterIsOnGrounds(boolean betterIsOnGround) {
        this.betterIsOnGround = betterIsOnGround;
    }

    public int getFinalJumps() {
        return this.finalJumps;
    }

    public void setFinalJumps(int finalJumps) {
        this.finalJumps = finalJumps;
    }

    public boolean getDiagonal() {
        return this.diagonal;
    }

    public void setDiagonal(boolean diagonal) {
        this.diagonal = diagonal;
    }

    public boolean getStrafe45() {
        return this.strafe45;
    }

    public void setStrafe45(boolean strafe45) {
        this.strafe45 = strafe45;
    }

    public boolean getOnGroundCheck() {
        return this.onGroundCheck;
    }

    public void setOnGroundCheck(boolean onGroundCheck) {
        this.onGroundCheck = onGroundCheck;
    }

    public double getTier() {
        return this.tierHeight;
    }

    public void setTier(double tierHeight) {
        this.tierHeight = tierHeight;
    }

    public void setHasJumped(boolean hasjumped) {
        this.hasjumped = hasjumped;
    }

}
