package dev.crystall.customtablistlib.api.packet;

import com.comphenix.protocol.wrappers.WrappedChatComponent;

/**
 * Created by CrystallDEV on 22/01/2022
 */
public interface PlayerListHeaderFooterPacket {

  /**
   * Retrieve Header.
   *
   * @return The current Header
   */
  WrappedChatComponent getHeader();

  /**
   * Set Header.
   *
   * @param value - new value.
   */
  void setHeader(WrappedChatComponent value);

  /**
   * Retrieve Footer.
   *
   * @return The current Footer
   */
  WrappedChatComponent getFooter();

  /**
   * Set Footer.
   *
   * @param value - new value.
   */
  void setFooter(WrappedChatComponent value);

}
