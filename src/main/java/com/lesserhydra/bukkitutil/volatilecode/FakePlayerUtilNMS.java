package com.lesserhydra.bukkitutil.volatilecode;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_12_R1.EntityHuman;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@SuppressWarnings({"WeakerAccess", "unused"})
public class FakePlayerUtilNMS {
  
  //TODO: What affect does this have on the world? Is the dummy saved in-world? Does it have its own save file?
  
  private static final UUID fakePlayerUUID = UUID.fromString("024922ab-a569-447c-ae5b-8f605b852dd7");
  private static final String fakePlayerName = "Gregory";
  private static final GameProfile fakePlayerProfile = new GameProfile(fakePlayerUUID, fakePlayerName);
  
  private static final Map<UUID, DummyHuman> dummyHumansPerWorld = new HashMap<>();
  
  public EntityHuman getDummyEntityHuman(World world) {
    return dummyHumansPerWorld.computeIfAbsent(world.getDataManager().getUUID(),
                                               uuid -> new DummyHuman(world, fakePlayerProfile));
  }
  
  public org.bukkit.entity.HumanEntity getDummyHuman(org.bukkit.World world) {
    return getDummyEntityHuman(((CraftWorld)world).getHandle()).getBukkitEntity();
  }
  
  public org.bukkit.entity.HumanEntity createDummyHuman(org.bukkit.World world) {
    DummyHuman result = new DummyHuman(((CraftWorld)world).getHandle(), fakePlayerProfile);
    return result.getBukkitEntity();
  }
  
  public org.bukkit.entity.HumanEntity createDummyHuman(org.bukkit.World world, UUID uuid, String name) {
    DummyHuman result = new DummyHuman(((CraftWorld)world).getHandle(), new GameProfile(uuid, name));
    return result.getBukkitEntity();
  }
  
}
