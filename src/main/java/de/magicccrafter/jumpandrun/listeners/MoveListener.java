package de.magicccrafter.jumpandrun.listeners;

import de.magicccrafter.jumpandrun.JumpAndRun;
import de.magicccrafter.jumpandrun.utils.PlayerJumpAndRun;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    @EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        if(JumpAndRun.getInstance().getJumpAndRunManager().isJumping(event.getPlayer())) {
            PlayerJumpAndRun jumpAndRun = JumpAndRun.getInstance().getJumpAndRunManager().getPlayersJumpAndRun(event.getPlayer());
            Block block = event.getPlayer().getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock();
            if(block.getLocation().equals(jumpAndRun.getNextLocation()) && block.getType().equals(Material.CHISELED_STONE_BRICKS)) {
                jumpAndRun.createNextJumpAndRunBlock();
            }
            if(event.getPlayer().getLocation().getY() < jumpAndRun.getCurrentLocation().getY()) {
                JumpAndRun.getInstance().getJumpAndRunManager().stopPlayersJumpAndRun(event.getPlayer());
            }
        }
    }

}
