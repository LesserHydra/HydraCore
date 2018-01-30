package com.lesserhydra.bukkitutil.nbt;

public class NbtString implements NbtBase {
  
  private final String value;
  
  public NbtString(String value) {
    this.value = value;
  }
  
  public String getString() {
    return value;
  }
  
  @Override
  public NbtType getType() {
    return NbtType.STRING;
  }
  
  @Override
  public boolean isEmpty() {
    return value.isEmpty();
  }
  
  @Override
  public NbtString clone() {
    return new NbtString(value);
  }
  
}
