package net.shibacraft.core.utils;

import net.shibacraft.core.chat.TextColor;
import org.bukkit.Bukkit;
import team.unnamed.error.Errors;


public class CoreLogger {
   
   private static final String LOGGER_NAME = "&b&lShibacraft";
   
   private CoreLogger() {
   }
   
   public static void info(String message) {
      log("&f&lINFO &r" + message);
   }
   
   public static void warn(String message) {
      log("&6&lWARN &r" + message);
   }
   
   public static void warn(Throwable throwable) {
      warn(Errors.getStackTrace(throwable));
   }
   
   public static void severe(String message) {
      log("&c&lSEVERE &r" + message);
   }
   
   public static void severe(Throwable throwable) {
      severe(Errors.getStackTrace(throwable));
   }
   
   private static void log(String message) {
      message = LOGGER_NAME + " &8| " + message;
      Bukkit.getConsoleSender().sendMessage(TextColor.color(message));
   }
   
}

