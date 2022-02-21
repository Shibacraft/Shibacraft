package net.shibacraft.core.utils.banner;

import net.shibacraft.core.chat.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


public class Banner {
   
   public static void action(@NotNull JavaPlugin plugin) {
      console("&5Core");
      console("&fAuthor: &e" + plugin.getDescription().getAuthors());
      console("&fVersion: &e" + plugin.getDescription().getVersion());
   }
   
   private static void console(String text) {
      Bukkit.getConsoleSender().sendMessage(TextColor.color(text));
   }
   
}
