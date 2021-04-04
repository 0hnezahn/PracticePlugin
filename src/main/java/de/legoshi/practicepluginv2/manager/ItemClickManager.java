package de.legoshi.practicepluginv2.manager;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.util.CheckPointObject;
import de.legoshi.practicepluginv2.util.PathPlayer;
import de.legoshi.practicepluginv2.util.PlayerObject;
import org.apache.commons.lang3.Range;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Objects;

public class ItemClickManager {

    private ItemStack red = new ItemStack(Material.INK_SACK, 1, (short) 1);
    private ItemStack green = new ItemStack(Material.INK_SACK, 1, (short) 2);
    private ItemStack gray = new ItemStack(Material.INK_SACK, 1, (short) 8);

    private MaterialData redData = red.getData();
    private MaterialData greenData = green.getData();
    private MaterialData grayData = gray.getData();

    public void skullForward(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        double movement = Main.getInstance().pathPlayer.getMovement();
        float facing = p.getLocation().getYaw();
        Location l = p.getLocation();

        if (isCorrectItem(p)) {
            if (getItemInHandMaterial(p).equals(Material.SKULL_ITEM)) {
                if (e.getItem().getItemMeta().getDisplayName().equals("§lForward")) {
                    if ((facing < 0 && facing > -45) || (facing < -315 && facing > -360)
                        || (facing > 315 && facing < 360) || (facing > 0 && facing < 45)) {
                        l.setZ((movement + l.getZ()));
                        p.teleport(l);
                    } else if ((facing > 45 && facing < 135) || (facing > -315 && facing < -225)) {
                        l.setX(l.getX() - movement);
                        p.teleport(l);
                    } else if ((facing > -225 && facing < -135) || (facing > 135 && facing < 225)) {
                        l.setZ(l.getZ() - movement);
                        p.teleport(l);
                    } else if ((facing > 225 && facing < 315) || (facing > -135 && facing < -45)) {
                        l.setX(movement + l.getX());
                        p.teleport(l);
                    }
                }
            }
        }
    }

    public void skullBackwards(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        double movement = Main.getInstance().pathPlayer.getMovement();
        float facing = p.getLocation().getYaw();
        Location l = p.getLocation();

        if (isCorrectItem(p)) {
            if (getItemInHandMaterial(p).equals(Material.SKULL_ITEM)) {
                if (e.getItem().getItemMeta().getDisplayName().equals("§lBackwards")) {
                    if ((facing < 0 && facing > -45) || (facing < -315 && facing > -360)
                        || (facing > 315 && facing < 360) || (facing > 0 && facing < 45)) {
                        l.setZ((l.getZ() - movement));
                        p.teleport(l);
                    } else if ((facing > 45 && facing < 135) || (facing > -315 && facing < -225)) {
                        l.setX(l.getX() + movement);
                        p.teleport(l);
                    } else if ((facing > -225 && facing < -135) || (facing > 135 && facing < 225)) {
                        l.setZ(l.getZ() + movement);
                        p.teleport(l);
                    } else if ((facing > 225 && facing < 315) || (facing > -135 && facing < -45)) {
                        l.setX(l.getX() - movement);
                        p.teleport(l);
                    }
                }
            }
        }
    }

    public void obsidianRight(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        PathPlayer pp = Main.getInstance().pathPlayer;

        ItemStack i = getItemInHandItemStack(p);
        ItemMeta m = i.getItemMeta();

        String str;

        if (isCorrectItem(p) && getItemInHandMaterial(p).equals(Material.OBSIDIAN)) {
            if (pp.getMovement() > 0.02) {
                pp.setMovement((pp.getMovement() - 0.01));

            } else {
                pp.setMovement(0.001);
            }
            str = String.format("%.3f", pp.getMovement());
            m.setDisplayName("§l" + str);
            i.setItemMeta(m);
            p.updateInventory();

        }
    }

    public void obsidianLeft(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        PathPlayer pp = Main.getInstance().pathPlayer;

        ItemStack i = getItemInHandItemStack(p);
        ItemMeta m = i.getItemMeta();

        String str;

        if (isCorrectItem(p) && getItemInHandMaterial(p).equals(Material.OBSIDIAN)) {

            if (pp.getMovement() < 0.01) {
                pp.setMovement(0.01);
            } else {
                pp.setMovement((pp.getMovement() + 0.01));
            }

            str = String.format("%.3f", pp.getMovement());
            m.setDisplayName("§l" + str);
            i.setItemMeta(m);
            p.updateInventory();

        }
    }

