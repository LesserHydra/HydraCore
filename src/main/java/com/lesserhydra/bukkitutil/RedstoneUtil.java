package com.lesserhydra.bukkitutil;

import com.lesserhydra.bukkitutil.volatilecode.RedstoneNMS;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.Diode;

import java.util.Arrays;


/**
 * A collection of misc utilities dealing with redstone.
 */
public class RedstoneUtil {
  
  /**
   * Checks if the repeater at the given location is locked from the side.
   *
   * @param diodeBlock Block where the repeater to check is
   * @return Whether the given repeater is locked
   */
  public static boolean diodeIsLocked(Block diodeBlock) {
    return Arrays.stream(BlockUtil.getHorizontalSides(((Diode) diodeBlock.getState().getData()).getFacing()))
        .map(diodeBlock::getRelative)
        .filter(block -> block.getType() == Material.DIODE_BLOCK_ON)
        .anyMatch(block -> block.getRelative(((Diode) block.getState().getData()).getFacing()).equals(diodeBlock));
  }
  
  public static void activateRepeater(Block diodeBlock) {
    RedstoneNMS.activateRepeater(diodeBlock);
  }
  
}
