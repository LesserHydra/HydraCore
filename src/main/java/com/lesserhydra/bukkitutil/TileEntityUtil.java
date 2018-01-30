package com.lesserhydra.bukkitutil;

import com.lesserhydra.bukkitutil.volatilecode.TileEntityUtilNMS;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class TileEntityUtil {
  
  @Nullable
  public static TileEntityWrapper getTileEntity(BlockStateMeta meta) {
    return TileEntityUtilNMS.getTileEntityFrom(meta);
  }
  
  @NotNull
  public static TileEntityWrapper getTileEntity(SpawnerMinecart cart) {
    return TileEntityUtilNMS.getTileEntityFrom(cart);
  }
  
  @NotNull
  public static TileEntityWrapper getTileEntity(CreatureSpawner spawner) {
    return TileEntityUtilNMS.getTileEntityFrom(spawner);
  }
  
}
