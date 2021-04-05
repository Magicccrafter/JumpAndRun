package de.magicccrafter.jumpandrun.listeners;

import de.magicccrafter.jumpandrun.JumpAndRun;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent event) {
        if(event.getClickedBlock() == null) return;
        if(event.getAction().equals(Action.PHYSICAL) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(event.getClickedBlock().getLocation().equals(JumpAndRun.getInstance().getConfig().get("StartInteractBlock"))) {
                JumpAndRun.getInstance().getJumpAndRunManager().createAndStartPlayerJumpAndRun(event.getPlayer());
            }
        }
        if(event.getItem() == null) return;
        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aJNR: SetupStick")) {
            event.setCancelled(true);
            JumpAndRun.getInstance().getConfig().set("StartInteractBlock", event.getClickedBlock().getLocation());
            JumpAndRun.getInstance().saveConfig();
            event.getPlayer().sendMessage(JumpAndRun.getInstance().getPrefix() + "§aDer Block wurde gesetzt. Er kann jede Zeit neugesetzt werden.");
        }
    }

}
