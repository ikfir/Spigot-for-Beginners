package com.kfir.ep1;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("meow").setExecutor(new Meow());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
