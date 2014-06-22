package com.mcdrum.dev;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 6/21/2014.
 */
public class KitCommand implements CommandExecutor{

    private List<String> canPurchase = new ArrayList<String>();

    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args){
        /*1200 ticks in a minute.
        72000 ticks in a hour.
        1728000 ticks in a day*/
        Player p = (Player) sender;
        if(args.length >= 3 || args.length == 0){
            p.sendMessage("Command usage: /kit <kitname> [info]");
            return true;
        }
        if(args[0].equalsIgnoreCase("1")){
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("info")){
                    p.sendMessage(String.format("%s----%sKit #%sItems%s----", ChatColor.DARK_GRAY, ChatColor.BLUE, args[0], ChatColor.DARK_GRAY));
                }else{
                    p.sendMessage("Command usage: /kit <kitname> [info]");
                }
                return true;
            }
            int price = 250;
            if(!(EconManager.getBalance(p.getName()) >= price)){
                p.sendMessage("Insufficient Balance!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "You have bought the 1st kit!");
            p.sendMessage(ChatColor.RED + "Deducted " + price + " from your account!");
            EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - 250);
            return true;
        }
        if(args[0].equalsIgnoreCase("2")){
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("info")){
                    p.sendMessage(String.format("%s----%sKit #%sItems%s----", ChatColor.DARK_GRAY, ChatColor.BLUE, args[0], ChatColor.DARK_GRAY));
                }else{
                    p.sendMessage("Command usage: /kit <kitname> [info]");
                }
                return true;
            }
            int price = 1000;
            if(!(EconManager.getBalance(p.getName()) >= price)){
                p.sendMessage("Insufficient Balance!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "You have bought the 1st kit!");
            p.sendMessage(ChatColor.RED + "Deducted " + price + " from your account!");
            EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - 1000);
            return true;
        }
        if(args[0].equalsIgnoreCase("3")){
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("info")){
                    p.sendMessage(String.format("%s----%sKit #%sItems%s----", ChatColor.DARK_GRAY, ChatColor.BLUE, args[0], ChatColor.DARK_GRAY));
                }else{
                    p.sendMessage("Command usage: /kit <kitname> [info]");
                }
                return true;
            }
            int price = 2500;
            if(!(EconManager.getBalance(p.getName()) >= price)){
                p.sendMessage("Insufficient Balance!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "You have bought the 1st kit!");
            p.sendMessage(ChatColor.RED + "Deducted " + price + " from your account!");
            EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - 2500);
            return true;
        }
        if(args[0].equalsIgnoreCase("4")){
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("info")){
                    p.sendMessage(String.format("%s----%sKit #%sItems%s----", ChatColor.DARK_GRAY, ChatColor.BLUE, args[0], ChatColor.DARK_GRAY));
                }else{
                    p.sendMessage("Command usage: /kit <kitname> [info]");
                }
                return true;
            }
            int price = 5000;
            if(!(EconManager.getBalance(p.getName()) >= price)){
                p.sendMessage("Insufficient Balance!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "You have bought the 1st kit!");
            p.sendMessage(ChatColor.RED + "Deducted " + price + " from your account!");
            EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - 5000);
            return true;
        }
        if(args[0].equalsIgnoreCase("5")){
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("info")){
                    p.sendMessage(String.format("%s----%sKit #%sItems%s----", ChatColor.DARK_GRAY, ChatColor.BLUE, args[0], ChatColor.DARK_GRAY));
                }else{
                    p.sendMessage("Command usage: /kit <kitname> [info]");
                }
                return true;
            }
            int price = 10000;
            if(!(EconManager.getBalance(p.getName()) >= price)){
                p.sendMessage("Insufficient Balance!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "You have bought the 1st kit!");
            p.sendMessage(ChatColor.RED + "Deducted " + price + " from your account!");
            EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - 10000);
            return true;
        }
        if(args[0].equalsIgnoreCase("6")){
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("info")){
                    p.sendMessage(String.format("%s----%sKit #%sItems%s----", ChatColor.DARK_GRAY, ChatColor.BLUE, args[0], ChatColor.DARK_GRAY));
                }else{
                    p.sendMessage("Command usage: /kit <kitname> [info]");
                }
                return true;
            }
            int price = 25000;
            if(!(EconManager.getBalance(p.getName()) >= price)){
                p.sendMessage("Insufficient Balance!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "You have bought the 1st kit!");
            p.sendMessage(ChatColor.RED + "Deducted " + price + " from your account!");
            EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - 25000);
            return true;
        }
        if(args[0].equalsIgnoreCase("7")){
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("info")){
                    p.sendMessage(String.format("%s----%sKit #%sItems%s----", ChatColor.DARK_GRAY, ChatColor.BLUE, args[0], ChatColor.DARK_GRAY));
                }else{
                    p.sendMessage("Command usage: /kit <kitname> [info]");
                }
                return true;
            }
            int price = 50000;
            if(!(EconManager.getBalance(p.getName()) >= price)){
                p.sendMessage("Insufficient Balance!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "You have bought the 1st kit!");
            p.sendMessage(ChatColor.RED + "Deducted " + price + " from your account!");
            EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - 50000);
            return true;
        }
        if(args[0].equalsIgnoreCase("8")){
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("info")){
                    p.sendMessage(String.format("%s----%sKit #%sItems%s----", ChatColor.DARK_GRAY, ChatColor.BLUE, args[0], ChatColor.DARK_GRAY));
                }else{
                    p.sendMessage("Command usage: /kit <kitname> [info]");
                }
            }
            int price = 100000;
            if(!(EconManager.getBalance(p.getName()) >= price)){
                p.sendMessage("Insufficient Balance!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "You have bought the 1st kit!");
            p.sendMessage(ChatColor.RED + "Deducted " + price + " from your account!");
            EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - 100000);

            return true;
        }
        if(args[0].equalsIgnoreCase("9")){
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("info")){
                    p.sendMessage(String.format("%s----%sKit #%sItems%s----", ChatColor.DARK_GRAY, ChatColor.BLUE, args[0], ChatColor.DARK_GRAY));
                }else{
                    p.sendMessage("Command usage: /kit <kitname> [info]");
                }
                return true;
            }
            int price = 500000;
            if(!(EconManager.getBalance(p.getName()) >= price)){
                p.sendMessage("Insufficient Balance!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "You have bought the 1st kit!");
            p.sendMessage(ChatColor.RED + "Deducted " + price + " from your account!");
            EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - 500000);
            return true;
        }
        if(args[0].equalsIgnoreCase("10")){
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("info")){
                    p.sendMessage(String.format("%s----%sKit #%sItems%s----", ChatColor.DARK_GRAY, ChatColor.BLUE, args[0], ChatColor.DARK_GRAY));
                }else{
                    p.sendMessage("Command usage: /kit <kitname> [info]");
                }
                return true;
            }
            int price = 1000000;
            if(!(EconManager.getBalance(p.getName()) >= price)){
                p.sendMessage("Insufficient Balance!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "You have bought the 1st kit!");
            p.sendMessage(ChatColor.RED + "Deducted " + price + " from your account!");
            EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - 1000000);
            return true;
        }



        return true;
    }

}
