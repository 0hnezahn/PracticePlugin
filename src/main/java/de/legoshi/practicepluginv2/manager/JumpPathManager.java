package de.legoshi.practicepluginv2.manager;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.util.LocArmObject;
import de.legoshi.practicepluginv2.util.PathPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Objects;

public class JumpPathManager {

    private ItemStack green = new ItemStack(Material.INK_SACK, 1, (short) 2);
    private ItemStack gray = new ItemStack(Material.INK_SACK, 1, (short) 8);

    private MaterialData greenData = green.getData();
    private MaterialData grayData = gray.getData();

    private ItemStack greenGlas = new ItemStack(Material.STAINED_GLASS, 1, (short) 5);
    private ItemStack yellowGlas = new ItemStack(Material.STAINED_GLASS, 1, (short) 4);
    private ItemStack redGlas = new ItemStack(Material.STAINED_GLASS, 1, (short) 14);

    boolean hasJumped = true;

    public void jumpPath() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            public void run() {
                checkPlayers();
            }
        }, 0, 1);

    }

    private void checkPlayers() {
        for (Player all : Bukkit.getOnlinePlayers()) {

            String mode = Main.getInstance().inventoryObjectManager.getInventoryObjectManager().get(all).getMode();

            if (mode.equals("jump")) {

                ItemStack item = all.getInventory().getItem(2);
                int index;

                Location location = all.getLocation();
                location.setY(all.getLocation().getY() - 2.0);

                String displayName = all.getInventory().getItem(3).getItemMeta().getDisplayName();

                switch (displayName) {
                    case "§lPath 1":
                        index = 1;
                        armorStandCreator(location, all, index, item);
                        break;
                    case "§lPath 2":
                        index = 2;
                        armorStandCreator(location, all, index, item);
                        break;
                    case "§lPath 3":
                        index = 3;
                        armorStandCreator(location, all, index, item);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void armorStandCreator(Location location, Player all, int index, ItemStack item) {
        LocArmObject lao = new LocArmObject();
        ArrayList<LocArmObject> al = Main.getInstance().pathPlayerManager.getPathPlayers().get(all).getPath().get(index - 1);
        PathPlayer pp = Main.getInstance().pathPlayerManager.getPathPlayers().get(all);
        String displayname = item.getItemMeta().getDisplayName();
        Material material = item.getType();
        MaterialData materialData = item.getData();

        if (!(isPathFull(all, index))) {
            if (materialData.equals(grayData)) {
                //Path nicht voll + GRAY + OFF + Visible
                if (displayname.equals("§lOff - Visible")) {
                    visibleEntities(pp, index);
                } else if (displayname.equals("§lOff - Invisible")) {
                    invisibleEntities(pp, index);
                }
            } else if (materialData.equals(greenData)) {

                if (displayname.equals("§lOn - Visible")) {
                    al.add(createArmorStand(all, location, index));
                } else if (displayname.equals("§lOn - Invisible")) {
                    lao.setEntity(null);
                    lao.setLocation(location);
                    al.add(lao);
                }
            }
        } else {

            if (materialData.equals(grayData)) {
                //Path nicht voll + GRAY + OFF + Visible
                if (displayname.equals("§lOff - Visible")) {
                    visibleEntities(pp, index);
                } else if (displayname.equals("§lOff - Invisible")) {
                    invisibleEntities(pp, index);
                }
            } else if (materialData.equals(greenData)) {

                if (displayname.equals("§lOn - Visible")) {
                    if (al.get(0).getEntity() != null) al.get(0).getEntity().remove();
                    al.remove(0);
                    al.add(createArmorStand(all, location, index));
                } else if (displayname.equals("§lOn - Invisible")) {
                    if (al.get(0).getEntity() != null) al.get(0).getEntity().remove();
                    al.remove(0);
                    lao.setEntity(null);
                    lao.setLocation(location);
                    al.add(lao);
                }
            }
        }
    }

    private void visibleEntities(PathPlayer pathPlayer, int index) {
        ArrayList<LocArmObject> laoList = pathPlayer.getPath().get(index - 1);
        Player p = pathPlayer.getPlayer();
        for (int i = 0; i < laoList.size(); i++) {
            if (laoList.get(i).getEntity() == null) {
                LocArmObject lao;
                lao = createArmorStand(p, laoList.get(i).getLocation(), index);
                laoList.set(i, lao);
            }
        }
    }

    private void invisibleEntities(PathPlayer pathPlayer, int index) {
        ArrayList<LocArmObject> laoList = pathPlayer.getPath().get(index - 1);
        Player p = pathPlayer.getPlayer();
        for (int i = 0; i < laoList.size(); i++) {
            if (laoList.get(i).getEntity() != null) {
                LocArmObject lao = new LocArmObject();
                lao.setEntity(null);
                lao.setLocation(laoList.get(i).getLocation());
                laoList.get(i).getEntity().remove();
                laoList.set(i, lao);
            }
        }
    }

    private LocArmObject createArmorStand(Player p, Location l, int index) {
        LocArmObject lao = new LocArmObject();
        //Location locationHorse = l;
        //locationHorse.setY(locationHorse.getY()-0.47125);

        ArmorStand as = Objects.requireNonNull(p.getLocation().getWorld()).spawn(l, ArmorStand.class);
        //Horse horse = Objects.requireNonNull(p.getLocation().getWorld()).spawn(locationHorse, Horse.class);
        //horse.setAdult();
        //horse.setCollidable(false);
        //horse.setTamed(true);
        //horse.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 2));
        //as.addPassenger(horse);


        if (index == 1)
            Objects.requireNonNull(as.getEquipment()).setHelmet(new ItemStack(greenGlas));
        if (index == 2)
            Objects.requireNonNull(as.getEquipment()).setHelmet(new ItemStack(yellowGlas));
        if (index == 3)
            Objects.requireNonNull(as.getEquipment()).setHelmet(new ItemStack(redGlas));

        as.setGravity(false);
        as.setVisible(false);
        lao.setEntity(as);
        lao.setLocation(l);

        return lao;
    }

    private boolean isPathFull(Player p, int i) {
        PathPlayer pp = Main.getInstance().pathPlayerManager.getPathPlayers().get(p);
        int pathLength = 0;
        if (pp.getPath().isEmpty()) {
            return false;
        }

        pathLength = pp.getPath().get(i - 1).size();
        return pathLength >= 50;
    }

    Location l1 = null;
    Location l2;

}
