package com.lesserhydra.bukkitutil.nbt;

public class NbtShort implements NbtNumber {
  
  private final short value;
  
  public NbtShort(short value) {
    this.value = value;
  }
  
  public NbtType getType() {
    return NbtType.SHORT;
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
    return new NbtShort(value);
  }
  
}
