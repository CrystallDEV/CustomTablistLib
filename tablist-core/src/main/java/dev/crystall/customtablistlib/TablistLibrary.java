package dev.crystall.customtablistlib;

import dev.crystall.customtablistlib.api.managers.EventManager;
import dev.crystall.customtablistlib.api.managers.TablistManagerImpl;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by CrystallDEV on 26/11/2020
 */
public class TablistLibrary {

  /**
   * The plugin that uses this library
   */
  @Getter
  @Setter
  private static JavaPlugin plugin;

  /**
   * Manages all events happening in this library
   */
  @Getter
  private static EventManager eventManager;

  /**
   * Manages everything that has to do with the tablist objects
   */
  @Getter
  private static TablistManagerImpl tablistManager;

  private TablistLibrary() {
  }

  public static void init(JavaPlugin plugin) {
    TablistLibrary.setPlugin(plugin);
    tablistManager = new TablistManagerImpl();
    eventManager = new EventManager();
  }
}
