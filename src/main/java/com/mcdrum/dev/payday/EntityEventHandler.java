package com.mcdrum.dev.payday;

import com.mcdrum.dev.EconManager;
import com.mcdrum.dev.McDrumEco;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @Author Hunter Sharpe
 */
public class EntityEventHandler implements Listener{

    public HashMap<String, PlayerData> PlayerData = new HashMap();
    public List<Location> PlayerPlacedBlocks = new ArrayList();
    public HashMap<Integer, Boolean> SpawnedNotNatural = new HashMap();

    public void onPlayerJoin(PlayerJoinEvent event)
    {
        this.PlayerData.put(event.getPlayer().getName(), new PlayerData(event.getPlayer()));
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event)
    {
        if (McDrumEco.mobrewards)
        {
            Player Killer = event.getEntity().getKiller();
            if ((Killer != null) && (Killer.hasPermission("mcdrumeco.mobs")))
            {
                boolean valid = true;
                if ((Killer.getGameMode() == GameMode.CREATIVE) && (!McDrumEco.creativeKilling)) {
                    valid = false;
                }
                if ((valid) && (this.SpawnedNotNatural.get(Integer.valueOf(event.getEntity().getEntityId())) == null))
                {
                    PlayerData playerData = McDrumEco.getPlayerData(Killer.getName());
                    try
                    {
                        double Worth = ((Double)McDrumEco.MobValues.get(event.getEntityType().toString().toLowerCase())).doubleValue();
                        playerData.MobsKilled += 1;
                        playerData.moneyToGive += Worth;
                    }
                    catch (Exception localException) {}
                }
            }
        }
        this.SpawnedNotNatural.remove(Integer.valueOf(event.getEntity().getEntityId()));
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        if (McDrumEco.playerrewards)
        {
            Player Killer = event.getPlayer().getKiller();
            if ((Killer != null) && (Killer.hasPermission("mcdrumeco.pvp")))
            {
                PlayerData playerData = McDrumEco.getPlayerData(Killer.getName());
                playerData.PlayersKilled += 1;
                if (EconManager.getBalance(event.getPlayer().getName()) < McDrumEco.plugin.getConfig().getDouble("player-min-reward"))
                {
                    event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE+ "Economy" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_GRAY + "You have been robbed by " + Killer.getName() + "! You lost " + EconManager.getBalance(event.getPlayer().getName()));
                    EconManager.setBalance(Killer.getName(), EconManager.getBalance(Killer.getName()) + EconManager.getBalance(event.getPlayer().getName()));
                    EconManager.setBalance(event.getPlayer().getName(), EconManager.getBalance(event.getPlayer().getName()) - EconManager.getBalance(event.getPlayer().getName()));
                }
                else
                {
                    //Below is working!
                    double moneyRobbed = new Random().nextFloat() * (McDrumEco.plugin.getConfig().getDouble("player-max-reward") - McDrumEco.plugin.getConfig().getDouble("player-min-reward")) + McDrumEco.plugin.getConfig().getDouble("player-min-reward");
                    event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Economy" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_GRAY + "You have been robbed by " + Killer.getName() + "! You lost " + moneyRobbed);
                    EconManager.setBalance(Killer.getName(), EconManager.getBalance(Killer.getName()) + moneyRobbed);
                    EconManager.setBalance(event.getPlayer().getName(), EconManager.getBalance(event.getPlayer().getName()) - moneyRobbed);
                }
                playerData.moneyToGive += McDrumEco.PlayerReward;
            }
        }
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event)
    {
        if ((!McDrumEco.plugin.getConfig().getBoolean("spawner-rewarded")) &&
                (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL)) {
            this.SpawnedNotNatural.put(Integer.valueOf(event.getEntity().getEntityId()), Boolean.valueOf(true));
        }
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event)
    {
        this.PlayerData.remove(event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        this.PlayerData.remove(event.getPlayer().getName());
    }

}
