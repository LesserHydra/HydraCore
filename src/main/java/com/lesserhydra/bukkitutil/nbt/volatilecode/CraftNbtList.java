package com.lesserhydra.bukkitutil.nbt.volatilecode;

import com.lesserhydra.bukkitutil.nbt.NbtBase;
import com.lesserhydra.bukkitutil.nbt.NbtByte;
import com.lesserhydra.bukkitutil.nbt.NbtByteArray;
import com.lesserhydra.bukkitutil.nbt.NbtCompound;
import com.lesserhydra.bukkitutil.nbt.NbtList;
import com.lesserhydra.bukkitutil.nbt.NbtLong;
import com.lesserhydra.bukkitutil.nbt.NbtShort;
import com.lesserhydra.bukkitutil.nbt.NbtType;
import net.minecraft.server.v1_12_R1.NBTTagByte;
import net.minecraft.server.v1_12_R1.NBTTagDouble;
import net.minecraft.server.v1_12_R1.NBTTagFloat;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagLong;
import net.minecraft.server.v1_12_R1.NBTTagShort;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;


@SuppressWarnings({"unused", "WeakerAccess"})
public class CraftNbtList implements NbtList {
  
  private final NBTTagList handle;
  
  public CraftNbtList(NBTTagList handle) {
    this.handle = handle;
  }
  
  public NBTTagList getHandle() {
    return handle;
  }
  
  @Override
  public NbtType getType() {
    return NbtType.LIST;
  }
  
  @Override
  public NbtType getElementType() {
    return NbtType.fromId(handle.g()); //OBF: Line 222, getElementTypeId
  }
  
  @Override
  public int size() {
    return handle.size();
  }
  
  @Override
  public NbtList add(NbtBase... elements) {
    for (NbtBase element : elements) {
      NbtBase result = element.clone();
      handle.add(((CraftNbtBase) result).getHandle());
    }
    return this;
  }
  
  @Override
  public NbtList add(NbtList... elements) {
    for (NbtList element : elements) {
      NbtList result = element.clone();
      handle.add(((CraftNbtList) result).getHandle());
    }
    return this;
  }
  
  @Override
  public NbtList add(NbtCompound... elements) {
    for (NbtCompound element : elements) {
      NbtCompound result = element.clone();
      handle.add(((CraftNbtCompound) result).getHandle());
    }
    return this;
  }
  
  @Override
  public NbtList add(byte... elements) {
    for (byte element : elements) {
      handle.add(new NBTTagByte(element));
    }
    return this;
  }
  
  @Override
  public NbtList add(short... elements) {
    for (short element : elements) {
      handle.add(new NBTTagShort(element));
    }
    return this;
  }
  
  @Override
  public NbtList add(int... elements) {
    for (int element : elements) {
      handle.add(new NBTTagInt(element));
    }
    return this;
  }
  
  @Override
  public NbtList add(long... elements) {
    for (long element : elements) {
      handle.add(new NBTTagLong(element));
    }
    return this;
  }
  
  @Override
  public NbtList add(float... elements) {
    for (float element : elements) {
      handle.add(new NBTTagFloat(element));
    }
    return this;
  }
  
  @Override
  public NbtList add(double... elements) {
    for (double element : elements) {
      handle.add(new NBTTagDouble(element));
    }
    return this;
  }
  
  @Override
  public NbtList add(String... elements) {
    for (String element : elements) {
      handle.add(new NBTTagString(element));
    }
    return this;
  }
  
  /*@Override
  public NbtList add(byte[] element) {
    handle.add(new NBTTagByteArray(element.clone()));
    return this;
  }
  
  @Override
  public NbtList add(int[] element) {
    handle.add(new NBTTagIntArray(element.clone()));
    return this;
  }*/
  
  @Override
  public NbtBase peek() {
    assert size() > 0;
    return get(size() - 1);
  }
  
  @Override
  public NbtList peekList() {
    assert size() > 0;
    return getList(size() - 1);
  }
  
  @Override
  public NbtCompound peekCompound() {
    assert size() > 0;
    return getCompound(size() - 1);
  }
  
  @Override
  public NbtBase get(int index) {
    return NbtFactoryNMS.wrapTag(handle.i(index)); //OBF: Line 187, getTag()
  }
  
  @Override
  public byte getByte(int index) {
    NbtBase result = get(index);
    if (result.getType() != NbtType.BYTE) return 0;
    return ((NbtByte) result).asByte();
  }
  
  @Override
  public short getShort(int index) {
    NbtBase result = get(index);
    if (result.getType() != NbtType.SHORT) return 0;
    return ((NbtShort) result).asShort();
  }
  
  @Override
  public int getInteger(int index) {
    return handle.c(index); //OBF: Line 134, getInteger()
  }
  
  @Override
  public long getLong(int index) {
    NbtBase result = get(index);
    if (result.getType() != NbtType.LONG) return 0;
    return ((NbtLong) result).asLong();
  }
  
  @Override
  public float getFloat(int index) {
    return handle.g(index); //OBF: Line 167, getFloat()
  }
  
  @Override
  public double getDouble(int index) {
    return handle.f(index); //OBF: Line 156, getDouble()
  }
  
  @Override
  public String getString(int index) {
    return handle.getString(index);
  }
  
  @Override
  public byte[] getByteArray(int index) {
    NbtBase result = get(index);
    if (result.getType() != NbtType.BYTE_ARRAY) return null;
    return ((NbtByteArray) result).getByteArray();
  }
  
  @Override
  public int[] getIntArray(int index) {
    return handle.d(index); //OBF: Line 145, getIntegerArray()
  }
  
  @Override
  public NbtList getList(int index) {
    NbtBase result = get(index);
    if (result.getType() != NbtType.LIST) return null;
    return (NbtList) result;
  }
  
  @Override
  public NbtCompound getCompound(int index) {
    return new CraftNbtCompound(handle.get(index));
  }
  
  @Override
  public NbtBase remove(int index) {
    return NbtFactoryNMS.wrapTag(handle.remove(index));
  }
  
  @Override
  public boolean isEmpty() {
    return handle.isEmpty();
  }
  
  @Override
  public NbtList clone() {
    return new CraftNbtList((NBTTagList) handle.clone());
  }
  
  @Override
  public @NotNull Iterator<NbtBase> iterator() {
    return new NbtListIterator();
  }
  
  @Override
  public String toString() {
    return handle.toString();
  }
  
  private class NbtListIterator implements Iterator<NbtBase> {
    int index = -1;
    
    @Override
    public boolean hasNext() {
      return index + 1 < size();
    }
  
    @Override
    public NbtBase next() {
      return get(++index);
    }
  
    @Override
    public void remove() {
      CraftNbtList.this.remove(index);
    }
  }
  
}
