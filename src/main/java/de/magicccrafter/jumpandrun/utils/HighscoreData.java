package de.magicccrafter.jumpandrun.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HighscoreData {

    private UUID uuid;
    private File file;
    private FileConfiguration fileConfiguration;

    public HighscoreData() {
        this.file = new File("plugins/JumpAndRun/Highscores", "highscores.yml");
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    private void saveData() {
        try {
            this.fileConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getHighscore() {
        if(this.fileConfiguration.getList("Highscores") == null) {
            return 0;
        } else {
            List<String> highscores = this.fileConfiguration.getStringList("Highscores");
            for(String string : highscores) {
                if(string.contains(this.uuid.toString())) {
                    Integer points = Integer.parseInt(string.split(":")[1]);
                    return points;
                }
            }
            return 0;
        }
    }

    public void setHighscore(Integer score) {
        if(this.fileConfiguration.getList("Highscores") == null) {
            List<String> highscores = new ArrayList<String>();
            highscores.add(this.uuid.toString() + ":" + score);
            this.fileConfiguration.set("Highscores", highscores);
        } else {
            List<String> highscores = this.fileConfiguration.getStringList("Highscores");
            String highscore = null;
            for(String string : highscores) {
                if(string.contains(this.uuid.toString())) {
                    highscore = string;
                }
            }
            if(highscore != null) {
                highscores.remove(highscore);
            }
            highscores.add(this.uuid.toString() + ":" + score);
            this.fileConfiguration.set("Highscores", highscores);
        }
        this.saveData();
    }

    public FileConfiguration getRawFileConfiguration() {
        return this.fileConfiguration;
    }

}
