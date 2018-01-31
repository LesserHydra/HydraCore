package com.lesserhydra.bukkitutil.nbt;

import java.util.UUID;


@SuppressWarnings({"unused", "WeakerAccess", "UnusedReturnValue"})
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
  
  NbtCompound set(String key, NbtBase value);
  
  NbtCompound set(String key, boolean value);
  
  NbtCompound set(String key, byte value);
  
  NbtCompound set(String key, short value);
  
  NbtCompound set(String key, int value);
  
  NbtCompound set(String key, long value);
  
  NbtCompound set(String key, float value);
  
  NbtCompound set(String key, double value);
  
  NbtCompound set(String key, String value);
  
  NbtCompound set(String key, byte[] value);
  
  NbtCompound set(String key, int[] value);
  
  NbtCompound set(String key, NbtCompound value);
  
  NbtCompound set(String key, NbtList value);
  
  NbtCompound setUUID(String key, UUID value);
  
  NbtCompound remove(String key);
  
  NbtCompound clone();
  
}