    public void redDyeRight(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        if ((getItemInHandData(p).equals(redData))) {
            CheckPointObject cpo = Main.getInstance().checkPointManager.getCheckpoint().get(p);
            PlayerObjectManager pom = Main.getInstance().playerObjectManager;
            PlayerObject po = pom.playerObject.get(p);

            e.setCancelled(true);

            if (cpo.getLocation() != null) {
                Location l = cpo.getLocation();
                //für frontjump command
                po.setJumps(po.getFinalJumps());
                po.setExtraCheck(false);

                p.teleport(l);
            } else {
                p.sendMessage("No Checkpoint set!");
            }
        }
    }

    public void redDyeLeft(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        if(checkPlayerPos(p)) {

            if ((getItemInHandData(p).equals(redData))) {
                CheckPointObject cpo = Main.getInstance().checkPointManager.getCheckpoint().get(p);
                cpo.setLocation(p.getLocation());
                p.sendMessage("Checkpoint set!");
            }

        }

        else {

            p.sendMessage("Wrong Position");
            p.sendMessage("X: " + p.getLocation().getX());
            p.sendMessage("Z: " + p.getLocation().getZ());

        }
    }

    private boolean checkPlayerPos(Player p) {

        double xPos = Math.abs(p.getLocation().getX());
        double zPos = Math.abs(p.getLocation().getZ());

        double widePos = 0.0001;
        double wideNeg = 0.0001;

        //saves only floatingpoint numbers into x and z
        double x = xPos - (int) Math.floor(xPos);
        double z = zPos - (int) Math.floor(zPos);

        //block size
        Range<Double> blockRangeX = Range.between(0.3000 - wideNeg, 0.3000 + widePos);
        Range<Double> blockRangeZ = Range.between(0.7000 - wideNeg, 0.7000 + widePos);
        //ladder size (1.8 only)
        Range<Double> ladderRangeX = Range.between(0.4249 - wideNeg, 0.4249 + widePos);
        Range<Double> ladderRangeZ = Range.between(0.5750 - wideNeg, 0.5750 + widePos);
        //trapdoor size
        Range<Double> trapdoorRangeX = Range.between(0.5125 - wideNeg, 0.5125 + widePos);
        Range<Double> trapdoorRangeZ = Range.between(0.4874 - wideNeg, 0.4874 + widePos);
        //shortpane size (1.8 only)
        Range<Double> paneSmallRangeX = Range.between(0.7999 - wideNeg, 0.7999 + widePos);
        Range<Double> paneSmallRangeZ = Range.between(0.2000 - wideNeg, 0.2000 + widePos);
        //longpane size
        Range<Double> paneLongRangeX = Range.between(0.8624 - wideNeg, 0.8624 + widePos);
        Range<Double> paneLongRangeZ = Range.between(0.1375 - wideNeg, 0.1375 + widePos);
        //fence size
        Range<Double> fenceRangeX = Range.between(0.9249 - wideNeg, 0.9249 + widePos);
        Range<Double> fenceRangeZ = Range.between(0.0750 - wideNeg, 0.0750 + widePos);
        //cobblewall size
        Range<Double> cobblewallRangeX = Range.between(0.0499 - wideNeg, 0.0499 + widePos);
        Range<Double> cobblewallRangeZ = Range.between(0.9500 - wideNeg, 0.9500 + widePos);
        //anvil size
        Range<Double> anvilRangeX = Range.between(0.1749 - wideNeg, 0.1749 + widePos);
        Range<Double> anvilRangeZ = Range.between(0.8250 - wideNeg, 0.8250 + widePos);
        //chest size
        Range<Double> chestRangeX = Range.between(0.2374 - wideNeg, 0.2374 + widePos);
        Range<Double> chestRangeZ = Range.between(0.7625 - wideNeg, 0.7625 + widePos);
        //pistonhead size
        Range<Double> pistonHeadRangeX = Range.between(0.5499 - wideNeg, 0.5499 + widePos);
        Range<Double> pistonHeadRangeZ = Range.between(0.4500 - wideNeg, 0.4500 + widePos);
        //cocaoBean1 size
        Range<Double> cocaoBeanOneRangeX = Range.between(0.6124 - wideNeg, 0.6124 + widePos);
        Range<Double> cocaoBeanOneRangeZ = Range.between(0.3875 - wideNeg, 0.3875 + widePos);
        //cocaoBean2 size
        Range<Double> cocaoBeanTwoRangeX = Range.between(0.7374 - wideNeg, 0.7374 + widePos);
        Range<Double> cocaoBeanTwoRangeZ = Range.between(0.2625 - wideNeg, 0.2625 + widePos);
        //flowerpot size
        Range<Double> flowerpotRangeX = Range.between(0.9874 - wideNeg, 0.9874 + widePos);
        Range<Double> flowerpotRangeZ = Range.between(0.0125 - wideNeg, 0.0125 + widePos);

        //checking x coord of player to be not in the range of any forbidden positions
        if( blockRangeX.contains(x) || blockRangeZ.contains(x) ||
            ladderRangeX.contains(x) || ladderRangeZ.contains(x) ||
            trapdoorRangeX.contains(x) || trapdoorRangeZ.contains(x) ||
            paneSmallRangeX.contains(x) || paneSmallRangeZ.contains(x) ||
            paneLongRangeX.contains(x) || paneLongRangeZ.contains(x) ||
            fenceRangeX.contains(x) || fenceRangeZ.contains(x) ||
            cobblewallRangeX.contains(x) || cobblewallRangeZ.contains(x) ||
            anvilRangeX.contains(x) || anvilRangeZ.contains(x) ||
            chestRangeX.contains(x) || chestRangeZ.contains(x) ||
            pistonHeadRangeX.contains(x) || pistonHeadRangeZ.contains(x) ||
            cocaoBeanOneRangeX.contains(x) || cocaoBeanOneRangeZ.contains(x) ||
            cocaoBeanTwoRangeX.contains(x) || cocaoBeanTwoRangeZ.contains(x) ||
            flowerpotRangeX.contains(x) || flowerpotRangeZ.contains(x) ) {

            return false;

        }

        //checking z coord of player to be not in the range of any forbidden positions
        if( blockRangeX.contains(z) || blockRangeZ.contains(z) ||
            ladderRangeX.contains(z) || ladderRangeZ.contains(z) ||
            trapdoorRangeX.contains(z) || trapdoorRangeZ.contains(z) ||
            paneSmallRangeX.contains(z) || paneSmallRangeZ.contains(z) ||
            paneLongRangeX.contains(z) || paneLongRangeZ.contains(z) ||
            fenceRangeX.contains(z) || fenceRangeZ.contains(z) ||
            cobblewallRangeX.contains(z) || cobblewallRangeZ.contains(z) ||
            anvilRangeX.contains(z) || anvilRangeZ.contains(z) ||
            chestRangeX.contains(z) || chestRangeZ.contains(z) ||
            pistonHeadRangeX.contains(z) || pistonHeadRangeZ.contains(z) ||
            cocaoBeanOneRangeX.contains(z) || cocaoBeanOneRangeZ.contains(z) ||
            cocaoBeanTwoRangeX.contains(z) || cocaoBeanTwoRangeZ.contains(z) ||
            flowerpotRangeX.contains(z) || flowerpotRangeZ.contains(z) ) {

            return false;

        }

        return true;

    }

