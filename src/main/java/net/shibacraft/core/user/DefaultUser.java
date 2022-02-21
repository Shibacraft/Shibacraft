package net.shibacraft.core.user;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;


public class DefaultUser implements User{
   
   private Player player;
   private String cityName;
   
   public DefaultUser(Player player) {
      this.player = player;
      this.cityName = null;
   }
   
   public DefaultUser(@NotNull Map<String, Object> userMap){
      this.cityName = (String) userMap.get("city-name");
   }
   
   @Override
   public Player getPlayer() {
      return player;
   }
   
   @Override
   public Optional<String> getCityName() {
      return Optional.ofNullable(cityName);
   }
   
   @Override
   public void setCityName(String name) {
      this.cityName = name;
   }
   
}
