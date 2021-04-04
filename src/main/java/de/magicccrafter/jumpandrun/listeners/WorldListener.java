package de.magicccrafter.jumpandrun.listeners;

import de.magicccrafter.jumpandrun.JumpAndRun;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldListener implements Listener {

    @EventHandler
    public void WorldChange(PlayerChangedWorldEvent event) {
        if(JumpAndRun.getInstance().getJumpAndRunAPI().isPlayerCurrentlyJumping(event.getPlayer())) {
            JumpAndRun.getInstance().getJumpAndRunAPI().stopPlayersJumpAndRun(event.getPlayer());
        }
    }

}
