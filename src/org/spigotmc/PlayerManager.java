package org.spigotmc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager implements Listener {

    private final JavaPlugin plugin;
    private final Set<String> playerNames;

    public PlayerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.playerNames = new HashSet<>();
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
        startUpdating();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerNames.add(player.getName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        playerNames.remove(player.getName());
    }

    private void startUpdating() {
        Bukkit.getScheduler().runTaskTimer(plugin, this::update, 20L, 20L);
    }

    private void update() {
        for (String playerName : playerNames) {
            plugin.getLogger().info("Player: " + playerName);
        }
    }
}

