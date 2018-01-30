package com.lesserhydra.bukkitutil.volatilecode;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_12_R1.EntityHuman;
import net.minecraft.server.v1_12_R1.World;


@SuppressWarnings("WeakerAccess")
public class DummyHuman extends EntityHuman {
  
  public DummyHuman(World world, GameProfile gameprofile) {
    super(world, gameprofile);
  }
  
  @Override
  public boolean isSpectator() {
    return false;
  }
  
  @Override
  //OBF: Line 1638, isCreative()
  public boolean z() {
    return false;
  }
  
}
