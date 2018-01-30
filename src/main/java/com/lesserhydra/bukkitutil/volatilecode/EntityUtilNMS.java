package com.lesserhydra.bukkitutil.volatilecode;

import net.minecraft.server.v1_12_R1.EntityArrow;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.PacketPlayOutAttachEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;


@SuppressWarnings({"WeakerAccess", "unused"})
public class EntityUtilNMS {
  
  public static void sendAttachEntityPacket(org.bukkit.entity.Player player, org.bukkit.entity.Entity bukkitEntity) {
    EntityLiving entityLiving = ((CraftLivingEntity)bukkitEntity).getHandle();
    if (!(entityLiving instanceof EntityInsentient)) return;
    EntityInsentient entity = (EntityInsentient) entityLiving;
    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutAttachEntity(entity, entity.getLeashHolder()));
  }
  
  public static void setArrowPickup(org.bukkit.entity.Arrow bukkitArrow, boolean value) {
    EntityArrow arrow = ((CraftArrow) bukkitArrow).getHandle();
    arrow.fromPlayer = value ? EntityArrow.PickupStatus.ALLOWED : EntityArrow.PickupStatus.DISALLOWED;
  }
  
}
