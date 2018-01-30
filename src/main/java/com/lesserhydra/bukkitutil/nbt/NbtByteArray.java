package com.lesserhydra.bukkitutil.nbt;

import java.util.Arrays;


public class NbtByteArray implements NbtBase {
  
  private final byte[] data;
  
  public NbtByteArray(byte[] data) {
    this.data = data;
  }
  
  @Override
  public NbtType getType() {
    return NbtType.BYTE_ARRAY;
  }
  
  public byte[] getByteArray() {
    return data;
  }
  
  @Override
  public NbtBase clone() {
    return new NbtByteArray(Arrays.copyOf(data, data.length));
  }
}
