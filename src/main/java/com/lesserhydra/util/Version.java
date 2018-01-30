package com.lesserhydra.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings({"WeakerAccess", "unused"})
public class Version {
  
  private final int major;
  private final int minor;
  private final int hotfix;
  private final boolean snapshot;
  
  public Version(int major, int minor, int hotfix) {
    this(major, minor, hotfix, false);
  }
  
  public Version(int major, int minor, int hotfix, boolean snapshot) {
    this.major = major;
    this.minor = minor;
    this.hotfix = hotfix;
    this.snapshot = snapshot;
  }
  
  public int getMajor() {
    return major;
  }
  
  public int getMinor() {
    return minor;
  }
  
  public int getHotfix() {
    return hotfix;
  }
  
  public boolean isSnapshot() {
    return snapshot;
  }
  
  public Compat expectVersion(int major, int minor) {
    if (this.major > major) return Compat.UPDATED_MAJOR;
    if (this.major < major) return Compat.OUTDATED_MAJOR;
    if (this.minor < minor) return Compat.OUTDATED_MINOR;
    return Compat.MATCH;
    //return this.major == major && this.minor >= minor;
  }
  
  //(?<major>\d+)\.(?<minor>\d+)(?:\.(?<hotfix>\d+))?(?<snapshot>-SNAPSHOT)?
  private static final Pattern versionPattern = Pattern.compile("(?<major>\\d+)\\.(?<minor>\\d+)(?:\\.(?<hotfix>\\d+))?(?<snapshot>-SNAPSHOT)?");
  
  public static Version fromString(String versionString) {
    Matcher versionMatcher = versionPattern.matcher(versionString);
    if (!versionMatcher.matches()) return null;
    return new Version(
        Integer.parseInt(versionMatcher.group("major")),
        Integer.parseInt(versionMatcher.group("minor")),
        Integer.parseInt(versionMatcher.group("hotfix")),
        versionMatcher.group("snapshot") != null
    );
  }
  
  public enum Compat {
    MATCH(false),
    UPDATED_MAJOR(false),
    OUTDATED_MAJOR(true),
    OUTDATED_MINOR(true);
    
    private final boolean outdated;
  
    Compat(boolean outdated) {
      this.outdated = outdated;
    }
    
    public boolean isMatching() {
      return this == MATCH;
    }
  
    public boolean isOutdated() {
      return outdated;
    }
    
    public boolean isUpdated() {
      return !isMatching() && !isOutdated();
    }
  }
  
}
