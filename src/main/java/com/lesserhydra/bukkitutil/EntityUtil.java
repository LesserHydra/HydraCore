package com.lesserhydra.bukkitutil;

import com.lesserhydra.bukkitutil.volatilecode.EntityUtilNMS;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.stream.Stream;


@SuppressWarnings({"WeakerAccess", "unused"})
public class EntityUtil {
  
  public static Stream<LivingEntity> streamLeashedEntities(Entity entity) {
    return entity.getNearbyEntities(7, 7, 7).stream()
        .filter(e -> e instanceof LivingEntity)
        .map(e -> (LivingEntity) e)
        .filter(e -> e.getLeashHolder() == entity);
  }
  
  public static void attachPlayerLeash(LivingEntity leashedEntity, Entity leashHolder, Player currentHolder) {
    PlayerLeashEntityEvent event = new PlayerLeashEntityEvent(leashedEntity, leashHolder, currentHolder);
    Bukkit.getServer().getPluginManager().callEvent(event);
    if (event.isCancelled()) return;
    leashedEntity.setLeashHolder(leashHolder);
    EntityUtilNMS.sendAttachEntityPacket(currentHolder, leashedEntity);
  }
  
  public static Cow shearMushroomCow(MushroomCow mushCow) {
    //Despawn mooshroom
    mushCow.remove();
    //Explosions to cover our tracks!
    mushCow.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, mushCow.getLocation().add(0, 0.45, 0), 1);
    //Spawn normal cow
    Cow cow = mushCow.getWorld().spawn(mushCow.getLocation(), Cow.class);
    //Totally the same cow!
    cow.setHealth(mushCow.getHealth());
    cow.setCustomName(mushCow.getCustomName());
    //Sound
    cow.getWorld().playSound(cow.getLocation(), Sound.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
    //Drop 5 mushrooms
    for (int i = 0; i < 5; i++) {
      cow.getWorld().dropItemNaturally(cow.getLocation().add(0, 0.9, 0), new ItemStack(Material.RED_MUSHROOM));
    }
    //Return result
    return cow;
  }
  
  public static void setArrowPickupStatus(Arrow arrow, boolean value) {
    EntityUtilNMS.setArrowPickup(arrow, value);
  }
  
}
