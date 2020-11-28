package dev.crystall.customtablistlib.api.managers;

import dev.crystall.customtablistlib.api.data.PlayerTablist;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by CrystallDEV on 26/11/2020
 */
public class TablistManager implements Listener {

  private static final Map<UUID, PlayerTablist> tablistEntryMap = new HashMap<>();


  public void addPlayer(Player player) {
    if (!tablistEntryMap.containsKey(player.getUniqueId())) {
      tablistEntryMap.put(player.getUniqueId(), new PlayerTablist(player));
    }
  }

  public void removePlayer(Player player) {
    tablistEntryMap.remove(player.getUniqueId());
  }

  public void updatePlayerTablist(Player player) {
    PlayerTablist playerTablist = tablistEntryMap.get(player.getUniqueId());
    if (playerTablist != null) {
      playerTablist.updateTablist();
    }
  }

  // TODO add option to add variable and check for it and replace it in an entry
}
