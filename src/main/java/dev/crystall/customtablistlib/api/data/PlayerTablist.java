package dev.crystall.customtablistlib.api.data;

import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import dev.crystall.customtablistlib.api.managers.PacketManager;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

/**
 * Created by CrystallDEV on 28/11/2020
 */
@Getter
public class PlayerTablist {

  private final static int MAX_PLAYER_COUNT = 80;

  private final Player player;
  private final List<TablistEntry> tablistEntries;
  @Setter
  private WrappedChatComponent header = WrappedChatComponent.fromText("This is a header");
  @Setter
  private WrappedChatComponent footer = WrappedChatComponent.fromText("This is a footer");

  public PlayerTablist(Player player) {
    this.player = player;
    this.tablistEntries = initEntryList();
  }

  /**
   * Sets an entry in the tablist entries for the given player. If the parameter update is true, then we also upadate the whole tablist for the player
   *
   * @param entry
   * @param update
   */
  public void setEntry(TablistEntry entry, boolean update) {
    if (update) {
      TablistEntry existingEntry = tablistEntries.get(entry.getIndex());
      if (existingEntry != null) {
        PacketManager.sendPlayerInfoPacket(player, existingEntry, PlayerInfoAction.REMOVE_PLAYER);
      }
      PacketManager.sendPlayerInfoPacket(player, entry, PlayerInfoAction.ADD_PLAYER);
    }
    tablistEntries.set(entry.getIndex(), entry);
  }

  /**
   * Adds an entry to the tablist entries for the given player. If the parameter update is true, then we also upadate the whole tablist for the player
   *
   * @param entry
   * @param update
   */
  public void addEntry(TablistEntry entry, boolean update) {
    tablistEntries.add(entry);
    if (update) {
      updateTablist();
    }
  }

  public void removeEntry(TablistEntry entry, boolean update) {
    tablistEntries.remove(entry);
    if (update) {
      updateTablist();
    }
  }

  public TablistEntry getEntryByReferenceKey(String referenceKey) {
    for (TablistEntry entry : tablistEntries) {
      if (entry.getReferenceKey().contentEquals(referenceKey)) {
        return entry;
      }
    }
    return null;
  }

  public void initTablist() {
    for (TablistEntry entry : tablistEntries) {
      PacketManager.sendPlayerInfoPacket(player, entry, PlayerInfoAction.ADD_PLAYER);
    }
    PacketManager.sendHeaderFooter(player, header, footer);
  }

  public void updateTablist() {
    for (TablistEntry entry : tablistEntries) {
      PacketManager.sendPlayerInfoPacket(player, entry, PlayerInfoAction.UPDATE_DISPLAY_NAME);
    }
    PacketManager.sendHeaderFooter(player, header, footer);
  }

  private List<TablistEntry> initEntryList() {
    List<TablistEntry> entries = new ArrayList<>();
    for (int i = 0; i < MAX_PLAYER_COUNT; i++) {
      entries.add(new TablistEntry(i, ""));
    }

    return entries;
  }

}
