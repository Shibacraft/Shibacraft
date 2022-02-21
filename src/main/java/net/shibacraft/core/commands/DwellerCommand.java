package net.shibacraft.core.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.user.UserHandler;
import org.bukkit.entity.Player;


@Command(names = "ciudadano")
public class DwellerCommand implements CommandClass {

   private final UserHandler userHandler;
   
   public DwellerCommand(CorePlugin plugin) {
      this.userHandler = new UserHandler(plugin);
   }
   
   @Command(names = {"help", "ayuda"})
   public void runHelpDweller(@Sender Player player) {
   
   }

   @Command(names = {"leave", "salir"})
   public void runLeaveCity(@Sender Player player){
      userHandler.leaveCity(player);
   }
}

