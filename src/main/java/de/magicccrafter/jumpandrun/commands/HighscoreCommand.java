package de.magicccrafter.jumpandrun.commands;

import de.magicccrafter.jumpandrun.JumpAndRun;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HighscoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.sendMessage(JumpAndRun.getInstance().getPrefix() + "§6Dein aktueller Highscore§7: §4" + JumpAndRun.getInstance().getJumpAndRunAPI().getPlayersHighscoreData(player.getUniqueId()).getHighscore() + " Punkte");
        }
        return false;
    }

}
