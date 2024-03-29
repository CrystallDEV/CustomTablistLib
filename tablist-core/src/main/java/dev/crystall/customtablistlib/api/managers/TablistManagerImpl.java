package dev.crystall.customtablistlib.api.managers;

import dev.crystall.customtablistlib.api.data.PlayerTablist;
import dev.crystall.customtablistlib.api.manager.TablistManager;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by CrystallDEV on 26/11/2020
 */
public class TablistManagerImpl implements TablistManager, Listener {

  private static final Map<UUID, PlayerTablist> tablistEntryMap = new HashMap<>();

  @Override
  public void addPlayer(Player player) {
    if (!tablistEntryMap.containsKey(player.getUniqueId())) {
      tablistEntryMap.put(player.getUniqueId(), new PlayerTablist(player));
    }
  }

  @Override
  public void removePlayer(Player player) {
    tablistEntryMap.remove(player.getUniqueId());
  }

  @Override
  public void updatePlayerTablist(Player player) {
    PlayerTablist playerTablist = tablistEntryMap.get(player.getUniqueId());
    if (playerTablist != null) {
      playerTablist.updateTablist();
    }
  }

  @Override
  public PlayerTablist getPlayerTablist(Player player) {
    return getPlayerTablist(player.getUniqueId());
  }

  @Override
  public PlayerTablist getPlayerTablist(UUID uuid) {
    return tablistEntryMap.get(uuid);
  }

  // TODO add option to add variable and check for it and replace it in an entry
}
