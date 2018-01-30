package com.lesserhydra.bukkitutil.nbt;

import java.util.Arrays;


public class NbtIntArray implements NbtBase {
  
  private final int[] data;
  
  public NbtIntArray(int[] data) {
    this.data = data;
  }
  
  @Override
  public NbtType getType() {
    return NbtType.INT_ARRAY;
  }
  
  public int[] getIntArray() {
    return data;
  }
  
  @Override
  public NbtBase clone() {
    return new NbtIntArray(Arrays.copyOf(data, data.length));
  }
}
