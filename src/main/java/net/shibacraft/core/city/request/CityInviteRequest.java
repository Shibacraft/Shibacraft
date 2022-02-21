package net.shibacraft.core.city.request;

import lombok.Getter;
import org.bukkit.entity.Player;


@Getter
public class CityInviteRequest extends Request {
   
   private final Player target;
   
   public CityInviteRequest(int time, Player target) {
      super(time);
      this.target = target;
   }
   
}
