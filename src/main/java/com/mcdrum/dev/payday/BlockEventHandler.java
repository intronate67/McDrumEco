package com.mcdrum.dev.payday;

import com.mcdrum.dev.McDrumEco;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * @Author Hunter Sharpe
 */
public class BlockEventHandler implements Listener{

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        Player player = event.getPlayer();
        if ((player != null) && (player.hasPermission("apocpayday.mining")))
        {
            Block block = event.getBlock();
            BlockData BlockData = McDrumEco.getBlockData(block.getTypeId());
            if (BlockData != null)
            {
                boolean valid = true;
                for (Location l : McDrumEco.PlayerHandler.PlayerPlacedBlocks) {
                    if (l != null)
                    {
                        Location bl = event.getBlock().getLocation();
                        if ((bl != null) &&
                                ((int)l.getX() == (int)bl.getX()) && ((int)l.getY() == (int)bl.getY()) && ((int)bl.getZ() == l.getZ()))
                        {
                            McDrumEco.PlayerHandler.PlayerPlacedBlocks.remove(l);
                            valid = false;
                            break;
                        }
                    }
                }
                if ((event.getPlayer().getGameMode() == GameMode.CREATIVE) && (!McDrumEco.creativeMining)) {
                    valid = false;
                }
                if (valid)
                {
                    PlayerData playerData = McDrumEco.getPlayerData(player.getName());
                    //Main.Economy.addMoney(player, BlockData.minValue, BlockData.maxValue);
                    if (BlockData.ore) {
                        playerData.OresMined += 1;
                    } else {
                        playerData.BlocksMined += 1;
                    }
                }
            }
        }
    }

}
