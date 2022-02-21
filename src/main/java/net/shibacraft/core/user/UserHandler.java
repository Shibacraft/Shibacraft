package net.shibacraft.core.user;

import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.city.City;
import net.shibacraft.core.city.CityUtilities;
import net.shibacraft.core.storage.Storage;
import net.shibacraft.core.yaml.YamlFileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;


public class UserHandler {
   
   private final YamlFileConfiguration lang;
   private final Storage<String, City> cityStorage;
   private final Storage<UUID, User> userStorage;
   
   public UserHandler(@NotNull CorePlugin plugin) {
      this.lang = plugin.getFileMatcher().getFiles().get("lang");
      this.cityStorage = plugin.getCityStorage();
      this.userStorage = plugin.getUserStorage();
   }
   
   public void leaveCity(Player player){
      Optional<User> userOptional = userStorage.find(player.getUniqueId());
      
      if(!userOptional.isPresent()) {
         player.sendMessage(lang.getString("cities.commands.user-error"));
         return;
      }
      User user = userOptional.get();
      if(!user.getCityName().isPresent()) {
         player.sendMessage(lang.getString("cities.commands.no-city"));
         return;
      }
      String cityName = user.getCityName().get();
      Optional<City> cityOptional = cityStorage.find(cityName);
      if(cityOptional.isPresent()) {
         City city = cityOptional.get();
      
         if(city.getOwner().equals(player.getName())) {
            player.sendMessage(lang.getString("cities.no-creator"));
            userOptional.get().setCityName(null);
            cityStorage.remove(city.getName());
            return;
         }
         if(city.getMembers().contains(player.getName())) {
            player.sendMessage(lang.getString("cities.commands.already-member"));
            userOptional.get().setCityName(null);
            city.getMembers().remove(player.getName());
         }
      }
   }
}
