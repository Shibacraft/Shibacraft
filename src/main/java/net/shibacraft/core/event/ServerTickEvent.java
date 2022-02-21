package net.shibacraft.core.event;

import net.shibacraft.core.tick.ServerTickCause;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;


public class ServerTickEvent extends Event {
   
   private static final HandlerList HANDLER_LIST = new HandlerList();
   private final ServerTickCause serverTickCause;
   
   public ServerTickEvent(ServerTickCause serverTickCause) {
      this.serverTickCause = serverTickCause;
   }
   
   public ServerTickCause getServerTickCause() {
      return serverTickCause;
   }
   
   @Override
   public @NotNull HandlerList getHandlers() {
      return HANDLER_LIST;
   }
   
   public static HandlerList getHandlerList() {
      return HANDLER_LIST;
   }
}
