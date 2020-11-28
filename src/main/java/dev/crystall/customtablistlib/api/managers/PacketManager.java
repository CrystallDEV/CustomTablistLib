package dev.crystall.customtablistlib.api.managers;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.NativeGameMode;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import dev.crystall.customtablistlib.TablistLibrary;
import dev.crystall.customtablistlib.api.data.TablistEntry;
import dev.crystall.customtablistlib.nms.wrappers.WrapperPlayServerPlayerInfo;
import dev.crystall.customtablistlib.nms.wrappers.WrapperPlayServerPlayerListHeaderFooter;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import net.minecraft.server.v1_16_R3.BiomeManager;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayOutLogin;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by CrystallDEV on 26/11/2020
 */
public class PacketManager {

  public PacketManager() {

  }

  /**
   * Sends packets to either add or remove the given entry from the tablist
   *
   * @param player
   * @param entry
   * @param action
   */
  public static void sendPlayerInfoPacket(Player player, TablistEntry entry, PlayerInfoAction action) {
    WrapperPlayServerPlayerInfo infoWrapper = new WrapperPlayServerPlayerInfo();
    PlayerInfoData data = new PlayerInfoData(entry.getGameProfile(), 1, NativeGameMode.NOT_SET, WrappedChatComponent.fromText(entry.getContent()));
    infoWrapper.setData(Collections.singletonList(data));
    infoWrapper.setAction(action);
    sendPacket(player, infoWrapper.getHandle(), false);
  }

  public static void sendHeaderFooter(Player player) {
    WrapperPlayServerPlayerListHeaderFooter headerFooterPacket = new WrapperPlayServerPlayerListHeaderFooter();
    headerFooterPacket.setHeader(WrappedChatComponent.fromText("Age of Battle"));
    headerFooterPacket.setFooter(WrappedChatComponent.fromText("This is a footer"));
    sendPacket(player, headerFooterPacket.getHandle(), false);
  }

  /**
   * Sends the given packet to the given player
   *
   * @param player
   * @param packetContainer
   */
  private static void sendPacket(Player player, PacketContainer packetContainer, boolean debug) {
    try {
      ProtocolLibrary.getProtocolManager().sendServerPacket(player, packetContainer);

      if (debug) {
        TablistLibrary.getPlugin().getServer().getConsoleSender().sendMessage(
          "Sent packet " + packetContainer.getType().name() + " to " + player.getDisplayName()
        );
      }
    } catch (InvocationTargetException e) {
      throw new RuntimeException("Cannot send packet " + packetContainer, e);
    }
  }


}
