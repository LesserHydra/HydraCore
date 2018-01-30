package com.lesserhydra.bukkitutil.nbt.volatilecode;

import com.lesserhydra.bukkitutil.nbt.NbtBase;
import com.lesserhydra.bukkitutil.nbt.NbtCompound;
import com.lesserhydra.bukkitutil.nbt.NbtList;
import com.lesserhydra.bukkitutil.nbt.NbtType;
import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;

import java.util.UUID;


@SuppressWarnings({"unused", "WeakerAccess"})
public class CraftNbtCompound implements NbtCompound {
  
  private final NBTTagCompound handle;
  
  public CraftNbtCompound(NBTTagCompound handle) {
    this.handle = handle;
  }
  
  public NBTTagCompound getHandle() {
    return handle;
  }
  
  @Override
  public NbtType getType() {
    return NbtType.COMPOUND;
  }
  
  @Override
  public CraftNbtCompound clone() {
    return new CraftNbtCompound((NBTTagCompound) handle.clone());
  }
  
  @Override
  public boolean isEmpty() {
    return handle.isEmpty();
  }
  
  @Override
  public boolean hasKey(String key) {
    return handle.hasKey(key);
  }
  
  @Override
  public boolean hasKeyOfType(String key, NbtType type) {
    return handle.hasKeyOfType(key, type.getId());
  }
  
  @Override
  public boolean hasUUID(String key) {
    return handle.b(key); //OBF: Line 108, hasUUID
  }
  
  @Override
  public NbtBase get(String key) {
    return NbtFactoryNMS.wrapTag(handle.get(key));
  }
  
  @Override
  public boolean getBoolean(String key) {
    return handle.getBoolean(key);
  }
  
  @Override
  public byte getByte(String key) {
    return handle.getByte(key);
  }
  
  @Override
  public short getShort(String key) {
    return handle.getShort(key);
  }
  
  @Override
  public int getInteger(String key) {
    return handle.getInt(key);
  }
  
  @Override
  public long getLong(String key) {
    return handle.getLong(key);
  }
  
  @Override
  public float getFloat(String key) {
    return handle.getFloat(key);
  }
  
  @Override
  public double getDouble(String key) {
    return handle.getDouble(key);
  }
  
  @Override
  public String getString(String key) {
    return handle.getString(key);
  }
  
  @Override
  public byte[] getByteArray(String key) {
    return handle.getByteArray(key);
  }
  
  @Override
  public int[] getIntArray(String key) {
    return handle.getIntArray(key);
  }
  
  @Override
  public NbtCompound getCompound(String key) {
    return getCompound(key, false);
  }
  
  @Override
  public NbtCompound getCompound(String key, boolean createIfMissing) {
    NBTBase result = handle.get(key);
    if (result instanceof NBTTagCompound) return new CraftNbtCompound((NBTTagCompound) result);
    if (!createIfMissing) return null;
    NBTTagCompound newCompound = new NBTTagCompound();
    handle.set(key, newCompound);
    return new CraftNbtCompound(newCompound);
  }
  
  private static boolean checkListElementType(NBTTagList list, NbtType type) {
    return list.isEmpty() || list.g() == type.getId();
  }
  
  @Override
  public NbtList getList(String key, NbtType type) {
    return getList(key, type, false);
  }
  
  @Override
  public NbtList getList(String key, NbtType type, boolean createIfMissing) {
    NBTBase result = handle.get(key);
    //Result is good
    if (result instanceof NBTTagList && checkListElementType((NBTTagList) result, type)) {
      return new CraftNbtList((NBTTagList) result);
    }
    
    //Result is bad. Create?
    if (!createIfMissing) return null;
    NBTTagList newList = new NBTTagList();
    handle.set(key, newList);
    return new CraftNbtList(newList);
  }
  
  @Override
  public UUID getUUID(String key) {
    return handle.a(key); //OBF: Line 104, getUUID()
  }
  
  @Override
  public void set(String key, NbtBase value) {
    handle.set(key, NbtFactoryNMS.unwrapTag(value));
  }
  
  @Override
  public void set(String key, boolean value) {
    handle.setBoolean(key, value);
  }
  
  @Override
  public void set(String key, byte value) {
    handle.setByte(key, value);
  }
  
  @Override
  public void set(String key, short value) {
    handle.setShort(key, value);
  }
  
  @Override
  public void set(String key, int value) {
    handle.setInt(key, value);
  }
  
  @Override
  public void set(String key, long value) {
    handle.setLong(key, value);
  }
  
  @Override
  public void set(String key, float value) {
    handle.setFloat(key, value);
  }
  
  @Override
  public void set(String key, double value) {
    handle.setDouble(key, value);
  }
  
  @Override
  public void set(String key, String value) {
    handle.setString(key, value);
  }
  
  @Override
  public void set(String key, byte[] value) {
    handle.setByteArray(key, value);
  }
  
  @Override
  public void set(String key, int[] value) {
    handle.setIntArray(key, value);
  }
  
  @Override
  public void set(String key, NbtCompound value) {
    handle.set(key, ((CraftNbtCompound)value).getHandle());
  }
  
  @Override
  public void set(String key, NbtList value) {
    handle.set(key, ((CraftNbtList)value).getHandle());
  }
  
  @Override
  public void setUUID(String key, UUID value) {
    handle.a(key, value); //OBF: Line 98, setUUID()
  }
  
  @Override
  public void remove(String key) {
    handle.remove(key);
  }
}
