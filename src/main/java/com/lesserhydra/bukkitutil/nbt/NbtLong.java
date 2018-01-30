package com.lesserhydra.bukkitutil.nbt;

public class NbtLong implements NbtNumber {
  
  private final long value;
  
  public NbtLong(long value) {
    this.value = value;
  }
  
  public NbtType getType() {
    return NbtType.LONG;
  }
  
  public byte asByte() {
    return (byte) value;
  };
  
  public short asShort() {
    return (short) value;
  }
  
  public int asInt() {
    return (int) value;
  }
  
  public long asLong() {
    return (long) value;
  }
  
  public float asFloat() {
    return (float) value;
  }
  
  public double asDouble() {
    return (double) value;
  }
  
  @Override
  public NbtBase clone() {
    return new NbtLong(value);
  }
  
}
