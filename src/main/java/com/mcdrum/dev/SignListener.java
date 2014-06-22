package com.mcdrum.dev;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by Admin on 6/22/2014.
 */
public class SignListener implements Listener{

    private HashMap<Location, String> signs = new HashMap<Location, String>();

    @EventHandler
    public void onSignCreate(SignChangeEvent e){

        Player p = e.getPlayer();

        if(e.getLine(0).equalsIgnoreCase("mcdrum") || e.getLine(0).equalsIgnoreCase("eco")){
            signs.put(e.getBlock().getLocation(), e.getPlayer().getName());
            String line2 = e.getLine(2);
            e.setLine(0, "[McDrum]");
            e.setLine(2, "$" + line2);
            e.getPlayer().sendMessage("Created an Economy Sign with the details below.");
            e.getPlayer().sendMessage("Block Type: " + e.getLine(1));
            e.getPlayer().sendMessage("Price: " + e.getLine(2));
            e.getPlayer().sendMessage("Amount: " + e.getLine(3));
        }else{
            return;
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        double balance = EconManager.getBalance(p.getName());
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR){
            return;
        }

        if (signs.containsKey(e.getClickedBlock().getLocation())) {
            Block block = e.getClickedBlock();
            Sign sign = (Sign) block.getState();
            String[] lines = sign.getLines();
            Material mat = Material.getMaterial(lines[1].toUpperCase());
            double price = Double.parseDouble(lines[2].replace("$", " "));
            int amount = Integer.parseInt(lines[3]);
            if (amount == 64) {
                if (!(balance >= price)) {
                    p.sendMessage(ChatColor.RED + "Insufficient Balance!");
                    return;

                }
                Inventory inv = p.getInventory();
                ItemStack items = new ItemStack(mat);
                items.setAmount(64);
                inv.addItem(items);
                p.sendMessage(ChatColor.GREEN + "You have bought 64 " + items.toString() + "s");
                EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - price);
                return;
            } else {
                if (!(balance >= price)) {
                    p.sendMessage(ChatColor.RED + "Insufficient Balance!");
                    return;
                }
                Inventory inv = p.getInventory();
                ItemStack items = new ItemStack(mat);
                items.setAmount(1);
                inv.addItem(items);
                p.sendMessage(ChatColor.GREEN + "You have bought 1 " + items.toString());
                EconManager.setBalance(p.getName(), EconManager.getBalance(p.getName()) - price);
            }


        }
    }
}
