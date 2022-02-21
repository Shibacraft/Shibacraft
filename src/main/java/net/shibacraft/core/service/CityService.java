package net.shibacraft.core.service;

import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.city.City;
import net.shibacraft.core.storage.CityStorage;
import net.shibacraft.core.storage.Storage;
import org.jetbrains.annotations.NotNull;
import team.unnamed.error.Errors;


public class CityService implements Service{
   
   private final Storage<String, City> cityStorage;
   
   public CityService(@NotNull CorePlugin plugin) {
      this.cityStorage = plugin.getCityStorage();
   }
   
   @Override
   public void start() {
      cityStorage.loadAll();
   }
   
   @Override
   public void stop() {
      try {
         cityStorage.saveAll();
      } catch(Exception e) {
         Errors.getStackTrace(e);
      }
   }
   
}
