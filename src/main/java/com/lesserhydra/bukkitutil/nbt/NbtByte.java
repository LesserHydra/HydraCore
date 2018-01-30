package com.lesserhydra.bukkitutil.nbt;

public class NbtByte implements NbtNumber {
  
  private final byte value;
  
  public NbtByte(byte value) {
    this.value = value;
  }
  
  public NbtType getType() {
    return NbtType.BYTE;
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
    return new NbtByte(value);
  }
  
}
