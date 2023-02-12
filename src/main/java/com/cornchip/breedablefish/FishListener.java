package com.cornchip.breedablefish;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class FishListener implements Listener {
    @EventHandler
    public void onFishBreed(PlayerInteractEntityEvent event) {
        if(event.isCancelled()) {
            return;
        }

        ItemStack heldItem = null;
        EquipmentSlot hand = event.getHand();
        if (hand == EquipmentSlot.HAND) {
            heldItem = event.getPlayer().getInventory().getItemInMainHand();
        } else if (hand == EquipmentSlot.OFF_HAND) {
            heldItem = event.getPlayer().getInventory().getItemInOffHand();
        } else {
            return;
        }

        //check if we are clicking with kelp
        if(heldItem.getType() != Material.KELP) {
            return;
        }

        Entity clickedEntity = event.getRightClicked();
        EntityType type = clickedEntity.getType();
        //check if we clicked a not fish
        if( !(type == EntityType.TROPICAL_FISH || type == EntityType.SALMON || type == EntityType.PUFFERFISH || type == EntityType.COD) ) {
            return;
        }

        //spawn new fish of same type
        Location pos = clickedEntity.getLocation();
        event.getPlayer().getWorld().spawnEntity(pos, type, false);

        //remove a kelp
        event.getPlayer().getInventory().removeItem(new ItemStack(Material.KELP, 1));
    }
}
