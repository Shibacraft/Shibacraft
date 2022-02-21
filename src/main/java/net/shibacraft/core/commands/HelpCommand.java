package net.shibacraft.core.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.yaml.YamlFileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Command(names = {"help", "ayuda"})
public class HelpCommand implements CommandClass {
   
   private final YamlFileConfiguration help;
   
   public HelpCommand(@NotNull CorePlugin plugin) {
      this.help = plugin.getFileMatcher().getFiles().get("help");
   }
   
   @Command(names = "")
   public void runHelp(@Sender Player player, @OptArg int page) {
      List<String> sectionList;
      for(String line : help.getSection("help").getKeys(false)) {
         sectionList = help.getStringList(line + ".");
         sectionList.forEach(player::sendMessage);
      }
      
   }
   
}
