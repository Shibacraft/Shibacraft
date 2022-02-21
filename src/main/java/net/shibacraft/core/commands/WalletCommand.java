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

@Command(names = "wallet")
public class WalletCommand implements CommandClass {
   
   
   private final YamlFileConfiguration wallet;
   
   public WalletCommand(@NotNull CorePlugin plugin) {
      this.wallet = plugin.getFileMatcher().getFiles().get("wallet");
   }
   
   @Command(names = "")
   public void runHelp(@Sender Player player, @OptArg int page) {
      List<String> sectionList;
      for(String line : wallet.getSection("help").getKeys(false)) {
         sectionList = wallet.getStringList(line + ".");
         sectionList.forEach(player::sendMessage);
      }
      
   }
}
