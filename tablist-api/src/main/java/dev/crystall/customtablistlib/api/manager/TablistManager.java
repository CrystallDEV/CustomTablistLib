package dev.crystall.customtablistlib.api.manager;

import dev.crystall.customtablistlib.api.data.PlayerTablist;
import java.util.UUID;
import org.bukkit.entity.Player;

/**
 * Created by CrystallDEV on 22/01/2022
 */
public interface TablistManager {

  void addPlayer(Player player);

  void removePlayer(Player player);

  void updatePlayerTablist(Player player);

  PlayerTablist getPlayerTablist(Player player);

  PlayerTablist getPlayerTablist(UUID uuid);

}
