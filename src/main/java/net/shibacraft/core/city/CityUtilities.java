package net.shibacraft.core.city;

import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.storage.Storage;
import net.shibacraft.core.user.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;


public class CityUtilities {
   
   
   private final Storage<UUID, User> userStorage;
   
   public CityUtilities(@NotNull CorePlugin plugin) {
      this.userStorage = plugin.getUserStorage();
   }
   
   public boolean playerHasCity(Player player) {
      return userStorage.find(player.getUniqueId()).map(user -> user.getCityName().isPresent()).orElse(false);
   }
   
}
