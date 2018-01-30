package com.lesserhydra.bukkitutil.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;


public interface NbtList extends NbtBase, Iterable<NbtBase> {
  
  NbtType getElementType();
  
  int size();
  
  NbtBase add(NbtBase element);
  
  NbtList add(NbtList element);
  
  NbtCompound add(NbtCompound element);
  
  void add(byte element);
  
  void add(short element);
  
  void add(int element);
  
  void add(long element);
  
  void add(float element);
  
  void add(double element);
  
  void add(String element);
  
  void add(byte[] element);
  
  void add(int[] element);
  
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
