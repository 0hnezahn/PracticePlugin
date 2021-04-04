package de.legoshi.practicepluginv2.util;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class LocArmObject {

    private Location location;
    private ArmorStand armorStand;

    public LocArmObject(Location location, ArmorStand armorStand) {
        this.location = location;
        this.armorStand = armorStand;
    }

    public LocArmObject() {
        location = null;
        armorStand = null;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setEntity(ArmorStand entity) {
        this.armorStand = entity;
    }

    public ArmorStand getEntity() {
        return this.armorStand;
    }
}
