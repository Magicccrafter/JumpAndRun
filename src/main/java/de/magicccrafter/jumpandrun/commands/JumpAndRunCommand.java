package de.magicccrafter.jumpandrun.commands;

import de.magicccrafter.jumpandrun.JumpAndRun;
import de.magicccrafter.jumpandrun.utils.PlayerJumpAndRun;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JumpAndRunCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.hasPermission("jumpandrun.admin")) {
                if(strings.length == 1) {
                    if(strings[0].equalsIgnoreCase("setstartlocation")) {
                        JumpAndRun.getInstance().getConfig().set("JumpAndRunStartLocation", player.getLocation());
                        JumpAndRun.getInstance().saveConfig();
                        player.sendMessage(JumpAndRun.getInstance().getPrefix() + "Du hast die Start Koordinaten gesetzt");
                        return true;
                    } else if(strings[0].equalsIgnoreCase("list")) {
                        player.sendMessage(JumpAndRun.getInstance().getPrefix() + "§2Laufende Jump And Runs");
                        for(PlayerJumpAndRun playerJumpAndRun : JumpAndRun.getInstance().getJumpAndRunManager().getJumpAndRuns().values()) {
                            player.sendMessage(JumpAndRun.getInstance().getPrefix() + "§7- §e" + playerJumpAndRun.getPlayer().getName());
                        }
                        return true;
                    } else if(strings[0].equalsIgnoreCase("test")) {
                        JumpAndRun.getInstance().getJumpAndRunManager().createAndStartPlayerJumpAndRun(player);
                        return true;
                    } else if(strings[0].equalsIgnoreCase("setupstick")) {
                        ItemStack stick = new ItemStack(Material.STICK);
                        ItemMeta stickMeta = stick.getItemMeta();
                        stickMeta.setDisplayName("§aJNR: SetupStick");
                        stick.setItemMeta(stickMeta);
                        player.getInventory().addItem(stick);
                        player.sendMessage(JumpAndRun.getInstance().getPrefix() + "§aDu hast den Setup Stick erhalten. Wenn du den JumpAndRun Start Punkt setzten möchtest §c(z.B. DRUCKPLATTE ODER KNOPF) §amache ein Linksklick darauf");
                        return true;
                    }
                    help(player);
                } else {
                    help(player);
                }
            } else {
                player.sendMessage(JumpAndRun.getInstance().getPrefix() + "§4Dazu hast du nicht genügend Berechigungen");
            }
         }
        return false;
    }

    private void help(Player player) {
        player.sendMessage(JumpAndRun.getInstance().getPrefix() + "§2JumpAndRun Admin Hilfe");
        player.sendMessage(JumpAndRun.getInstance().getPrefix() + "§e/jumpandrun setstartlocation §7- §eSetzt die Start Koordinaten");
        player.sendMessage(JumpAndRun.getInstance().getPrefix() + "§e/jumpandrun list §7- §eListet alle aktiven JumpAndRuns auf");
        player.sendMessage(JumpAndRun.getInstance().getPrefix() + "§e/jumpandrun test §7- §eStartet ein Test Jump And Run");
        player.sendMessage(JumpAndRun.getInstance().getPrefix() + "§e/jumpandrun setupstick §7- §eGebe dir den SetupStick");
    }

}
