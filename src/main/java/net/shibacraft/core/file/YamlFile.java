package net.shibacraft.core.file;

import net.shibacraft.core.chat.TextColor;
import net.shibacraft.core.yaml.YamlConfigurationSection;
import net.shibacraft.core.yaml.YamlFileConfiguration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;


public class YamlFile implements YamlFileConfiguration {
   
   private final YamlConfiguration configuration;
   private final ConfigurationSection section;
   private final File file;
   
   public YamlFile(@NotNull Plugin plugin, String fileName) {
      fileName += ".yml";
      this.configuration = new YamlConfiguration();
      this.section = configuration;
      this.file = new File(plugin.getDataFolder(), fileName);
      createFile(plugin, fileName);
   }
   
   public YamlFile(ConfigurationSection section) {
      this.configuration = null;
      this.section = section;
      file = null;
   }
   
   private void createFile(Plugin plugin, String fileName) {
      try {
         if(!file.exists()) {
            if(plugin.getResource(fileName) != null) {
               plugin.saveResource(fileName, false);
            } else {
               configuration.save(file);
            }
            
            configuration.load(file);
            return;
         }
         
         configuration.load(file);
         configuration.save(file);
      } catch(InvalidConfigurationException | IOException e) {
         e.printStackTrace();
      }
   }
   
   
   @Override
   public Object get(String path) {
      return section.get(path);
   }
   
   @Override
   public void set(String path, Object value) {
      section.set(path, value);
   }
   
   @Override
   public String getString(String path) {
      return TextColor.color(section.getString(path));
   }
   
   @Override
   public boolean contains(String path) {
      return section.contains(path);
   }
   
   @Override
   public int getInt(String path) {
      return section.getInt(path);
   }
   
   @Override
   public double getDouble(String path) {
      return section.getDouble(path);
   }
   
   @Override
   public boolean getBoolean(String path) {
      return section.getBoolean(path);
   }
   
   @Override
   public float getFloat(String path) {
      return (float) section.getDouble(path);
   }
   
   @Override
   public List<String> getStringList(String path) {
      List<String> list = section.getStringList(path);
      list.replaceAll(TextColor::color);
      return list;
   }
   
   @Override
   public Set<String> getKeys(boolean deep) {
      return section.getKeys(deep);
   }
   
   @Override
   public YamlConfigurationSection getSection(String path) {
      ConfigurationSection configurationSection = this.section.getConfigurationSection(path);
      if(configurationSection == null || configurationSection.getKeys(false).isEmpty()) {
         return null;
      }
      return new YamlFile(section);
   }
   
   @Override
   public void reload() throws Exception {
      configuration.load(file);
   }
   
   @Override
   public void save() throws Exception {
      configuration.save(file);
   }
   
}
