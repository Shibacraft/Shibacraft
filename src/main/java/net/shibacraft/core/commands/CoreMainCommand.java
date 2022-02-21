package net.shibacraft.core.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.file.FileMatcher;
import org.bukkit.command.CommandSender;
import team.unnamed.error.Errors;


@Command(names = "shibacraft", desc = "Main command for Core", permission = "shibacraft.admin")
public class CoreMainCommand implements CommandClass {
   
   private final FileMatcher fileMatcher;
   
   public CoreMainCommand(CorePlugin plugin) {
      this.fileMatcher = plugin.getFileMatcher();
   }
   
   @Command(names = "reload")
   public void runReload(CommandSender sender){
      fileMatcher.getFiles().forEach((key, value) -> {
         try {
            value.reload();
         } catch(Exception exception) {
            Errors.getStackTrace(exception);
         }
      });
      sender.sendMessage(fileMatcher.getFiles().get("lang").getString("messages.reload"));
   }
   
}
