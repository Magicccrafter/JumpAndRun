package de.magicccrafter.jumpandrun.listeners;

import de.magicccrafter.jumpandrun.JumpAndRun;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void BlockBreak(BlockBreakEvent event) {
        if(JumpAndRun.getInstance().getJumpAndRunManager().isJumping(event.getPlayer())) {
            if(event.getBlock().getLocation().equals(JumpAndRun.getInstance().getJumpAndRunManager().getPlayersJumpAndRun(event.getPlayer()).getNextLocation())) {
                event.setCancelled(true);
            }
        }
    }

}
