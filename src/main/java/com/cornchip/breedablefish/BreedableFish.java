package com.cornchip.breedablefish;

import org.bukkit.plugin.java.JavaPlugin;

public final class BreedableFish extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new FishListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
