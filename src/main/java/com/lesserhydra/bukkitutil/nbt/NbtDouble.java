package com.lesserhydra.bukkitutil.nbt;

public class NbtDouble implements NbtNumber {
  
  private final double value;
  
  public NbtDouble(double value) {
    this.value = value;
  }
  
  public NbtType getType() {
    return NbtType.DOUBLE;
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
    return new NbtDouble(value);
  }
  
}
