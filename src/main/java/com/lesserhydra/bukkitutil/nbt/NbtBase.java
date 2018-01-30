package com.lesserhydra.bukkitutil.nbt;

@SuppressWarnings({"unused", "WeakerAccess"})
public interface NbtBase extends Cloneable {
  
  //TODO: read and write?
  
  NbtType getType();
  
  default boolean isNumber() {
    return false;
  }
  
  default boolean isEmpty() {
    return false;
  }
  
  NbtBase clone();
  
  String toString();

}
