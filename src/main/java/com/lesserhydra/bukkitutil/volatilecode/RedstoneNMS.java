package com.lesserhydra.bukkitutil.volatilecode;

import com.lesserhydra.bukkitutil.RedstoneUtil;
import net.minecraft.server.v1_12_R1.Block;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
import org.bukkit.material.Diode;


public class RedstoneNMS {
	
	public static void activateRepeater(org.bukkit.block.Block diodeBlock) {
		if (RedstoneUtil.diodeIsLocked(diodeBlock)) return;
		
		Diode diode = (Diode) diodeBlock.getState().getData();
		Block block = CraftMagicNumbers.getBlock(diodeBlock.getType());
		World world = ((CraftWorld)diodeBlock.getWorld()).getHandle();
		BlockPosition blockposition = new BlockPosition(diodeBlock.getX(), diodeBlock.getY(), diodeBlock.getZ());
		
		world.a(blockposition, block, diode.getDelay() * 2, -1); //OBF: Line 523, Update diode state
	}
	
}
