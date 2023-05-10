package org.spigotmc;

public class PlayerStatus {
       
    private boolean isBleeding;
    private boolean isInfected;
    private int thirstLevel;
    
    public PlayerStatus(String playerName) {
        this.plugin = plugin;
        this.playerNames = new HashSet<>();
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.thirstLevel = 0;
        startUpdating();
    }
}
