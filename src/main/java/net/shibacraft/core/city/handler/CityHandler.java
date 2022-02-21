package net.shibacraft.core.city.handler;

import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.city.City;
import net.shibacraft.core.city.CityUtilities;
import net.shibacraft.core.city.DefaultCity;
import net.shibacraft.core.city.request.CityInviteRequest;
import net.shibacraft.core.city.request.Request;
import net.shibacraft.core.city.request.RequestHandler;
import net.shibacraft.core.storage.Storage;
import net.shibacraft.core.user.User;
import net.shibacraft.core.yaml.YamlFileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public class CityHandler {
   
   private final YamlFileConfiguration config, lang;
   private final Storage<String, City> cityStorage;
   private final Storage<UUID, User> userStorage;
   private final CityUtilities cityUtilities;
   private final RequestHandler requestHandler;
   
   private final Map<String, String> pendingUsers = new HashMap<>();
   
   public CityHandler(@NotNull CorePlugin plugin) {
      this.lang = plugin.getFileMatcher().getFiles().get("lang");
      this.config = plugin.getFileMatcher().getFiles().get("config");
      this.cityStorage = plugin.getCityStorage();
      this.userStorage = plugin.getUserStorage();
      this.cityUtilities = new CityUtilities(plugin);
      this.requestHandler = plugin.getRequestHandler();
   }
   
   public void createCity(Player player, String name, String prefix) {
      Optional<User> userOptional = userStorage.find(player.getUniqueId());
      
      if(!userOptional.isPresent()) {
         player.sendMessage(lang.getString("cities.commands.user-error"));
         return;
      }
      if(cityUtilities.playerHasCity(player)) {
         player.sendMessage(lang.getString("cities.commands.already-clan"));
         return;
      }
      
      if(cityStorage.find(name).isPresent()) {
         player.sendMessage(lang.getString("cities.already-exists"));
         return;
      }
      
      userOptional.ifPresent(user -> {
         cityStorage.add(name, new DefaultCity(name, name, player.getName()));
         user.setCityName(name);
         player.sendMessage(lang.getString("cities.commands.successfully-create").replace("%name%", name));
      });
      
      
   }
   
   public void inviteCity(Player player, Player target) {
      if(target == null) {
         player.sendMessage(lang.getString("messages.no-player-exist"));
         return;
      }
      if(!target.isOnline()) {
         player.sendMessage(lang.getString("messages.no-online"));
         return;
      }
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
         
         if(!city.getOwner().equals(player.getName())) {
            player.sendMessage(lang.getString("cities.no-creator"));
            return;
         }
         if(city.getMembers().contains(player.getName())) {
            player.sendMessage(lang.getString("cities.commands.already-member"));
            return;
         }
         int maximumMembers = city.getMaximumMembers();
         if(city.getMembers().size() >= maximumMembers) {
            player.sendMessage(
               lang.getString("cities.commands.maximum-members").replace("%limit%", maximumMembers + ""));
            return;
         }
      }
      if(cityUtilities.playerHasCity(target)) {
         player.sendMessage(lang.getString("cities.commands.already-has-city"));
         return;
      }
      if(requestHandler.hasPendingRequests(target)) {
         player.sendMessage(lang.getString("cities.commands.already-invite"));
      } else {
         pendingUsers.put(target.getName(), cityOptional.get().getName());
         Request request = new CityInviteRequest(config.getInt("config.request-expiration"), target);
         requestHandler.addRequest(request);
         player.sendMessage(lang
                               .getString("cities.commands.successfully-invite-sender")
                               .replace("%target%", target.getName()));
         target.sendMessage(lang
                               .getString("clans.commands.successfully-invite-target")
                               .replace("%clan%", cityName)
                               .replace("%time%", request.getTime() + "")
                               .replace("%sender%", player.getName()));
      }
      
   }
   
   public void kickCity(Player player, Player target) {
      if(target == null) {
         player.sendMessage(lang.getString("messages.no-player-exist"));
         return;
      }
      if(!target.isOnline()) {
         player.sendMessage(lang.getString("messages.no-online"));
         return;
      }
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
         
         if(!city.getOwner().equals(player.getName())) {
            player.sendMessage(lang.getString("cities.no-creator"));
            return;
         }
         if(!city.getMembers().contains(player.getName())) {
            player.sendMessage(lang.getString("cities.commands.invalid-member"));
            return;
         }
         if(cityUtilities.playerHasCity(target)) {
            player.sendMessage(lang.getString("cities.commands.already-has-city"));
            return;
         }
         city.getMembers().remove(target.getName());
         player.sendMessage(
            lang.getString("cities.commands.successfully-kick-member").replace("%target%", target.getName()));
         
         target.sendMessage(
            lang.getString("clans.commands.successfully-kick-member-target").replace("%clan%", cityName));
      }
      
   }
   
   public void listCities(Player player) {
      Map<String, City> cityOptional = cityStorage.get();
      cityOptional.forEach((name, city) -> {
         player.sendMessage(name);
         player.sendMessage(city.toString());
      });
   }
   
}
