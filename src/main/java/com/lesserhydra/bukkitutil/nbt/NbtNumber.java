package com.lesserhydra.bukkitutil.nbt;

public interface NbtNumber extends NbtBase {
  
  @Override
  default boolean isNumber() {
    return true;
  }
  
  byte asByte();
  
  short asShort();
  
  int asInt();
  
  long asLong();
  
  float asFloat();
  
  double asDouble();
  
}
