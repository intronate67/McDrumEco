package com.mcdrum.dev;

import com.mcdrum.dev.payday.BlockData;
import com.mcdrum.dev.payday.BlockEventHandler;
import com.mcdrum.dev.payday.EntityEventHandler;
import com.mcdrum.dev.payday.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Admin on 6/21/2014.
 */
public class McDrumEco extends JavaPlugin{
    public static com.mcdrum.dev.payday.BlockEventHandler BlockEventHandler = new BlockEventHandler();
    public static EntityEventHandler PlayerHandler = new EntityEventHandler();
    public static HashMap<Integer, BlockData> Blocks = null;
    public static HashMap<String, Double> MobValues = new HashMap();
    public static int SecondsUntilPayday = 5;
    public static double PlayerReward = 1.0D;
    public static boolean mobrewards = true;
    public static boolean playerrewards = true;
    public static boolean miningrewards = true;
    public static boolean voterewards = true;
    public static boolean onlinerewards = true;
    public static String VoteRewardString = "";
    public static String Message = "";
    public static boolean creativeMining = false;
    public static boolean playerRobbery = true;
    public static boolean creativeKilling = false;
    public static McDrumEco plugin = null;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable(){
        plugin = this;
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        if (getServer().getPluginManager().getPlugin("Vault") != null)
        {
            loadConfig();
            if (miningrewards) {
                getServer().getPluginManager().registerEvents(BlockEventHandler, this);
            }
            if ((mobrewards) || (playerrewards)) {
                getServer().getPluginManager().registerEvents(PlayerHandler, this);
            }
            for (Player player : getServer().getOnlinePlayers()) {
                PlayerHandler.PlayerData.put(player.getName(), new PlayerData(player));
            }
        }
        else
        {
            System.out.println("Vault not installed! APOC Payday cannot run and is being disabled.");
            getServer().getPluginManager().disablePlugin(this);
        }
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("bal").setExecutor(new BalanceCommand());
        getCommand("eco").setExecutor(new EconCommand());
        getCommand("kit").setExecutor(new KitCommand());
        new EconManager(this);
        SLAPI.loadBalances();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerKillEvent(), this);
        pm.registerEvents(new SignListener(), this);
        saveConfig();
    }
    @Override
    public void onDisable(){
        String signList = SignListener.getInstance().signList.toString();
        config.set("signs", signList);
        SLAPI.saveBalances();
    }
    private void loadConfig()
    {
        Blocks = new HashMap();
        FileConfiguration Config = getConfig();
        saveDefaultConfig();

        SecondsUntilPayday = Config.getInt("time");
        Message = Config.getString("message");
        mobrewards = Config.getBoolean("mob-rewards");
        playerrewards = Config.getBoolean("pvp-rewards");
        miningrewards = Config.getBoolean("mining-rewards");
        PlayerReward = Config.getDouble("player-reward");
        voterewards = Config.getBoolean("vote-rewards");
        VoteRewardString = Config.getString("votereward.item");
        onlinerewards = Config.getBoolean("online-rewards");
        creativeKilling = Config.getBoolean("creative-killing-rewarded");
        creativeMining = Config.getBoolean("cretive-mining-rewarded");
        playerRobbery = Config.getBoolean("player-robbery");


        ConfigurationSection theSection = Config.getConfigurationSection("blocks");
        if (theSection != null)
        {
            Set<String> theKeys = theSection.getKeys(false);
            if (theKeys != null) {
                for (String theBlock : theKeys)
                {
                    int ID = Integer.valueOf(theBlock.split(":")[0]).intValue();
                    BlockData BlockData = new BlockData();
                    try
                    {
                        BlockData.DataValue = Integer.valueOf(theBlock.split(":")[1]).intValue();
                    }
                    catch (Exception localException) {}
                    ConfigurationSection BlockInfo = theSection.getConfigurationSection(theBlock);
                    Set<String> properties = BlockInfo.getKeys(false);
                    for (String Property : properties) {
                        if (Property.equals("max-value")) {
                            BlockData.maxValue = BlockInfo.getDouble(Property);
                        } else if (Property.equals("min-value")) {
                            BlockData.minValue = BlockInfo.getDouble(Property);
                        } else if (Property.equals("ore")) {
                            BlockData.ore = BlockInfo.getBoolean("ore");
                        }
                    }
                    Blocks.put(Integer.valueOf(ID), BlockData);
                }
            }
        }
        theSection = Config.getConfigurationSection("mobsettings");
        if (theSection != null)
        {
            Set<String> theKeys = theSection.getKeys(false);
            if (theKeys != null)
            {
                Iterator<String> iterator = theKeys.iterator();
                Iterator<String> it;
                for (; iterator.hasNext(); it.hasNext())
                {
                    String theMob = (String)iterator.next();
                    ConfigurationSection MobInfo = theSection.getConfigurationSection(theMob);
                    Set<String> properties = MobInfo.getKeys(false);
                    it = properties.iterator();
                    String Property;
                    Property = (String)it.next();
                    if (Property.equals("value")) {
                        MobValues.put(theMob, Double.valueOf(MobInfo.getDouble("value")));
                    }
                    continue;
                }
            }
        }
    }
    class Updater
            implements Runnable
    {
        McDrumEco plugin = null;
        public boolean running = true;

        public Updater(McDrumEco plugin)
        {
            this.plugin = plugin;
        }

        public void run()
        {
            while (this.running) {
                try
                {
                    Thread.sleep(1000 * McDrumEco.SecondsUntilPayday);
                    for (Player player : McDrumEco.this.getServer().getOnlinePlayers())
                    {
                        PlayerData playerData = (PlayerData)McDrumEco.PlayerHandler.PlayerData.get(player.getName());
                        if (playerData.moneyToGive != 0.0D)
                        {
                            String CMessage = ChatColor.translateAlternateColorCodes('&', McDrumEco.Message);
                            double ToGive = playerData.moneyToGive;
                            EconManager.setBalance(player.getName(), EconManager.getBalance(player.getName()) + ToGive);

                            CMessage = CMessage.replace("%amount%", String.valueOf(ToGive));
                            CMessage = CMessage.replace("%ores%", String.valueOf(playerData.OresMined));
                            CMessage = CMessage.replace("%blocks%", String.valueOf(playerData.BlocksMined));
                            CMessage = CMessage.replace("%mobs%", String.valueOf(playerData.MobsKilled));
                            CMessage = CMessage.replace("%players%", String.valueOf(playerData.PlayersKilled));
                            McDrumEco.this.getServer().getPlayer(player.getName()).sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Economy" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_GRAY + CMessage);

                            playerData.moneyToGive = 0.0D;
                            playerData.OresMined = 0;
                            playerData.BlocksMined = 0;
                            playerData.MobsKilled = 0;
                            playerData.PlayersKilled = 0;
                        }
                        if ((McDrumEco.onlinerewards) && (player.hasPermission("mcdrumeco.online")) &&
                                (McDrumEco.onlinerewards) && (System.currentTimeMillis() - playerData.lastReward >= McDrumEco.this.getConfig().getLong("online-time-for-reward") * 1000L))
                        {
                            String[] RewardItems = McDrumEco.this.getConfig().getString("onlinereward.item").split(";");
                            for (String Item : RewardItems)
                            {
                                String[] Info = Item.split(":");
                                int ID = Integer.valueOf(Info[0]).intValue();
                                int Data = Integer.valueOf(Info[1]).intValue();
                                int Amount = Integer.valueOf(Info[2]).intValue();
                                ItemStack Items = new ItemStack(ID, Amount);
                                Items.setData(new MaterialData(Data));

                                EconManager.setBalance(player.getName(), EconManager.getBalance(player.getName()) + McDrumEco.this.getConfig().getDouble("onlinereward.money"));
                                player.getInventory().addItem(new ItemStack[] { Items });
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Economy" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_GRAY + " " + McDrumEco.this.getConfig().getString("onlinereward.message")));
                            player.giveExp(McDrumEco.this.getConfig().getInt("onlinereward.exp"));
                            playerData.lastReward = System.currentTimeMillis();
                        }
                    }
                }
                catch (Exception localException) {}
            }
        }
    }
    public static PlayerData getPlayerData(String playerName)
    {
        if (Bukkit.getServer().getPlayer(playerName) != null)
        {
            if (PlayerHandler.PlayerData.containsKey(playerName)) {
                return (PlayerData)PlayerHandler.PlayerData.get(playerName);
            }
            PlayerHandler.PlayerData.put(playerName, new PlayerData(Bukkit.getServer().getPlayer(playerName)));
            return (PlayerData)PlayerHandler.PlayerData.get(playerName);
        }
        return new PlayerData();
    }
    public static BlockData getBlockData(int ID)
    {
        if (Blocks.containsKey(Integer.valueOf(ID))) {
            return (BlockData)Blocks.get(Integer.valueOf(ID));
        }
        return null;
    }
}
