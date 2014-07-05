package com.mcdrum.dev;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Admin on 6/21/2014.
 */
public class EconCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender cs, Command command, String s, String[] args)
    {
        Player p = (Player) cs;
        if(!p.hasPermission("mcdrumeco.eco")){
            p.sendMessage("You cannot do this!");
            return true;
        }
        if(args.length >=4 || args.length == 0)
        {
            cs.sendMessage(ChatColor.GREEN+"Usage: /eco <add/remove/set/balance> [player] [amount]");
            return true;
        }
        if(args[0].equalsIgnoreCase("add"))
        {
            if(!p.hasPermission("mcdrumeco.add")){
                p.sendMessage(ChatColor.RED + "You cannot do this!");
                return true;
            }
            if(args.length != 3){
                p.sendMessage(ChatColor.RED + "Command Usage: /eco add <player> <amount>");
                return true;
            }
            if(!EconManager.hasAccount(args[1]))
            {
                cs.sendMessage(ChatColor.RED+"Error: Player does not have an account");
                return true;
            }
            double amount = 0;
            try
            {
                amount = Double.parseDouble(args[2]);
            }catch (Exception e)
            {
                cs.sendMessage(ChatColor.RED+"You gotta enter in a number bro");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "Added" + amount + "player: " + args[1] + "'s balance.");
            EconManager.setBalance(args[1], EconManager.getBalance(args[1]) + amount);
        }else if (args[0].equalsIgnoreCase("remove"))
        {
            if(!p.hasPermission("mcdrumeco.remove")){
                p.sendMessage(ChatColor.RED + "You cannot do this!");
                return true;
            }
            if(args.length != 3){
                p.sendMessage(ChatColor.RED + "Command Usage: /eco remove <player> <amount>");
                return true;
            }
            if(!EconManager.hasAccount(args[1]))
            {
                cs.sendMessage(ChatColor.RED+"Error: Player does not have an account");
                return true;
            }
            double amount = 0;
            try
            {
                amount = Double.parseDouble(args[2]);
            }catch (Exception e)
            {
                cs.sendMessage(ChatColor.RED+"You gotta enter in a number bro");
                return true;
            }
            p.sendMessage(ChatColor.RED + "Deducted " + amount + " from player: " + args[1] + "'s balance.");
            EconManager.setBalance(args[1], EconManager.getBalance(args[1]) - amount);
        }else if (args[0].equalsIgnoreCase("set"))
        {
            if(!p.hasPermission("mcdrumeco.set")){
                p.sendMessage(ChatColor.RED + "You cannot do this!");
                return true;
            }
            if(args.length != 3){
                p.sendMessage(ChatColor.RED + "Command Usage: /eco set <player> <amount>");
                return true;
            }
            if(!EconManager.hasAccount(args[1]))
            {
                cs.sendMessage(ChatColor.RED+"Error: Player does not have an account");
                return true;
            }
            double amount = 0;
            try
            {
                amount = Double.parseDouble(args[2]);
            }catch (Exception e)
            {
                cs.sendMessage(ChatColor.RED+"You gotta enter in a number bro");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "Set player: " + args[1] + "'s balance to " + amount);
            EconManager.setBalance(args[1], amount);
        }else if(args[0].equalsIgnoreCase("balance")){
            if(args.length >= 3){
                p.sendMessage("Command Usage: /eco balance [player]");
                return true;
            }
            if(args.length == 2){
                double balance = EconManager.getBalance(args[1]);
                p.sendMessage(ChatColor.BLUE + args[1] + "'s" + ChatColor.DARK_GRAY +"balance is: " + ChatColor.GREEN + balance);
                return true;
            }
            double balance = EconManager.getBalance(p.getName());
            p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Balance" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_GRAY + "Your current balance is: " + ChatColor.GREEN +balance);
        }else if(args[0].equalsIgnoreCase("top")){
            EconManager.getTopBal(p);
        }

        else if(args[0].equalsIgnoreCase("getbal")){
            if(args.length != 2){
                p.sendMessage("Command usage: /eco getbal <player>");
                return true;
            }
            double balance = EconManager.getBalance(args[1]);
            p.sendMessage(String.valueOf(balance));
        }else
        {
            cs.sendMessage(ChatColor.RED+"Incorrect argument");
        }
        return true;
    }
}
