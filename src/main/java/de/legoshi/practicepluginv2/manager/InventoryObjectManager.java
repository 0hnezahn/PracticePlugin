package de.legoshi.practicepluginv2.manager;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.util.InventoryObject;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class InventoryObjectManager {

    HashMap<Player, InventoryObject> pInventory;

    public InventoryObjectManager() {
        this.pInventory = new HashMap<Player, InventoryObject>();
    }

    public HashMap<Player, InventoryObject> getInventoryObjectManager() {
        return this.pInventory;
    }

    public void joinPlayer(Player p) {
        InventoryObjectManager iom = Main.getInstance().inventoryObjectManager;
        InventoryObject inventoryObject = new InventoryObject(p);
        iom.getInventoryObjectManager().put(p, inventoryObject);
    }

}
