package dev.crystall.customtablistlib.api.managers;

import dev.crystall.customtablistlib.TablistLibrary;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by CrystallDEV on 26/11/2020
 */
public class EventManager implements Listener {


  public EventManager() {
    TablistLibrary.getPlugin().getServer().getPluginManager().registerEvents(this, TablistLibrary.getPlugin());
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    TablistLibrary.getTablistManager().addPlayer(event.getPlayer());
    TablistLibrary.getTablistManager().updatePlayerTablist(event.getPlayer());
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    TablistLibrary.getTablistManager().removePlayer(event.getPlayer());
  }

}
