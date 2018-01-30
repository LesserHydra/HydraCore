package com.lesserhydra.bukkitutil;

import org.bukkit.inventory.ItemStack;

import java.util.Arrays;


public class CraftingGrid {
  
  private ItemStack[] contents;
  private ItemStack result = null;
  
  public CraftingGrid(ItemStack[] contents) {
    assert contents.length == 9;
    this.contents = Arrays.copyOf(contents, 9);
  }
  
  public void setContents(ItemStack[] contents) {
    this.contents = contents;
  }
  
  public ItemStack[] getContents() {
    return contents;
  }
  
  public void setResult(ItemStack result) {
    this.result = result;
  }
  
  public ItemStack getResult() {
    return result;
  }
  
  public ItemStack get(int i) {
    return contents[i];
  }
  
  public void set(int i, ItemStack item) {
    contents[i] = item;
  }
  
  public boolean isSuccess() {
    return result != null;
  }
  
}
