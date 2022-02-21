package net.shibacraft.core.listeners;

import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.city.request.CityInviteRequest;
import net.shibacraft.core.city.request.Request;
import net.shibacraft.core.city.request.RequestHandler;
import net.shibacraft.core.event.RequestExpireEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;


public class RequestExpireListener implements Listener{
   
   private final RequestHandler requestHandler;
   
   public RequestExpireListener(CorePlugin plugin) {
      this.requestHandler = plugin.getRequestHandler();
   }
   
   @EventHandler
   public void onRequestExpireEvent(@NotNull RequestExpireEvent event){
      Request request = event.getRequest();
      if(request instanceof CityInviteRequest cityInviteRequest){
         requestHandler.removeRequest(cityInviteRequest.getTarget());
      }
   }
}
