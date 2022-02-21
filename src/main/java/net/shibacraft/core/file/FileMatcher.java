package net.shibacraft.core.file;

import lombok.Getter;
import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.loader.Loader;
import net.shibacraft.core.yaml.YamlFileConfiguration;

import java.util.HashMap;
import java.util.Map;


@Getter
public class FileMatcher implements Loader {
   
   private final Map<String, YamlFileConfiguration> files = new HashMap<>();
   private final CorePlugin plugin;
   
   
   public FileMatcher(CorePlugin plugin) {
      this.plugin = plugin;
   }
   
   @Override
   public void load() {
      files.put("config", new YamlFile(plugin.getLoader(), "config"));
      files.put("lang", new YamlFile(plugin.getLoader(), "lang"));
      files.put("cities", new YamlFile(plugin.getLoader(), "cities"));
      files.put("help", new YamlFile(plugin.getLoader(), "help"));
      files.put("wallet", new YamlFile(plugin.getLoader(), "wallet"));
   }
   
   
}
