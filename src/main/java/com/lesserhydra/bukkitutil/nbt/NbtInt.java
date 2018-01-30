package com.lesserhydra.bukkitutil.nbt;

public class NbtInt implements NbtNumber {
  
  private final int value;
  
  public NbtInt(int value) {
    this.value = value;
  }
  
  public NbtType getType() {
    return NbtType.INT;
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
    return new NbtInt(value);
  }
  
}
