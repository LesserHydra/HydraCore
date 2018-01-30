package com.lesserhydra.bukkitutil.nbt;

import java.util.Arrays;


public class NbtLongArray implements NbtBase {
  
  private final long[] data;
  
  public NbtLongArray(long[] data) {
    this.data = data;
  }
  
  @Override
  public NbtType getType() {
    return NbtType.LONG_ARRAY;
  }
  
  public long[] getLongArray() {
    return data;
  }
  
  @Override
  public NbtBase clone() {
    return new NbtLongArray(Arrays.copyOf(data, data.length));
  }
}
