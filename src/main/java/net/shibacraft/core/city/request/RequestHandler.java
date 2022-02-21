package net.shibacraft.core.city.request;

import net.shibacraft.core.event.RequestExpireEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class RequestHandler {
   
   private final Map<String, Request> requestsCity;
   
   public RequestHandler() {
      this.requestsCity = new ConcurrentHashMap<>();
   }
   
   public void addRequest(Request request) {
      if(request instanceof CityInviteRequest inviteRequest) {
         requestsCity.put(inviteRequest.getTarget().getUniqueId().toString(), inviteRequest);
      }
   }
   
   public void removeRequest(@NotNull Player player) {
      requestsCity.remove(player.getUniqueId().toString());
   }
   
   public boolean hasPendingRequests(@NotNull Player player) {
      return requestsCity.containsKey(player.getUniqueId().toString());
   }
   
   public Request getPendingRequest(@NotNull Player player) {
      return requestsCity.get(player.getUniqueId().toString());
   }
   
   public void updatePendingRequests() {
      requestsCity.forEach((uuid, request) -> {
         request.decreaseTime();
         if(request.getTime() <= 0) {
            Bukkit.getPluginManager().callEvent(new RequestExpireEvent(request));
         }
      });
   }
   
}

