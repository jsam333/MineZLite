package org.spigotmc;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


public class MineZLite extends JavaPlugin {

    PlayerManager playerManager;
    
    @Override
    public void onEnable() {
        getLogger().info("Hello, World! MineZLite has been enabled.");
        getServer().getPluginManager().registerEvents(new ZombieSpawnListener(), this);
        playerManager = new PlayerManager(this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("broadcast")) {
            if (args.length > 0) {
                StringBuilder message = new StringBuilder();
                for (String arg : args) {
                    message.append(arg).append(" ");
                }
                getServer().broadcastMessage(ChatColor.GREEN + sender.getName() + " says: " + ChatColor.WHITE + message.toString().trim());
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /broadcast <message>");
                return false;
            }
        }
        return false;
    }
}
