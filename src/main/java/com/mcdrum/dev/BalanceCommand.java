package com.mcdrum.dev;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Author Hunter Sharpe
 */
public class BalanceCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can have balances!");
            return true;
        }
        Player p = (Player) sender;
        if(args.length != 0){
            p.sendMessage(String.format("%s[%sEconomy%s] Command Usage: /bal or /balance", ChatColor.DARK_GRAY, ChatColor.BLUE, ChatColor.DARK_GRAY, ChatColor.DARK_GRAY));
            return true;
        }
        p.sendMessage(String.format("%s[%sEconomy%s] %sYour balance is: " + EconManager.getBalance(p.getName()).toString(), ChatColor.DARK_GRAY, ChatColor.BLUE, ChatColor.DARK_GRAY, ChatColor.DARK_GRAY));
        return true;
    }

}
