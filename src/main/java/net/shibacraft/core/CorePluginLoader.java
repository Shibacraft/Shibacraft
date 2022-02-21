package net.shibacraft.core;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class CorePluginLoader extends JavaPlugin {
   
   private final CorePlugin plugin;
   
   public CorePluginLoader(CorePlugin plugin) {
      this.plugin = plugin;
   }
   
   @Override
   public void onLoad() {
      plugin.load();
   }
   
   @Override
   public void onEnable() {
      plugin.enable();
   }
   
   @Override
   public void onDisable() {
      plugin.disable();
   }
   
}
