package net.shibacraft.core.commands.internal;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.SubCommandInstanceCreator;
import net.shibacraft.core.CorePlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;


public class CommandInstanceCreator implements SubCommandInstanceCreator {
   
   private final CorePlugin plugin;
   
   public CommandInstanceCreator(CorePlugin plugin) {
      this.plugin = plugin;
   }
   
   
   @Override
   @Deprecated
   public CommandClass createInstance(@NotNull Class<? extends CommandClass> clazz, CommandClass parent) {
      try {
         return clazz.newInstance();
      } catch(InstantiationException | IllegalAccessException ignored) {
      }
      try {
         return clazz.getConstructor(CorePlugin.class).newInstance(plugin);
      } catch(InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
         e.printStackTrace();
      }
      return null;
   }
   
}
