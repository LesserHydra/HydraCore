package com.lesserhydra.bukkitutil.volatilecode;

import com.lesserhydra.bukkitutil.BlockSound;
import com.lesserhydra.util.MapBuilder;
import net.minecraft.server.v1_12_R1.Block;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.IBlockData;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.SoundEffectType;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@SuppressWarnings({"WeakerAccess", "unused"})
public class BlockUtilNMS {
  
  private static Method BLOCK_GET_SILKTOUCH;
  
  static {
    try {
      //OBF: getSilktouched(IBlockData) Line 521, gets block as silktouched item
      BLOCK_GET_SILKTOUCH = Block.class.getDeclaredMethod("u", IBlockData.class);
      BLOCK_GET_SILKTOUCH.setAccessible(true);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
  }
  
  private static final Map<SoundEffectType, BlockSound> typeMap =
      MapBuilder.init(HashMap<SoundEffectType, BlockSound>::new)
          .put(SoundEffectType.a, BlockSound.WOOD)
          .put(SoundEffectType.b, BlockSound.GRAVEL)
          .put(SoundEffectType.c, BlockSound.GRASS)
          .put(SoundEffectType.d, BlockSound.STONE)
          .put(SoundEffectType.e, BlockSound.METAL)
          .put(SoundEffectType.f, BlockSound.GLASS)
          .put(SoundEffectType.g, BlockSound.CLOTH)
          .put(SoundEffectType.h, BlockSound.SAND)
          .put(SoundEffectType.i, BlockSound.SNOW)
          .put(SoundEffectType.j, BlockSound.LADDER)
          .put(SoundEffectType.k, BlockSound.ANVIL)
          .put(SoundEffectType.l, BlockSound.SLIME)
          .buildImmutable();
  
  public static float getTrueTemperature(org.bukkit.block.Block bukkitBlock) {
    BlockPosition blockPosition = new BlockPosition(bukkitBlock.getX(), bukkitBlock.getY(), bukkitBlock.getZ());
    World world = ((CraftWorld)bukkitBlock.getWorld()).getHandle();
    return world.getBiome(blockPosition).a(blockPosition); //OBF: Line 156, getTrueTemperature()
  }
  
  public static BlockSound getBlockSound(org.bukkit.Material blockType) {
    Block block = CraftMagicNumbers.getBlock(blockType);
    return typeMap.get(block.getStepSound());
  }
  
  public static boolean isUnbreakable(org.bukkit.Material bukkitMaterial) {
    return getBlockStrength(CraftMagicNumbers.getBlock(bukkitMaterial)) < 0; //-1 is unbreakable
  }
  
  public static boolean isInstantlyBreakable(org.bukkit.Material bukkitMaterial) {
    return getBlockStrength(CraftMagicNumbers.getBlock(bukkitMaterial)) == 0;
  }
  
  public static Collection<org.bukkit.inventory.ItemStack> getDrops(org.bukkit.block.Block bukkitBlock, boolean silkTouch, int fortuneLevel) {
    //Alright, this whole thing is INCREDIBLY hacky.
    //The code for deciding what drops from a block is tied into actually dropping it...
    //...And that code is specialized for many blocks.
    //TODO: Surely there's a better way to do this?
    
    Block block = CraftMagicNumbers.getBlock(bukkitBlock.getType());
    World world = ((CraftWorld)bukkitBlock.getWorld()).getHandle();
    IBlockData blockData = ((CraftChunk)bukkitBlock.getChunk()).getHandle().getBlockData(new BlockPosition(bukkitBlock.getX(), bukkitBlock.getY(), bukkitBlock.getZ()));//world.c();
    
    //Silk touch
    if (silkTouch && isSilkTouchable(block)) {
      return Collections.singletonList(CraftItemStack.asBukkitCopy(silkTouch(block, blockData)));
    }
    
    //Take note of current entities
    Collection<org.bukkit.entity.Entity> before = bukkitBlock.getWorld().getNearbyEntities(bukkitBlock.getLocation().add(0.5, 0.5, 0.5), 0.5, 0.5, 0.5);
    
    //Drop block items without breaking the block
    //No, you can't just copy the implementation; there are too many specializations.
    block.dropNaturally(world, new BlockPosition(bukkitBlock.getX(), bukkitBlock.getY(), bukkitBlock.getZ()), blockData, 1.0F, fortuneLevel);
    
    //Get new entities
    Collection<org.bukkit.entity.Entity> after = bukkitBlock.getWorld().getNearbyEntities(bukkitBlock.getLocation().add(0.5, 0.5, 0.5), 0.5, 0.5, 0.5);
    after.removeAll(before);
    
    //Get itemstacks
    Collection<org.bukkit.inventory.ItemStack> results = after.stream()
        .filter(entity -> entity instanceof org.bukkit.entity.Item)
        .map(itemEntity -> ((org.bukkit.entity.Item)itemEntity).getItemStack())
        .collect(Collectors.toList());
    
    //Remove item entities from the world
    after.stream()
        .filter(entity -> entity instanceof org.bukkit.entity.Item)
        .forEach(org.bukkit.entity.Entity::remove);
    
    return results;
  }
  
  
  @SuppressWarnings({"deprecation", "RedundantCast"})
  private static float getBlockStrength(Block block) {
    //Parameters are never used, but types are needed to differentiate between methods
    return block.a((IBlockData)null, (World)null, (BlockPosition)null); //OBF: Line 278, getStrength()
  }
  
  private static boolean isSilkTouchable(Block block) {
    //OBF: isSilktouchable() Line 557, checks if block can be silktouched (false for spawners and the like)
    return block.o();
  }
  
  private static ItemStack silkTouch(Block block, IBlockData data) {
    try {
      return (ItemStack) BLOCK_GET_SILKTOUCH.invoke(block, data);
    }
    catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
  
}
