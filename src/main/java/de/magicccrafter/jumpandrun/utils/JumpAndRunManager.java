package de.magicccrafter.jumpandrun.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class JumpAndRunManager {

    private HashMap<UUID, PlayerJumpAndRun> jumpAndRuns = new HashMap<UUID, PlayerJumpAndRun>();

    public HashMap<UUID, PlayerJumpAndRun> getJumpAndRuns() {
        return jumpAndRuns;
    }

    public PlayerJumpAndRun createAndStartPlayerJumpAndRun(Player player) {
        PlayerJumpAndRun playerUnlimitedJumpAndRun = new PlayerJumpAndRun(player);
        playerUnlimitedJumpAndRun.startJumpAndRun();
        this.jumpAndRuns.put(player.getUniqueId(), playerUnlimitedJumpAndRun);
        return playerUnlimitedJumpAndRun;
    }

    public Boolean isJumping(Player player) {
        if(this.jumpAndRuns.containsKey(player.getUniqueId())) {
            return true;
        }
        return false;
    }

    public PlayerJumpAndRun getPlayersJumpAndRun(Player player) {
        if(isJumping(player)) {
            return this.jumpAndRuns.get(player.getUniqueId());
        }
        return null;
    }

    public void stopPlayersJumpAndRun(Player player) {
        if(isJumping(player)) {
            getPlayersJumpAndRun(player).stopJumpAndRun();
            this.jumpAndRuns.remove(player.getUniqueId());
        }
    }
    
}
