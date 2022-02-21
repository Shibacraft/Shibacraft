package net.shibacraft.core.listeners;

import net.shibacraft.core.CorePlugin;
import net.shibacraft.core.city.request.RequestHandler;
import net.shibacraft.core.event.ServerTickEvent;
import net.shibacraft.core.tick.ServerTickCause;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;


public class ServerTickListener implements Listener{
   
   private final RequestHandler requestHandler;
   
   public ServerTickListener(CorePlugin plugin) {
      this.requestHandler = plugin.getRequestHandler();
   }
   
   
   @EventHandler
   public void onServerTickEvent(@NotNull ServerTickEvent event){
      if(event.getServerTickCause() == ServerTickCause.SECOND){
         requestHandler.updatePendingRequests();
      }
   }
}
