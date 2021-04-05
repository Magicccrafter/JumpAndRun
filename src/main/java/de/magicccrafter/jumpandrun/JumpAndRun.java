package de.magicccrafter.jumpandrun;

import de.magicccrafter.jumpandrun.api.JumpAndRunAPI;
import de.magicccrafter.jumpandrun.commands.HighscoreCommand;
import de.magicccrafter.jumpandrun.commands.JumpAndRunCommand;
import de.magicccrafter.jumpandrun.listeners.*;
import de.magicccrafter.jumpandrun.utils.JumpAndRunManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class JumpAndRun extends JavaPlugin {

    private static JumpAndRun instance;
    private String prefix;
    private JumpAndRunManager jumpAndRunManager;
    private JumpAndRunAPI jumpAndRunAPI;

    private Boolean showActionbar;
    private Boolean activateHighscoreSystem;

    @Override
    public void onEnable() {
        instance = this;
        prefix = "§7[§cJumpAndRun§7] §a";
        jumpAndRunManager = new JumpAndRunManager();
        jumpAndRunAPI = new JumpAndRunAPI();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new MoveListener(), this);
        pluginManager.registerEvents(new BlockListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new InteractListener(), this);

        getCommand("jumpandrun").setExecutor(new JumpAndRunCommand());
        getCommand("highscore").setExecutor(new HighscoreCommand());

        setupConfiguration();

    }

    private void setupConfiguration() {
        if(getConfig().get("showActionbar") == null) {
            getConfig().set("showActionbar", true);
            this.showActionbar = true;
        } else {
            this.showActionbar = (Boolean) getConfig().get("showActionbar");
        }
        if(getConfig().get("activateHighscoreSystem") == null) {
            getConfig().set("activateHighscoreSystem", true);
            this.activateHighscoreSystem = true;
        } else {
            this.activateHighscoreSystem = (Boolean) getConfig().get("activateHighscoreSystem");
        }
        saveConfig();
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

    public JumpAndRunAPI getJumpAndRunAPI() {
        return jumpAndRunAPI;
    }

    public Boolean isHighscoreSystemActivated() {
        return activateHighscoreSystem;
    }

    public Boolean isActionbarShown() {
        return showActionbar;
    }
}
