package com.lesserhydra.hydracore;

import com.lesserhydra.util.Version;
import org.bukkit.plugin.java.JavaPlugin;


@SuppressWarnings({"WeakerAccess", "unused"})
public class HydraCore extends JavaPlugin {
  
  private static HydraCore instance = null;
  private Version version;
  
  @Override
  public void onEnable() {
    instance = this;
    version = Version.fromString(getDescription().getVersion());
    assert version != null;
  }
  
  @Override
  public void onDisable() {
    instance = null;
  }
  
  public Version getVersion() {
    return version;
  }
  
  public static boolean isLoaded() {
    return instance != null;
  }
  
  public static HydraCore getInstance() {
    return instance;
  }
  
  public static Version.Compat expectVersion(int major, int minor) {
    return getInstance().getVersion().expectVersion(major, minor);
  }
  
}
