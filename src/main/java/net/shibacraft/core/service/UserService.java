package net.shibacraft.core.service;

import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.storage.Storage;
import net.shibacraft.core.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;


public class UserService implements Service {
   
   private final Storage<UUID, User> userStorage;
   
   public UserService(@NotNull CorePlugin plugin) {
      this.userStorage = plugin.getUserStorage();
   }
   
   @Override
   public void start() {
      userStorage.loadAll();
   }
   
   @Override
   public void stop() {
      try {
         userStorage.saveAll();
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
   
}
