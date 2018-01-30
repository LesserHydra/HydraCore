package com.lesserhydra.bukkitutil.nbt;

import com.lesserhydra.bukkitutil.nbt.volatilecode.NbtFactoryNMS;


public class NbtFactory {
  
  public static NbtList makeList() {
    return NbtFactoryNMS.makeList();
  }
  
  public static NbtCompound makeCompound() {
    return NbtFactoryNMS.makeCompound();
  }
  
}
