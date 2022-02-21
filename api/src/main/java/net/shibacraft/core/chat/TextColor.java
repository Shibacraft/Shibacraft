package net.shibacraft.core.chat;

import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextColor {
   
   private static final Pattern PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");
   
   public static @NotNull String color(String text) {
      
      Matcher matcher = PATTERN.matcher(text);
      
      while(matcher.find()){
         String color = text.substring(matcher.start(), matcher.end());
         text = text.replace(color, ChatColor.of(color) + "");
         
         matcher = PATTERN.matcher(text);
      }
      
      return ChatColor.translateAlternateColorCodes('&', text);
   }
   
}
