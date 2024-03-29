/**
 * PacketWrapper - ProtocolLib wrappers for Minecraft packets
 * Copyright (C) dmulloy2 <http://dmulloy2.net>
 * Copyright (C) Kristian S. Strangeland
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package dev.crystall.customtablistlib.nms.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import dev.crystall.customtablistlib.api.packet.PlayerInfoPacket;
import java.util.List;

public class WrapperPlayServerPlayerInfo extends AbstractPacket implements PlayerInfoPacket {

  public static final PacketType TYPE = Server.PLAYER_INFO;

  public WrapperPlayServerPlayerInfo() {
    super(new PacketContainer(TYPE), TYPE);
    handle.getModifier().writeDefaults();
  }

  public WrapperPlayServerPlayerInfo(PacketContainer packet) {
    super(packet, TYPE);
  }

  @Override
  public PlayerInfoAction getAction() {
    return handle.getPlayerInfoAction().read(0);
  }

  @Override
  public void setAction(PlayerInfoAction value) {
    handle.getPlayerInfoAction().write(0, value);
  }

  @Override
  public List<PlayerInfoData> getData() {
    return handle.getPlayerInfoDataLists().read(0);
  }

  @Override
  public void setData(List<PlayerInfoData> value) {
    handle.getPlayerInfoDataLists().write(0, value);
  }

}
