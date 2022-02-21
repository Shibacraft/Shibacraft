package net.shibacraft.core.commands.internal;

import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.translator.TranslationProvider;
import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.yaml.YamlConfigurationSection;
import org.jetbrains.annotations.NotNull;


public class CommandTranslatorProvider implements TranslationProvider {
   
   private final YamlConfigurationSection lang;
   
   public CommandTranslatorProvider(@NotNull CorePlugin plugin) {
      this.lang = plugin.getFileMatcher().getFiles().get("lang").getSection("commands");
   }
   
   @Override
   public String getTranslation(@NotNull Namespace namespace, String key) {
      return lang.getString("commands." + key);
   }
   
}