    public void grayGreenDyeRight(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        boolean isGreenDye = getItemInHandData(p).equals(greenData);
        boolean isGrayDye = getItemInHandData(p).equals(grayData);
        String displayname = getItemInHandItemStack(p).getItemMeta().getDisplayName();

        if (isGreenDye || isGrayDye) {

            ArrayList<String> lore = new ArrayList<String>();
            lore.add("§l§6Practice");

            ItemStack item = getItemInHandItemStack(p);
            ItemMeta meta = item.getItemMeta();

            if (isGrayDye && displayname.equals("§lOff - Visible")) {
                meta.setDisplayName("§lOn - Visible");
                item = green;
            } else if (isGrayDye && displayname.equals("§lOff - Invisible")) {
                meta.setDisplayName("§lOn - Invisible");
                item = green;
            } else if (isGreenDye && displayname.equals("§lOn - Visible")) {
                meta.setDisplayName("§lOff - Visible");
                item = gray;
            } else if (isGreenDye && displayname.equals("§lOn - Invisible")) {
                meta.setDisplayName("§lOff - Invisible");
                item = gray;
            }

            meta.setLore(lore);
            item.setItemMeta(meta);
            p.getInventory().setItem(2, item);

            p.updateInventory();
        }
    }

    public void grayGreenDyeLeft(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        boolean isGreenDye = getItemInHandData(p).equals(greenData);
        boolean isGrayDye = getItemInHandData(p).equals(grayData);

        if (isGreenDye || isGrayDye) {

            String displayname = getItemInHandItemStack(p).getItemMeta().getDisplayName();

            ArrayList<String> lore = new ArrayList<String>();
            lore.add("§l§6Practice");

            ItemStack item = getItemInHandItemStack(p);
            ItemMeta meta = getItemInHandItemStack(p).getItemMeta();

            if (isGreenDye && displayname.equals("§lOn - Visible")) {
                meta.setDisplayName("§lOn - Invisible");
                item.setItemMeta(meta);
                //remove all entities in path
                //dont add more entities

            } else if (isGreenDye && displayname.equals("§lOn - Invisible")) {
                meta.setDisplayName("§lOn - Visible");
                item.setItemMeta(meta);
                //place all entities in path
                //add more entities

            } else if (isGrayDye && displayname.equals("§lOff - Visible")) {
                meta.setDisplayName("§lOff - Invisible");
                item.setItemMeta(meta);

            } else if (isGrayDye && displayname.equals("§lOff - Invisible")) {
                meta.setDisplayName("§lOff - Visible");
                item.setItemMeta(meta);

            }
            p.updateInventory();
        }
    }

