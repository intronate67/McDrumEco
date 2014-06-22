package com.mcdrum.dev;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Created by Admin on 6/21/2014.
 */
public class PlayerKillEvent implements Listener {

    @EventHandler
    public void onPlayerKill(EntityDeathEvent e){

        if(e.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent){
           Player p = e.getEntity().getKiller().getPlayer();
            if(e.getEntityType().equals(EntityType.ZOMBIE)){
                EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) + 10);
                p.sendMessage(ChatColor.GREEN + "You were rewarded $10 for killing that zombie.");
                return;
            }
            if(e.getEntityType().equals(EntityType.CREEPER)){
                EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) + 25);
                p.sendMessage(ChatColor.GREEN + "You were rewarded $25 for killing that creeper.");
                return;
            }
            if(e.getEntityType().equals(EntityType.SPIDER)){
                EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) + 15);
                p.sendMessage(ChatColor.GREEN + "You were rewarded $15 for killing that spider.");
                return;
            }
            if(e.getEntityType().equals(EntityType.BLAZE)){
                EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) + 30);
                p.sendMessage(ChatColor.GREEN + "You were rewarded $30 for killing that blazeman.");
                return;
            }
            if(e.getEntityType().equals(EntityType.ENDERMAN)){
                EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) + 45);
                p.sendMessage(ChatColor.GREEN + "You were rewarded $45 for killing that enderman.");
                return;
            }
            if(e.getEntityType().equals(EntityType.PIG_ZOMBIE)){
                EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) + 30);
                p.sendMessage(ChatColor.GREEN + "You were rewarded $30 for killing that zombie pigman.");
                return;
            }
            if(e.getEntityType().equals(EntityType.WITCH)){
                EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) + 35);
                p.sendMessage(ChatColor.GREEN + "You were rewarded $35 for killing that witch.");
                return;
            }
        }

    }

}
