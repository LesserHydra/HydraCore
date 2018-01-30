package com.lesserhydra.bukkitutil.nbt;

import java.util.UUID;


public interface NbtCompound extends NbtBase {
  
  boolean hasKey(String key);
  
  boolean hasKeyOfType(String key, NbtType type);
  
  boolean hasUUID(String key);
  
  NbtBase get(String key);
  
  boolean getBoolean(String key);
  
  byte getByte(String key);
  
  short getShort(String key);
  
  int getInteger(String key);
  
  long getLong(String key);
  
  float getFloat(String key);
  
  double getDouble(String key);
  
  String getString(String key);
  
  byte[] getByteArray(String key);
  
  int[] getIntArray(String key);
  
  NbtCompound getCompound(String key);
  
  NbtCompound getCompound(String key, boolean createIfMissing);
  
  NbtList getList(String key, NbtType type);
  
  NbtList getList(String key, NbtType type, boolean createIfMissing);
  
  UUID getUUID(String key);
  
  void set(String key, NbtBase value);
  
  void set(String key, boolean value);
  
  void set(String key, byte value);
  
  void set(String key, short value);
  
  void set(String key, int value);
  
  void set(String key, long value);
  
  void set(String key, float value);
  
  void set(String key, double value);
  
  void set(String key, String value);
  
  void set(String key, byte[] value);
  
  void set(String key, int[] value);
  
  void set(String key, NbtCompound value);
  
  void set(String key, NbtList value);
  
  void setUUID(String key, UUID value);
  
  void remove(String key);
  
  NbtCompound clone();
  
}
