package net.shibacraft.shibacraft.commands;

import net.shibacraft.shibacraft.Shibacraft;
import net.shibacraft.shibacraft.fileManager.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShibacraftCommand implements CommandExecutor {

    private Shibacraft plugin;

    public ShibacraftCommand(Shibacraft plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command shibacraft, String label, String[] args) {

        Player user = (Player) sender;
        FileManager messagesFile = new FileManager(plugin, "messages");
        FileManager ayudaFile = new FileManager(plugin, "ayuda");
        FileManager ciudadesFile = new FileManager(plugin, "ayuda");
        FileManager walletFile = new FileManager(plugin, "ayuda");

        final String prefix = messagesFile.getString("Prefix");
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                messagesFile.reload();
                ayudaFile.reload();
                ciudadesFile.reload();
                walletFile.reload();
                if (messagesFile.getString("PluginReload").trim().length() > 0) {
                    user.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            messagesFile.getString("PluginReload").replace("{prefix}", prefix)));
                }
            } else {
                user.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getString("InvalidArgument").replace("{prefix}", prefix)));
            }
        } else {
            user.sendMessage(
                    ChatColor.translateAlternateColorCodes('&', messagesFile.getString("NoArguments").replace("{prefix}", prefix)));

        }
        return true;
    }
}