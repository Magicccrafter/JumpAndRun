package de.magicccrafter.jumpandrun;

import de.magicccrafter.jumpandrun.commands.JumpAndRunCommand;
import de.magicccrafter.jumpandrun.listeners.BlockListener;
import de.magicccrafter.jumpandrun.listeners.MoveListener;
import de.magicccrafter.jumpandrun.utils.JumpAndRunManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class JumpAndRun extends JavaPlugin {

    private static JumpAndRun instance;
    private String prefix;
    private JumpAndRunManager jumpAndRunManager;

    @Override
    public void onEnable() {
        instance = this;
        prefix = "§7[§cJumpAndRun§7] §a";
        jumpAndRunManager = new JumpAndRunManager();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new MoveListener(), this);
        pluginManager.registerEvents(new BlockListener(), this);

        getCommand("jumpandrun").setExecutor(new JumpAndRunCommand());
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDisable() {

    }

    public static JumpAndRun getInstance() {
        return instance;
    }

    public String getPrefix() {
        return prefix;
    }

    public JumpAndRunManager getJumpAndRunManager() {
        return jumpAndRunManager;
    }
}