    public void blazeRodRight(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (getItemInHandMaterial(p).equals(Material.BLAZE_ROD)) {

            ArrayList<String> lore = new ArrayList<String>();
            lore.add("§l§6Practice");

            String s = getItemInHandItemStack(p).getItemMeta().getDisplayName();
            ItemStack item = getItemInHandItemStack(p);
            ItemMeta meta = item.getItemMeta();

            switch (s) {
                case "§lPath 3":
                    meta.setDisplayName("§lPath 1");
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    p.updateInventory();
                    break;
                case "§lPath 1":
                    meta.setDisplayName("§lPath 2");
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    p.updateInventory();
                    break;
                case "§lPath 2":
                    meta.setDisplayName("§lPath 3");
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    p.updateInventory();
                    break;
                default:
                    p.sendMessage("Wrong Item");
                    break;
            }
        }
    }

    public void blazeRodLeft(PlayerInteractEvent e) {
        PathPlayerManager ppm = Main.getInstance().pathPlayerManager;
        Player p = e.getPlayer();

        String displayname = getItemInHandItemStack(p).getItemMeta().getDisplayName();

        if (getItemInHandMaterial(p).equals(Material.BLAZE_ROD)) {
            switch (displayname) {
                case "§lPath 1":
                    ppm.clearPlayerPath(p, 1);
                    break;
                case "§lPath 2":
                    ppm.clearPlayerPath(p, 2);
                    break;
                case "§lPath 3":
                    ppm.clearPlayerPath(p, 3);
                    break;
            }
        }
    }

    public void gunPowderRight(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        ItemMeta m = e.getItem().getItemMeta();
        PlayerObject po = Main.getInstance().playerObjectManager.getPlayerObject().get(p);
        Location l = p.getLocation().clone();
        Vector v = p.getVelocity().clone();

        if (getItemInHandMaterial(p).equals(Material.SULPHUR)) {

            if(e.getItem().getItemMeta().getDisplayName().equals("§l 45 Strafe")) {

                m.setDisplayName("§l 45 Strafe");
                e.getItem().setItemMeta(m);
                l.setYaw(l.getYaw());
                p.teleport(l);
                p.setVelocity(v);


            }
        }

    }

    public Material getItemInHandMaterial(Player p) {
        return Objects.requireNonNull(p.getInventory().getItem(p.getInventory().getHeldItemSlot())).getType();
    }

    public ItemStack getItemInHandItemStack(Player p) {
        return Objects.requireNonNull(p.getInventory().getItem(p.getInventory().getHeldItemSlot()));
    }

    public MaterialData getItemInHandData(Player p) {
        return Objects.requireNonNull(p.getInventory().getItem(p.getInventory().getHeldItemSlot()).getData());
    }

    public boolean isPracticeItem(Player p) {
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§l§6Practice");
        return Objects.equals(Objects.requireNonNull(Objects.requireNonNull(
            p.getInventory().getItem(p.getInventory().getHeldItemSlot())).getItemMeta()).getLore(), lore);
    }

    public boolean isHandEmpty(Player p) {
        ItemStack itemstack = p.getInventory().getItem(p.getInventory().getHeldItemSlot());
        return itemstack == null;
    }

    public boolean isCorrectItem(Player p) {
        if (!(isHandEmpty(p))) {
            return isPracticeItem(p);
        }
        return false;
    }
}
