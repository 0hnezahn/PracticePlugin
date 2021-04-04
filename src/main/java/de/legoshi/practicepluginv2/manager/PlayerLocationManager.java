package de.legoshi.practicepluginv2.manager;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.util.PlayerObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.round;

public class PlayerLocationManager {

    public void playerLocation() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            public void run() {

                getLocation();
                runCommand();


            }

        }, 0, 1);

    }

    public void getLocation() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            PlayerObject po = Main.getInstance().playerObjectManager.playerObject.get(all);

            if (po.getBeforeJump() || po.getAfterJump()) {
                    if (po.getLocationList().size() >= 25) {
                        po.getLocationList().remove(0);
                    }
                po.getLocationList().add(all.getLocation());
                playerOnGround(all);
            }

        }
    }

    public void runCommand() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            PlayerObject po = Main.getInstance().playerObjectManager.playerObject.get(all);

            if (po.getAfterJump()) {

                if (po.getBeforeJump()) {

                    all.sendMessage("Please deactivate jf before");
                    po.setBeforeJump(false);

                }

                doAfterJump(po, all);

            }

            if (po.getBeforeJump()) {

                if (po.getAfterJump()) {

                    all.sendMessage("Please deactivate je before");
                    po.setAfterJump(false);

                }

                doBeforeJump(po, all);

            }
        }
    }

    public void doBeforeJump(PlayerObject po, Player all) {
        if (po.getBeforeJump()) {

            if (!(po.getHasJumped()) && !(all.isOnGround())) {

                Location l = po.getLocationList().get(po.getLocationList().size() - 2);
                float facing = l.getYaw();
                boolean isDiagonal = po.getDiagonal();
                int jumps = po.getJumps();

                if (!(isDiagonal) && jumps == 0 && (!po.getExtraCheck())) {

                    facingStraightCheckBeforeJump(facing, all, l);
                    po.setJumps(po.getFinalJumps());
                    po.setExtraCheck(true);

                } else if (isDiagonal && jumps == 0 && (!po.getExtraCheck())) {

                    facingDiagonalCheckBeforeJump(all, l);
                    po.setJumps(po.getFinalJumps());
                    po.setExtraCheck(true);

                } else {

                    po.setJumps(jumps - 1);

                }
                po.setHasJumped(true);

            } else if (all.isOnGround()) {

                po.setHasJumped(false);

            }
        }
    }

    public void doAfterJump(PlayerObject po, Player all) {
       /* Location l = po.getLocationList().get(po.getLocationList().size() - 3);

        float facing;
        int jumps;
        boolean isDiagonal;

        //all.sendMessage("" + po.getLocationList().get(po.getLocationList().size() - 1).getY());

        if (!(po.getbetterIsOnGround())) {

            po.setHasJumped(true);

        }

        all.sendMessage("Tier: ");

        if (po.getHasJumped()) {

            if(tierCalc(po.getTier()) == 0) {

                if (po.getbetterIsOnGround()) {

                    facing = setFacing(po, l);
                    isDiagonal = po.getDiagonal();
                    jumps = po.getJumps();

                    //all.sendMessage("" + jumps);

                    if (!(isDiagonal) && jumps == 0) {

                        facingStraightCheck(facing, all, l);
                        po.setJumps(po.getFinalJumps());
                        po.setHasJumped(false);

                    } else if (isDiagonal && jumps == 0) {

                        facingDiagonalCheck(all, l);
                        po.setJumps(po.getFinalJumps());
                        po.setHasJumped(false);

                    } else {

                        po.setJumps(jumps - 1);
                        po.setHasJumped(false);

                    }

                }

            } else {
                po.setFinalTier(po.getFinalTier() - 1);

                if (po.getFinalTier() == 0) {

                    facing = setFacing(po, l);
                    isDiagonal = po.getDiagonal();
                    jumps = po.getJumps();

                    //all.sendMessage("" + jumps);

                    if (!(isDiagonal) && jumps == 0) {

                        facingStraightCheck(facing, all, l);
                        po.setJumps(po.getFinalJumps());
                        po.setHasJumped(false);

                    } else if (isDiagonal && jumps == 0) {

                        facingDiagonalCheck(all, l);
                        po.setJumps(po.getFinalJumps());
                        po.setHasJumped(false);

                    } else {

                        po.setJumps(jumps - 1);
                        po.setHasJumped(false);

                    }

                }

            }

        } */

        //all.sendMessage("" + isPlayerCompletelyOnGround(all));

        if (!(po.getHasJumped()) && !(all.isOnGround())) {

            //damit es nicht dauerhaft loopt ausserhalb vom scheduler
            po.setHasJumped(true);
            int tier = tierCalc(po.getTier()) - 11;
            int jumps1;
            int delay1 = 1;
            jumps1 = po.getJumps();

            if(jumps1 == 2) {
                delay1 = 2;
            }

            int finalDelay = delay1;
            new BukkitRunnable() {

                @Override

                public void run() {

                    Location l;
                    float facing;
                    boolean isDiagonal;
                    int jumps;

                    l = po.getLocationList().get(po.getLocationList().size() - finalDelay);
                    facing = setFacing(po, l);
                    isDiagonal = po.getDiagonal();
                    jumps = po.getJumps();


                    if (!(isDiagonal) && jumps == 0 && (!po.getExtraCheck())) {

                        facingStraightCheckAfterJump(facing, all, l);
                        po.setJumps(po.getFinalJumps());
                        po.setExtraCheck(true);

                    } else if (isDiagonal && jumps == 0 && (!po.getExtraCheck())) {

                        facingDiagonalCheckAfterJump(all, l);
                        po.setJumps(po.getFinalJumps());
                        po.setExtraCheck(true);

                    } else {

                        po.setJumps(jumps - 1);

                    }

                }

            }.runTaskLater(Main.getInstance(), (11 + tier));

        } else if (all.isOnGround()) {

            po.setHasJumped(false);

        }

    }

    public void facingStraightCheckAfterJump(double facing, Player all, Location l) {

        if ((facing < 0.0 && facing > -45.0) || (facing < -315.0 && facing > -360.0)
            || (facing > 315.0 && facing < 360.0) || (facing > 0.0 && facing < 45.0)) {

            all.sendMessage("Under-/Overjumped by: " + String.format("%.5f", l.getZ()));

        } else if ((facing > 45.0 && facing < 135.0) || (facing > -315.0 && facing < -225.0)) {

            all.sendMessage("Under-/Overjumped by: " + String.format("%.5f", l.getX()));

        } else if ((facing > -225.0 && facing < -135.0) || (facing > 135.0 && facing < 225.0)) {

            all.sendMessage("Under-/Overjumped by: " + String.format("%.5f", l.getZ()));

        } else if ((facing > 225.0 && facing < 315.0) || (facing > -135.0 && facing < -45.0)) {

            all.sendMessage("Under-/Overjumped by: " + String.format("%.5f", l.getX()));
        }
    }

    public void facingStraightCheckBeforeJump(double facing, Player all, Location l) {

        if ((facing < 0.0 && facing > -45.0) || (facing < -315.0 && facing > -360.0)
            || (facing > 315.0 && facing < 360.0) || (facing > 0.0 && facing < 45.0)) {

            all.sendMessage("Potential forward: " + String.format("%.5f", l.getZ()));

        } else if ((facing > 45.0 && facing < 135.0) || (facing > -315.0 && facing < -225.0)) {

            all.sendMessage("Potential forward: " + String.format("%.5f", l.getX()));

        } else if ((facing > -225.0 && facing < -135.0) || (facing > 135.0 && facing < 225.0)) {

            all.sendMessage("Potential forward: " + String.format("%.5f", l.getZ()));

        } else if ((facing > 225.0 && facing < 315.0) || (facing > -135.0 && facing < -45.0)) {

            all.sendMessage("Potential forward: " + String.format("%.5f", l.getX()));
        }

    }

    public float setFacing(PlayerObject po, Location l) {
        float facing;

        if (po.getDirection().equals("-")) {

            facing = l.getYaw();

        } else {

            facing = Float.parseFloat(po.getDirection());

        }

        return facing;
    }

    public void facingDiagonalCheckAfterJump(Player all, Location l) {

        all.sendMessage("Under-/Overjumped by X-Coord: " + String.format("%.5f", l.getX()));
        all.sendMessage("Under-/Overjumped by Z-Coord: " + String.format("%.5f", l.getZ()));

    }

    public void facingDiagonalCheckBeforeJump(Player all, Location l) {

        all.sendMessage("Potential forward X-Coord: " + String.format("%.5f", l.getX()));
        all.sendMessage("Potential forward Z-Coord: " + String.format("%.5f", l.getZ()));

    }

    public int tierCalc(double height) {

        double sum = 1.24919;
        double n = 0.00301;
        int i;

        for (i = 4; sum >= height; i++) {

            sum = sum + n;
            n = (n - 0.08) * 0.98;

        }

        return i;
    }

    public void playerOnGround(Player p) {

        PlayerObject po = Main.getInstance().playerObjectManager.getPlayerObject().get(p);

        if ((po.getLocationList().get(po.getLocationList().size() - 1).getY() - po.getLocationList().get(po.getLocationList().size() - 2).getY() == 0)
            || Math.abs((po.getLocationList().get(po.getLocationList().size() - 1).getY()
            - po.getLocationList().get(po.getLocationList().size() - 2).getY())) == 0.42) {

            po.setBetterIsOnGrounds(true);

        } else {

            po.setBetterIsOnGrounds(false);

        }

    }

    public static boolean isPlayerCompletelyOnGround(Player player) {
        double check = (player.getPlayer().getLocation().getY() - player.getPlayer().getLocation().getBlockY());
        //player.sendMessage("check: " + player.getPlayer().getLocation().getBlockY());
        return check % getRelativeBlockHeight(player.getLocation().getBlock().getType()) == 0;
    }

    public static Double getRelativeBlockHeight(Material material) {
        switch (material) {
            case ACACIA_FENCE:
            case ACACIA_FENCE_GATE:
            case BIRCH_FENCE:
            case BIRCH_FENCE_GATE:
            case DARK_OAK_FENCE:
            case DARK_OAK_FENCE_GATE:
            case FENCE:
            case FENCE_GATE:
            case IRON_FENCE:
            case JUNGLE_FENCE:
            case JUNGLE_FENCE_GATE:
            case NETHER_FENCE:
            case SPRUCE_FENCE:
            case SPRUCE_FENCE_GATE:
            case COBBLE_WALL:
                return 0.5;
            case GRASS_PATH:
            case SOIL:
            case CACTUS:
                return 0.9375;
            case SOUL_SAND:
            case CHEST:
            case ENDER_CHEST:
            case TRAPPED_CHEST:
                return 0.875;
            case CHORUS_PLANT:
                return 0.8125;
            case ENCHANTMENT_TABLE:
                return 0.75;
            case BED_BLOCK:
                return 0.5625;
            case SKULL:
                return 0.25;
            case WATER_LILY:
                return 0.09375;
            default:
                return 0.0625;
        }
    }

}
