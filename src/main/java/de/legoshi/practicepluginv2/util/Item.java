package de.legoshi.practicepluginv2.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Item {

    private final ItemStack current;

    public Item(ItemStack i) {
        this.current = i;
    }

    public Item(Material m, int amount, short ID) {
        this(new ItemStack(m, amount, ID));
    }

    public Item(Material m, int amount) {
        this(new ItemStack(m, amount));
    }

    public Item setDisplayName(String displayname) {
        ItemMeta m = current.getItemMeta();
        m.setDisplayName(displayname);
        current.setItemMeta(m);
        return this;
    }

    public Item setLore(List<String> lore) {
        ItemMeta m = current.getItemMeta();
        m.setLore(lore);
        current.setItemMeta(m);
        return this;
    }

    public Item addEnchantment(Enchantment ench, int level, boolean bool) {
        ItemMeta m = current.getItemMeta();
        m.addEnchant(ench, level, bool);
        current.setItemMeta(m);
        return this;
    }

    public Item addUnsafeEnchantment(Enchantment ench, int level) {
        current.addUnsafeEnchantment(ench, level);
        return this;
    }

    public Item setAmount(int am) {
        current.setAmount(am);
        return this;
    }

    public ItemStack build() {
        return this.current;
    }
}
