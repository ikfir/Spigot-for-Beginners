package com.kfir.ep3;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new events(),this);
        getCommand("points").setExecutor(new Points());
        for(Player player : Bukkit.getOnlinePlayers()){
            events.getItem.put(player.getUniqueId(),events.randomBlock());
            events.points.put(player.getUniqueId(),0);
            player.sendMessage("§eYou have to break: §c"+events.getItem.get(player.getUniqueId()).name());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
