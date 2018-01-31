package com.lesserhydra.bukkitutil.volatilecode;

import net.minecraft.server.v1_12_R1.ItemStack;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Map;


@SuppressWarnings({"WeakerAccess", "unused"})
public class MirrorItemStack extends org.bukkit.inventory.ItemStack {
  
  private CraftItemStack handle;
  
  public MirrorItemStack(@NotNull CraftItemStack handle) {
    this.handle = handle;
  }
  
  public CraftItemStack getCraftHandle() {
    return handle;
  }
  
  public ItemStack getNmsHandle() {
    return InventoryUtilNMS.getNmsItem(handle);
  }
  
  public int getTypeId() {
    return handle.getTypeId();
  }
  
  public void setTypeId(int type) {
    handle.setTypeId(type);
  }
  
  public int getAmount() {
    return handle.getAmount();
  }
  
  public void setAmount(int amount) {
    handle.setAmount(amount);
  }
  
  public void setDurability(short durability) {
    handle.setDurability(durability);
  }
  
  public short getDurability() {
    return handle.getDurability();
  }
  
  public int getMaxStackSize() {
    return handle.getMaxStackSize();
  }
  
  public void addUnsafeEnchantment(Enchantment ench, int level) {
    handle.addUnsafeEnchantment(ench, level);
  }
  
  public boolean containsEnchantment(Enchantment ench) {
    return handle.containsEnchantment(ench);
  }
  
  public int getEnchantmentLevel(Enchantment ench) {
    return handle.getEnchantmentLevel(ench);
  }
  
  public int removeEnchantment(Enchantment ench) {
    return handle.removeEnchantment(ench);
  }
  
  public Map<Enchantment, Integer> getEnchantments() {
    return handle.getEnchantments();
  }
  
  public MirrorItemStack clone() {
    MirrorItemStack result = (MirrorItemStack)super.clone();
    result.handle = handle.clone();
    return result;
  }
  
  public ItemMeta getItemMeta() {
    return handle.getItemMeta();
  }
  
  public boolean setItemMeta(ItemMeta itemMeta) {
    return handle.setItemMeta(itemMeta);
  }
  
  public boolean isSimilar(org.bukkit.inventory.ItemStack stack) {
    return handle.isSimilar(stack);
  }
  
  public boolean hasItemMeta() {
    return handle.hasItemMeta();
  }
  
}
