package com.kfir.ep4;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    private static Main instance;

    public static Main getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getCommand("inv").setExecutor(new Inv());
        getCommand("meow").setExecutor(new meow());
        Bukkit.getPluginManager().registerEvents(new meow(),this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
