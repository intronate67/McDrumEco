package com.mcdrum.dev;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Admin on 6/21/2014.
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        if(EconManager.hasAccount(event.getPlayer().getName())) return;
        EconManager.setBalance(event.getPlayer().getName(), 250D);
    }

}
