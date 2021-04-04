package de.legoshi.practicepluginv2.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class InventoryObject {

    Player player;
    Inventory inventory;
    String mode;

    public InventoryObject(Player player) {
        this.player = player;
        this.inventory = player.getInventory();
        mode = "build";
    }

    public InventoryObject() {}

    public Player getPlayer() {
        return this.player;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public String getMode() {
        return this.mode;
    }

    public void setJump(Player player) {
        player.getInventory().clear();
        this.mode = "jump";

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§l§6Practice");

        ItemStack red = new ItemStack(Material.INK_SACK, 1, (short)1);
        ItemMeta metared = red.getItemMeta();
        metared.setLore(lore);
        metared.setDisplayName("§lCheckpoint");
        red.setItemMeta(metared);

        player.getInventory().setItem(0, red);

        ItemStack gray = new ItemStack(Material.INK_SACK, 1, (short)8);
        ItemMeta metagray = gray.getItemMeta();
        metagray.setLore(lore);
        metagray.setDisplayName("§lOff - Invisible");
        gray.setItemMeta(metagray);

        player.getInventory().setItem(2, gray);

        player.getInventory().setItem(3, new Item(Material.BLAZE_ROD, 1)
            .setLore(lore)
            .setDisplayName("§lPath 1")
            .build());

        ItemStack head1 = CustomItemSkulls.getSkull("http://textures.minecraft.net/texture/9cdb8f43656c06c4e8683e2e6341b4479f157f48082fea4aff09b37ca3c6995b");
        ItemStack head2 = CustomItemSkulls.getSkull("http://textures.minecraft.net/texture/61e1e730c77279c8e2e15d8b271a117e5e2ca93d25c8be3a00cc92a00cc0bb85");

        ItemMeta m1 = head1.getItemMeta();
        m1.setLore(lore);
        m1.setDisplayName("§lForward");
        head1.setItemMeta(m1);

        ItemMeta m2 = head2.getItemMeta();
        m2.setLore(lore);
        m2.setDisplayName("§lBackwards");
        head2.setItemMeta(m2);

        player.getInventory().setItem(5, head1);

        player.getInventory().setItem(6, new Item(Material.OBSIDIAN, 1)
            .setLore(lore)
            .setDisplayName("§l0.100")
            .build());

        player.getInventory().setItem(7, head2);

        player.getInventory().setItem(8, new Item(Material.SULPHUR, 1)
        .setLore(lore)
        .setDisplayName("§l 45 Strafe")
        .build());

        player.updateInventory();
    }

    public void setBuild(Player player) {
        player.getInventory().clear();
        this.mode = "build";

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§l§6Practice");

        ItemStack red = new ItemStack(Material.INK_SACK, 1, (short)1);
        ItemMeta metared = red.getItemMeta();
        metared.setLore(lore);
        metared.setDisplayName("§lCheckpoint");
        red.setItemMeta(metared);

        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        ItemStack redstone = new ItemStack(Material.REDSTONE, 1);

        player.getInventory().setItem(0, red);
        player.getInventory().setItem(7, redstone);
        player.getInventory().setItem(8, arrow);
    }
}
