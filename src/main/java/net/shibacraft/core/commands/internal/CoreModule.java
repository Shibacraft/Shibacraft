package net.shibacraft.core.commands.internal;

import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import net.shibacraft.core.CorePlugin;
import org.jetbrains.annotations.NotNull;


public class CoreModule extends BukkitModule {
   
   public CoreModule(CorePlugin plugin) {
   }
   
   public static @NotNull PartInjector newInjector(CorePlugin plugin) {
      PartInjector injector = PartInjector.create();
      injector.install(new CoreModule(plugin));
      return injector;
   }
   
   @Override
   public void configure() {
      super.configure();
   }
   
}
