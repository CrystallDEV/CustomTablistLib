package dev.crystall.customtablistlib.api.packet;

import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import java.util.List;

/**
 * Created by CrystallDEV on 22/01/2022
 */
public interface PlayerInfoPacket {

  PlayerInfoAction getAction();

  void setAction(PlayerInfoAction value);

  List<PlayerInfoData> getData();

  void setData(List<PlayerInfoData> value);

}
