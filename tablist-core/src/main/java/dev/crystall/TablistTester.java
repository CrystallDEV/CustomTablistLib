package dev.crystall;

import dev.crystall.customtablistlib.TablistLibrary;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TablistTester extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    TablistLibrary.init(this);
  }

}
