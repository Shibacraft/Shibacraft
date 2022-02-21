package net.shibacraft.core.commands.president;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.city.handler.CityHandler;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;


@Command(names = {"president", "presidente"})
public class PresidentCommand implements CommandClass {

   private final CityHandler cityHandler;
   
   public PresidentCommand(CorePlugin plugin) {
      this.cityHandler = new CityHandler(plugin);
   }
   
   @Command(names = {"create", "crear"})
   public void runCreateCity(@Sender Player player, String name){
   cityHandler.createCity(player, name, name);
   
   }

   @Command(names = {"invite", "invitar"})
   public void runInviteCityCommand(@Sender Player player, Player target){
      cityHandler.inviteCity(player, target);
   }
   
   @Command(names = {"kick"})
   public void runKickCityCommand(@Sender Player player, Player target){
      cityHandler.kickCity(player, target);
   }
   
   @Command(names = {"list"," lista"})
   public void runListCities(Player player){
      cityHandler.listCities(player);
   }
}
