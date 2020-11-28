package dev.crystall.customtablistlib.api.managers;

import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import dev.crystall.customtablistlib.api.data.TablistEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by CrystallDEV on 26/11/2020
 */
public class TablistManager implements Listener {

  private static final Map<UUID, List<TablistEntry>> tablistEntryMap = new HashMap<>();


  /**
   * Sets an entry in the tablist entries for the given player. If the parameter update is true, then we also upadate the whole tablist for the player
   *
   * @param player
   * @param entry
   * @param update
   */
  public void setEntry(Player player, TablistEntry entry, boolean update) {
    List<TablistEntry> entries = tablistEntryMap.getOrDefault(player.getUniqueId(), initEntryList());
    entries.set(entry.getIndex(), entry);
    tablistEntryMap.put(player.getUniqueId(), entries);
    if (update) {
      updateTablist(player);
    }
  }

  /**
   * Adds an entry to the tablist entries for the given player. If the parameter update is true, then we also upadate the whole tablist for the player
   *
   * @param player
   * @param entry
   * @param update
   */
  public void addEntry(Player player, TablistEntry entry, boolean update) {
    List<TablistEntry> entries = tablistEntryMap.getOrDefault(player.getUniqueId(), initEntryList());
    entries.add(entry);
    tablistEntryMap.put(player.getUniqueId(), entries);
    if (update) {
      updateTablist(player);
    }
  }

  public void removeEntry(Player player, TablistEntry entry, boolean update) {
    List<TablistEntry> entries = tablistEntryMap.getOrDefault(player.getUniqueId(), initEntryList());
    entries.remove(entry);
    tablistEntryMap.put(player.getUniqueId(), entries);
    if (update) {
      updateTablist(player);
    }
  }

  public void updateTablist(Player player) {
    for (TablistEntry entry : tablistEntryMap.get(player.getUniqueId())) {
      PacketManager.sendPlayerInfoPacket(player, entry, PlayerInfoAction.REMOVE_PLAYER);
      PacketManager.sendPlayerInfoPacket(player, entry, PlayerInfoAction.ADD_PLAYER);
    }
    PacketManager.sendHeaderFooter(player);
  }

  public void addPlayer(Player player) {
    if (!tablistEntryMap.containsKey(player.getUniqueId())) {
      tablistEntryMap.put(player.getUniqueId(), initEntryList());
    }
  }

  public void removePlayer(Player player) {
    tablistEntryMap.remove(player.getUniqueId());
  }

  private List<TablistEntry> initEntryList() {
    List<TablistEntry> entries = new ArrayList<>();
    for (int i = 0; i < 80; i++) {
      entries.add(new TablistEntry(i, ""));
    }

    return entries;
  }

  // TODO add option to add variable and check for it and replace it in an entry
}
