package com.mcdrum.dev;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Admin on 6/21/2014.
 */
public class McDrumEco extends JavaPlugin{

    @Override
    public void onEnable(){
        getCommand("eco").setExecutor(new EconCommand());
        getCommand("kit").setExecutor(new KitCommand());
        new EconManager(this);
        SLAPI.loadBalances();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerKillEvent(), this);
        pm.registerEvents(new SignListener(), this);

    }
    @Override
    public void onDisable(){
        SLAPI.saveBalances();
    }
}
