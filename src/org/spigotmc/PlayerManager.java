package org.spigotmc;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class PlayerManager implements Listener {

    private final JavaPlugin plugin;
    private final Set<String> playerNames;
    private HashMap<String, PlayerStatus> playerStatusList;
    private int thirstLevel;

    public PlayerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.playerNames = new HashSet<>();
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.thirstLevel = 0;
        startUpdating();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerNames.add(player.getName());
        if (playerStatusList.containsKey(player.getName())) {

        }
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
            Player player = Bukkit.getPlayer(playerName);
            if (player != null) {
                player.setLevel(20);
            }
        }
    }

    private String readFile(String fileName) {
        StringBuilder content = new StringBuilder();

        try {
            File dataFolder = this.plugin.getDataFolder();
            File file = new File(dataFolder, fileName);

            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }

                bufferedReader.close();
                fileReader.close();
            } else {
                System.out.println("File not found: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    

    private void writeToFile(String fileName, String content) {
        try {
            File dataFolder = this.plugin.getDataFolder();
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File file = new File(dataFolder, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, false); // 'true' to append to the file, 'false' to overwrite
            writer.write(content + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
