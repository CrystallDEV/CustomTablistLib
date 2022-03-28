package dev.crystall.customtablistlib.api.manager;

import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import dev.crystall.customtablistlib.api.data.TablistEntry;
import org.bukkit.entity.Player;

/**
 * Created by CrystallDEV on 28/03/2022
 */
public interface PacketManager {

  static void sendPlayerInfoPacket(Player player, TablistEntry entry, PlayerInfoAction addPlayer) {
  }

  static void sendHeaderFooter(Player player, WrappedChatComponent header, WrappedChatComponent footer) {
  }

}
