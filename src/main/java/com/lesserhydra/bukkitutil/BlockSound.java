package com.lesserhydra.bukkitutil;

import org.bukkit.Sound;
import org.jetbrains.annotations.Contract;


@SuppressWarnings({"unused", "WeakerAccess"})
public enum BlockSound {
  
  WOOD    (1.0F, 1.0F, Sound.BLOCK_WOOD_BREAK,   Sound.BLOCK_WOOD_STEP,   Sound.BLOCK_WOOD_PLACE,   Sound.BLOCK_WOOD_HIT,   Sound.BLOCK_WOOD_FALL),
  GRAVEL  (1.0F, 1.0F, Sound.BLOCK_GRAVEL_BREAK, Sound.BLOCK_GRAVEL_STEP, Sound.BLOCK_GRAVEL_PLACE, Sound.BLOCK_GRAVEL_HIT, Sound.BLOCK_GRAVEL_FALL),
  GRASS   (1.0F, 1.0F, Sound.BLOCK_GRASS_BREAK,  Sound.BLOCK_GRASS_STEP,  Sound.BLOCK_GRASS_PLACE,  Sound.BLOCK_GRASS_HIT,  Sound.BLOCK_GRASS_FALL),
  STONE   (1.0F, 1.0F, Sound.BLOCK_STONE_BREAK,  Sound.BLOCK_STONE_STEP,  Sound.BLOCK_STONE_PLACE,  Sound.BLOCK_STONE_HIT,  Sound.BLOCK_STONE_FALL),
  METAL   (1.0F, 1.5F, Sound.BLOCK_METAL_BREAK,  Sound.BLOCK_METAL_STEP,  Sound.BLOCK_METAL_PLACE,  Sound.BLOCK_METAL_HIT,  Sound.BLOCK_METAL_FALL),
  GLASS   (1.0F, 1.0F, Sound.BLOCK_GLASS_BREAK,  Sound.BLOCK_GLASS_STEP,  Sound.BLOCK_GLASS_PLACE,  Sound.BLOCK_GLASS_HIT,  Sound.BLOCK_GLASS_FALL),
  CLOTH   (1.0F, 1.0F, Sound.BLOCK_CLOTH_BREAK,  Sound.BLOCK_CLOTH_STEP,  Sound.BLOCK_CLOTH_PLACE,  Sound.BLOCK_CLOTH_HIT,  Sound.BLOCK_CLOTH_FALL),
  SAND    (1.0F, 1.0F, Sound.BLOCK_SAND_BREAK,   Sound.BLOCK_SAND_STEP,   Sound.BLOCK_SAND_PLACE,   Sound.BLOCK_SAND_HIT,   Sound.BLOCK_SAND_FALL),
  SNOW    (1.0F, 1.0F, Sound.BLOCK_SNOW_BREAK,   Sound.BLOCK_SNOW_STEP,   Sound.BLOCK_SNOW_PLACE,   Sound.BLOCK_SNOW_HIT,   Sound.BLOCK_SNOW_FALL),
  LADDER  (1.0F, 1.0F, Sound.BLOCK_LADDER_BREAK, Sound.BLOCK_LADDER_STEP, Sound.BLOCK_LADDER_PLACE, Sound.BLOCK_LADDER_HIT, Sound.BLOCK_LADDER_FALL),
  ANVIL   (0.3F, 1.0F, Sound.BLOCK_ANVIL_BREAK,  Sound.BLOCK_ANVIL_STEP,  Sound.BLOCK_ANVIL_PLACE,  Sound.BLOCK_ANVIL_HIT,  Sound.BLOCK_ANVIL_FALL),
  SLIME   (1.0F, 1.0F, Sound.BLOCK_SLIME_BREAK,  Sound.BLOCK_SLIME_STEP,  Sound.BLOCK_SLIME_PLACE,  Sound.BLOCK_SLIME_HIT,  Sound.BLOCK_SLIME_FALL);
  
  
  private final SoundEffect breakSound;
  private final SoundEffect stepSound;
  private final SoundEffect placeSound;
  private final SoundEffect hitSound;
  private final SoundEffect fallSound;
  
  BlockSound(float volume, float pitch, Sound breakSound, Sound stepSound, Sound placeSound, Sound hitSound, Sound fallSound) {
    this.breakSound = new SoundEffect(breakSound, volume, pitch); //TODO: ???
    this.stepSound = new SoundEffect(stepSound, volume * 0.15F, pitch);
    this.placeSound = new SoundEffect(placeSound, (volume + 1.0F) / 2.0F, pitch * 0.8F);
    this.hitSound = new SoundEffect(hitSound, volume, pitch); //TODO: ???
    this.fallSound = new SoundEffect(fallSound, volume * 0.5F, pitch * 0.75F);
  }
  
  @Contract(pure = true)
  public SoundEffect getBreakEffect() {
    return breakSound;
  }
  
  @Contract(pure = true)
  public SoundEffect getStepEffect() {
    return stepSound;
  }
  
  @Contract(pure = true)
  public SoundEffect getPlaceEffect() {
    return placeSound;
  }
  
  @Contract(pure = true)
  public SoundEffect getHitEffect() {
    return hitSound;
  }
  
  @Contract(pure = true)
  public SoundEffect getFallEffect() {
    return fallSound;
  }
  
}
