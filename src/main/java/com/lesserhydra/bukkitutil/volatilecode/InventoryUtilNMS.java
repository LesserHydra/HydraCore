package com.lesserhydra.bukkitutil.volatilecode;

import com.lesserhydra.bukkitutil.CraftingGrid;
import com.lesserhydra.bukkitutil.nbt.NbtCompound;
import com.lesserhydra.bukkitutil.nbt.volatilecode.CraftNbtCompound;
import net.minecraft.server.v1_12_R1.Container;
import net.minecraft.server.v1_12_R1.CraftingManager;
import net.minecraft.server.v1_12_R1.EntityHuman;
import net.minecraft.server.v1_12_R1.IRecipe;
import net.minecraft.server.v1_12_R1.InventoryCraftResult;
import net.minecraft.server.v1_12_R1.InventoryCrafting;
import net.minecraft.server.v1_12_R1.Item;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.Items;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NonNullList;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.List;


@SuppressWarnings({"WeakerAccess", "unused"})
public class InventoryUtilNMS {
  
  private static final Field CraftItemStack_handle;
  
  static {
    try {
      CraftItemStack_handle = CraftItemStack.class.getDeclaredField("handle");
      CraftItemStack_handle.setAccessible(true);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static final ItemStack EMPTY_ITEM = ItemStack.a; //OBF: ItemStack.AIR;
  
  public static CraftItemStack getCraftItemStack(org.bukkit.inventory.ItemStack item) {
    if (item instanceof CraftItemStack) return (CraftItemStack) item;
    return CraftItemStack.asCraftCopy(item);
  }
  
  @Nullable
  public static NbtCompound getItemTag(org.bukkit.inventory.ItemStack bukkitItem, boolean createIfMissing) {
    ItemStack item = getNmsItem(bukkitItem);
    NBTTagCompound itemTag = item.getTag();
    if (itemTag == null && createIfMissing) {
      itemTag = new NBTTagCompound();
      item.setTag(itemTag);
    }
    return itemTag == null ? null : new CraftNbtCompound(itemTag);
  }
  
  public static void setItemTag(org.bukkit.inventory.ItemStack bukkitItem, NbtCompound itemTag) {
    ItemStack item = getNmsItem(bukkitItem);
    NBTTagCompound nmsTag = ((CraftNbtCompound)itemTag).getHandle();
    item.setTag(nmsTag);
  }
  
  @NotNull
  public static org.bukkit.inventory.ItemStack removeCustomTag(org.bukkit.inventory.ItemStack item, String key) {
    ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
    NBTTagCompound compound = nmsItem.getTag();
    if (compound == null) return item;
    compound.remove(key);
    if (compound.isEmpty()) nmsItem.setTag(null);
    
    return CraftItemStack.asBukkitCopy(nmsItem);
  }
  
  public static int getMissingStack(org.bukkit.inventory.Inventory inventory, org.bukkit.inventory.ItemStack missingItem) {
    ItemStack nmsMissing = CraftItemStack.asNMSCopy(missingItem);
    List<ItemStack> contents = ((CraftInventory) inventory).getInventory().getContents();
    for (int i = 0; i < contents.size(); ++i) {
      ItemStack stack = contents.get(i);
      if (stack.getCount() == 0 && stack.doMaterialsMatch(nmsMissing)) return i;
    }
    return -1;
  }
  
  public static CraftingGrid craft(CraftingGrid grid, org.bukkit.World bukkitWorld) {
    //Create inventory
    InventoryCrafting invCraft = new InventoryCrafting(new Container() {
      @Override
      public InventoryView getBukkitView() {
        //TODO: How?
        return null;
      }
      
      @Override
      public boolean canUse(EntityHuman entityHuman) {
        return false;
      }
    }, 3, 3);
    InventoryCraftResult resultInventory = new InventoryCraftResult();
    invCraft.resultInventory = resultInventory;
    for (int i = 0; i < 9; i++) {
      ItemStack item = CraftItemStack.asNMSCopy(grid.get(i));
      if (item.getCount() > 1) item.setCount(1);
      invCraft.setItem(i, item);
    }
    
    ItemStack craftingResult = EMPTY_ITEM;
    
    //Find recipe
    World world = ((CraftWorld) bukkitWorld).getHandle();
    IRecipe recipe = CraftingManager.b(invCraft, world); //OBF: Line 180, static findRecipe(InventoryCrafting, World)
    if (recipe != null) {
      //Set result from recipe
      resultInventory.a(recipe); //OBF: Line 133, setRecipe
      craftingResult = recipe.craftItem(invCraft); //Gets result, does not use items
    }
    
    //Run through Bukkit PrepareItemCraftEvent
    //TODO: Listener may assume view and player is non-null.
    craftingResult = CraftEventFactory.callPreCraftEvent(invCraft, craftingResult, null, false);
    resultInventory.setItem(0, craftingResult);
    
    //TODO: Run through Bukkit CraftItemEvent?
    
    //Cancel if no result
    if (craftingResult == EMPTY_ITEM) return grid;
    
    //Build results
    grid.setResult(CraftItemStack.asCraftMirror(craftingResult));
    if (recipe != null) {
      //TODO: Respect inventory changes from PreCraftEvent
      NonNullList<ItemStack> remainder = recipe.b(invCraft); //OBF: Line 27, Get inventory remainder
      for (int i = 0; i < remainder.size(); ++i) {
        ItemStack item = remainder.get(i);
        org.bukkit.inventory.ItemStack gridItem = grid.get(i);
        // Result item seems to only handle explicitly changed items
        if (item == EMPTY_ITEM && gridItem.getAmount() > 1) gridItem.setAmount(gridItem.getAmount() - 1);
        else grid.set(i, CraftItemStack.asCraftMirror(remainder.get(i)));
      }
    }
    return grid;
  }
  
  public static boolean isItemAir(Item item) {
    return item == Items.a; //OBF: Items.AIR
  }
  
  public static ItemStack getNmsItem(org.bukkit.inventory.ItemStack bukkitItem) {
    if (!(bukkitItem instanceof CraftItemStack)) return CraftItemStack.asNMSCopy(bukkitItem);
    CraftItemStack craftItem = (CraftItemStack) bukkitItem;
    ItemStack handle;
    try {
      handle = (ItemStack) CraftItemStack_handle.get(craftItem);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    return handle;
  }
  
}
