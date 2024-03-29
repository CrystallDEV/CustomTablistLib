package dev.crystall.customtablistlib.api.data;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import dev.crystall.customtablistlib.api.skin.PlayerSkin;
import dev.crystall.customtablistlib.api.skin.SkinFetcher;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by CrystallDEV on 26/11/2020
 */
@Getter
public class TablistEntry {

  private final UUID uuid = UUID.randomUUID();
  private final int index;
  private final String internalKey;
  private final String referenceKey;
  private PlayerSkin playerSkin;

  @Setter
  private String content;

  public TablistEntry(int index, String content) {
    this(index, "\uE83ATBL-" + (index >= 10 ? index : "0" + index), content);
  }

  public TablistEntry(int index, String referenceKey, String content) {
    this.index = index;
    this.content = content;
    this.internalKey = "\uE83ATBL-" + (index >= 10 ? index : "0" + index);
    this.referenceKey = referenceKey;
    setSkin(1);
  }

  public void setSkin(int skinId) {
    setSkin(SkinFetcher.fetchSkin(skinId));
  }

  public void setSkin(PlayerSkin playerSkin) {
    this.playerSkin = playerSkin;
  }

  public WrappedGameProfile getGameProfile() {
    WrappedGameProfile wrappedGameProfile = new WrappedGameProfile(uuid, internalKey);
    if (playerSkin != null) {
      wrappedGameProfile.getProperties().get("textures").clear();
      wrappedGameProfile.getProperties().put("textures", new WrappedSignedProperty("textures", playerSkin.value(), playerSkin.signature()));
    }
    return wrappedGameProfile;
  }

  public int getIndex() {
    return index;
  }

}
