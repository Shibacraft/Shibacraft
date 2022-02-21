package net.shibacraft.core.event;

import net.shibacraft.core.city.request.Request;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;


public class RequestExpireEvent extends Event {
   
   private static final HandlerList HANDLER_LIST = new HandlerList();
   
   private final Request request;
   
   public RequestExpireEvent(Request request) {
      this.request = request;
   }
   
   public Request getRequest() {
      return request;
   }
   
   @Override
   public @NotNull HandlerList getHandlers() {
      return HANDLER_LIST;
   }
   
   public static HandlerList getHandlerList() {
      return HANDLER_LIST;
   }
   
}
