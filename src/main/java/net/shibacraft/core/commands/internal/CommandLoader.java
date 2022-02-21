package net.shibacraft.core.commands.internal;

import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.builder.AnnotatedCommandBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.translator.DefaultTranslator;
import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.commands.WalletCommand;
import net.shibacraft.core.commands.*;
import net.shibacraft.core.commands.president.PresidentCommand;
import org.jetbrains.annotations.NotNull;


public class CommandLoader {
   
   private final CorePlugin plugin;
   
   private final BukkitCommandManager commandManager;
   private final AnnotatedCommandTreeBuilder annotatedCommandTreeBuilder;
   
   public CommandLoader(@NotNull CorePlugin plugin) {
      this.plugin = plugin;
      commandManager = new BukkitCommandManager(plugin.getLoader().getName());
      PartInjector injector = CoreModule.newInjector(plugin);
      annotatedCommandTreeBuilder = AnnotatedCommandTreeBuilder.create(
         new AnnotatedCommandBuilderImpl(injector), new CommandInstanceCreator(plugin));
      commandManager.setTranslator(new DefaultTranslator(new CommandTranslatorProvider(plugin)));
   }
   
   public void load() {
      commandManager.registerCommands(annotatedCommandTreeBuilder.fromClass(new CoreMainCommand(plugin)));
      commandManager.registerCommands(annotatedCommandTreeBuilder.fromClass(new DiscordCommand(plugin)));
      commandManager.registerCommands(annotatedCommandTreeBuilder.fromClass(new DwellerCommand(plugin)));
      commandManager.registerCommands(annotatedCommandTreeBuilder.fromClass(new HelpCommand(plugin)));
      commandManager.registerCommands(annotatedCommandTreeBuilder.fromClass(new MapCommand(plugin)));
      commandManager.registerCommands(annotatedCommandTreeBuilder.fromClass(new WalletCommand(plugin)));
      commandManager.registerCommands(annotatedCommandTreeBuilder.fromClass(new WebCommand(plugin)));
      commandManager.registerCommands(annotatedCommandTreeBuilder.fromClass(new WikiCommand(plugin)));
      commandManager.registerCommands(annotatedCommandTreeBuilder.fromClass(new PresidentCommand(plugin)));
      
   }
   
   public void unload() {
      commandManager.unregisterAll();
   }
   
}
