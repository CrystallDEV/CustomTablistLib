package dev.crystall;

import dev.crystall.customtablistlib.TablistLibrary;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TablistTester extends JavaPlugin implements Listener {

  private TablistLibrary tablistLibrary;

  @Override
  public void onEnable() {
    // Plugin startup logic
    tablistLibrary = new TablistLibrary(this);
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }


}
