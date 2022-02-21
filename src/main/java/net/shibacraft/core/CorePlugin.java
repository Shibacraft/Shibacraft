package net.shibacraft.core;

import lombok.Getter;
import net.shibacraft.core.commands.internal.CommandLoader;
import net.shibacraft.core.file.FileMatcher;
import net.shibacraft.core.utils.banner.Banner;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class CorePlugin {

   private final JavaPlugin loader;
   
   private final FileMatcher fileMatcher;
   private final CommandLoader commandLoader;
   
   public CorePlugin(JavaPlugin loader) {
      this.loader = loader;
      this.fileMatcher = new FileMatcher(this);
      this.commandLoader = new CommandLoader(this);
   }
   
   public void load() {
      fileMatcher.load();
      commandLoader.load();
   }
   
   public void enable() {
      Banner.action(loader);
   }
   
   public void disable() {
      Banner.action(loader);
   }
   
}

