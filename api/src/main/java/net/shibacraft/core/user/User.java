package net.shibacraft.core.user;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


public interface User extends ConfigurationSerializable {
   
   Player getPlayer();
   
   Optional<String> getCityName();
   
   void setCityName(String name);
   
   @NotNull
   @Override
   default Map<String, Object> serialize() {
      Map<String, Object> playerMap = new LinkedHashMap<>();
      playerMap.put("name", getPlayer().getName());
      getCityName().ifPresent(name -> playerMap.put("city-name", name));
      return playerMap;
   }
}
