package net.shibacraft.core.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.yaml.YamlFileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


@Command(names = "map")
public class MapCommand implements CommandClass {
   
   private final YamlFileConfiguration lang;
   
   public MapCommand(@NotNull CorePlugin plugin) {
      this.lang = plugin.getFileMatcher().getFiles().get("lang");
   }
   
   @Command(names = "")
   public void runMap(@Sender @NotNull Player player){
      player.sendMessage(lang.getString("messages.map"));
   }
}
