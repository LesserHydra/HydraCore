package com.lesserhydra.bukkitutil.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;


@SuppressWarnings({"WeakerAccess", "unused"})
public interface NbtList extends NbtBase, Iterable<NbtBase> {
  
  NbtType getElementType();
  
  int size();
  
  NbtList add(NbtBase... element);
  
  NbtList add(NbtList... element);
  
  NbtList add(NbtCompound... element);
  
  NbtList add(byte... element);
  
  NbtList add(short... element);
  
  NbtList add(int... element);
  
  NbtList add(long... element);
  
  NbtList add(float... element);
  
  NbtList add(double... element);
  
  NbtList add(String... element);
  
  //TODO: NbtList add(byte[] element);
  
  //TODO: NbtList add(int[] element);
  
  NbtBase peek();
  
  NbtList peekList();
  
  NbtCompound peekCompound();
  
  NbtBase get(int index);
  
  byte getByte(int index);
  
  short getShort(int index);
  
  int getInteger(int index);
  
  long getLong(int index);
  
  float getFloat(int index);
  
  double getDouble(int index);
  
  String getString(int index);
  
  byte[] getByteArray(int index);
  
  int[] getIntArray(int index);
  
  NbtList getList(int index);
  
  NbtCompound getCompound(int index);
  
  NbtBase remove(int index);
  
  NbtList clone();
  
  @NotNull
  Iterator<NbtBase> iterator();
  
}
