package com.lesserhydra.bukkitutil.nbt.volatilecode;

import com.lesserhydra.bukkitutil.nbt.NbtBase;
import net.minecraft.server.v1_12_R1.NBTBase;


public interface CraftNbtBase extends NbtBase {
  
  NBTBase getHandle();
  
}
