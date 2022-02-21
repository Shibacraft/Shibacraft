package net.shibacraft.core.storage;

import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.user.DefaultUser;
import net.shibacraft.core.user.User;
import net.shibacraft.core.yaml.YamlFileConfiguration;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class UserStorage implements Storage<UUID, User> {
   
   private final YamlFileConfiguration data;
   private final Map<UUID, User> userStorage = new ConcurrentHashMap<>();
   
   public UserStorage(@NotNull CorePlugin plugin) {
      this.data = plugin.getFileMatcher().getFiles().get("data");
   }
   
   @Override
   public Map<UUID, User> get() {
      return userStorage;
   }
   
   @Override
   public Optional<User> find(UUID key) {
      return Optional.ofNullable(userStorage.get(key));
   }
   
   @Override
   public Optional<User> findFromData(UUID key) {
      if(!data.contains("users.")) {
         return Optional.empty();
      }
      Object object = data.get("users." + key.toString());
      if(object instanceof Map) {
         return Optional.of(new DefaultUser((Map<String, Object>) object));
      }
      return Optional.empty();
   }
   
   @Override
   public void save(UUID key) {
      find(key).ifPresent(user -> {
         data.set("user." + key, user.serialize());
         try {
            data.save();
         } catch(Exception e) {
            e.printStackTrace();
         }
         remove(key);
      });
   }
   
   @Override
   public void saveObject(UUID key, @NotNull User value) throws Exception {
      data.set("user." + key, value.serialize());
      data.save();
      remove(key);
   }
   
   @Override
   public void remove(UUID key) {
      userStorage.remove(key);
   }
   
   @Override
   public void add(UUID key, User value) {
      userStorage.put(key, value);
   }
   
   @Override
   public void saveAll() {
      userStorage.keySet().forEach(this::save);
   }
   
   @Override
   public void loadAll() {
      if(!data.contains("users")) {
         return;
      }
      Bukkit.getOnlinePlayers().forEach(player -> findFromData(player.getUniqueId()).ifPresent(user -> {
         add(player.getUniqueId(), user);
      }));
   }
   
   
}
