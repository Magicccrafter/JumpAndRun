package de.magicccrafter.jumpandrun.api;

import de.magicccrafter.jumpandrun.JumpAndRun;
import de.magicccrafter.jumpandrun.utils.PlayerJumpAndRun;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class JumpAndRunAPI {

    public PlayerJumpAndRun createAndStartJumpAndRun(Player player) {
        return JumpAndRun.getInstance().getJumpAndRunManager().createAndStartPlayerJumpAndRun(player);
    }

    public PlayerJumpAndRun getPlayersJumpAndRun(Player player) {
        return JumpAndRun.getInstance().getJumpAndRunAPI().getPlayersJumpAndRun(player);
    }

    public int getCurrentPlayerScore(Player player) {
        return JumpAndRun.getInstance().getJumpAndRunManager().getPlayersJumpAndRun(player).getPoints();
    }

    public boolean isPlayerCurrentlyJumping(Player player) {
        return JumpAndRun.getInstance().getJumpAndRunManager().isJumping(player);
    }

    public void stopPlayersJumpAndRun(Player player) {
        JumpAndRun.getInstance().getJumpAndRunManager().stopPlayersJumpAndRun(player);
    }

    public HashMap<UUID, PlayerJumpAndRun> getAllPlayerJumpAndRuns() {
        return JumpAndRun.getInstance().getJumpAndRunManager().getJumpAndRuns();
    }

}
