package net.shibacraft.core.storage;

import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.city.City;
import net.shibacraft.core.city.DefaultCity;
import net.shibacraft.core.yaml.YamlFileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class CityStorage implements Storage<String, City> {
   
   private final YamlFileConfiguration cities;
   private final Map<String, City> citiesStorage = new ConcurrentHashMap<>();
   
   public CityStorage(@NotNull CorePlugin plugin) {
      this.cities = plugin.getFileMatcher().getFiles().get("cities");
   }
   
   @Override
   public Map<String, City> get() {
      return citiesStorage;
   }
   
   @Override
   public Optional<City> find(String key) {
      return Optional.ofNullable(citiesStorage.get(key));
   }
   
   @Override
   public Optional<City> findFromData(String key) {
      if(!cities.contains("cities." + key)) {
         return Optional.empty();
      }
      
      Map<String, Object> citySerialize = new HashMap<>();
      citySerialize.put("city-name", cities.getString("cities." + key + ".city-name"));
      citySerialize.put("displayname", citySerialize.get("cities." + key + ".displayname"));
      citySerialize.put("owner", citySerialize.get("cities." + key + ".owner"));
      Set<String> members = new HashSet<>(cities.getStringList("cities." + key + ".members"));
      citySerialize.put("members", members);
      citySerialize.put("members-remaining", cities.getInt("cities." + key + ".members-remaining"));
      return Optional.of(new DefaultCity(citySerialize));
   }
   
   @Override
   public void save(String key) {
      find(key).ifPresent(city -> cities.set("cities." + key, city.serialize()));
      remove(key);
   }
   
   @Override
   public void saveObject(String key, City value) {
   }
   
   @Override
   public void remove(String key) {
      citiesStorage.remove(key);
   }
   
   @Override
   public void add(String key, City value) {
      citiesStorage.put(key, value);
   }
   
   @Override
   public void saveAll() throws Exception {
      citiesStorage.keySet().forEach(this::save);
      cities.save();
   }
   
   @Override
   public void loadAll() {
      if(!cities.contains("cities")) {
         return;
      }
      cities.getSection("cities").getKeys(false).forEach(name -> findFromData(name).ifPresent(city -> {
         add(name, city);
      }));
   }
   
}
