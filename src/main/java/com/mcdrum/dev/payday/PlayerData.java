package com.mcdrum.dev.payday;

import org.bukkit.entity.Player;

/**
 * @Author Hunter Sharpe
 */
public class PlayerData {

    public double moneyToGive = 0.0D;
    public int OresMined = 0;
    public int BlocksMined = 0;
    public int MobsKilled = 0;
    public int PlayersKilled = 0;
    public long lastReward = 0L;

    public PlayerData(Player player)
    {
        this.lastReward = System.currentTimeMillis();
    }

    public PlayerData() {}

}
