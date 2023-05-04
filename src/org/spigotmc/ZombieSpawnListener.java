package org.spigotmc;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;


public class ZombieSpawnListener implements Listener {

    @EventHandler
    public void onZombieSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() == EntityType.ZOMBIE) {
            Zombie zombie = (Zombie) event.getEntity();
            AttributeInstance movementSpeedAttribute = zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            if (movementSpeedAttribute != null) {
                double newSpeed = 3; 
                movementSpeedAttribute.setBaseValue(newSpeed);
            }
        }
    }
}