package dev.crystall.customtablistlib.api.data;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import java.util.UUID;
import lombok.Getter;

/**
 * Created by CrystallDEV on 26/11/2020
 */
@Getter
public class TablistEntry {

  private final int index;
  private final String key;
  private final String content;
  private final WrappedGameProfile gameProfile;

  public TablistEntry(int index, String content) {
    this.index = index;
    this.content = content;
    this.key = "#TBL-" + (index >= 10 ? index : "0" + index);
    this.gameProfile = new WrappedGameProfile(UUID.randomUUID(), key);
  }

}
