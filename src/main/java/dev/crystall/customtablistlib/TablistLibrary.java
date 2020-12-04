package dev.crystall.customtablistlib;

import dev.crystall.customtablistlib.api.managers.EventManager;
import dev.crystall.customtablistlib.api.managers.TablistManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by CrystallDEV on 26/11/2020
 */
public class TablistLibrary {

  /**
   * The plugin that uses this library
   */
  @Getter
  private static JavaPlugin plugin;

  /**
   * Manages all events happening in this library
   */
  @Getter
  private static final EventManager eventManager = new EventManager();

  /**
   * Manages everything that has to do with the tablist objects
   */
  @Getter
  private static final TablistManager tablistManager = new TablistManager();

  public static void init(JavaPlugin plugin) {
    TablistLibrary.plugin = plugin;
  }
}
