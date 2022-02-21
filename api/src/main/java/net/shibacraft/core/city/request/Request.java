package net.shibacraft.core.city.request;

import lombok.Getter;


@Getter
public class Request {
   
   private int time;
   
   public Request(int time) {
      this.time = time;
   }
   
   public void decreaseTime() {
      --time;
   }
}
