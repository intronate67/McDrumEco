package com.mcdrum.dev;

/**
 * Created by Admin on 6/21/2014.
 */
public class SLAPI {

    private static McDrumEco plugin = EconManager.getPlugin();

    public static void saveBalances()
    {
        for(String p : EconManager.getBalanceMap().keySet())
        {
            plugin.getConfig().set("balance."+p, EconManager.getBalanceMap().get(p));
        }
        plugin.saveConfig();
    }

    public static void loadBalances()
    {
        if(!plugin.getConfig().contains("balance")) return;
        for(String s : plugin.getConfig().getConfigurationSection("balance").getKeys(false))
        {
            EconManager.setBalance(s, plugin.getConfig().getDouble("balance."+s));
        }
    }

}
