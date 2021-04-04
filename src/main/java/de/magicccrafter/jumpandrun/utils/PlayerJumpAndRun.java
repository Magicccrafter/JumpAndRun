package de.magicccrafter.jumpandrun.utils;

import de.magicccrafter.jumpandrun.JumpAndRun;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Random;

public class PlayerJumpAndRun {

    private Player player;
    private Random random;
    private Location spawnLocation;
    private Location currentLocation;
    private Location nextLocation;
    private Location lastLocation;
    private Boolean finished;
    private Boolean started;
    private Integer actionbarScheduler;
    private Integer points;

    public PlayerJumpAndRun(Player player) {
        this.player = player;
        this.spawnLocation = player.getLocation();
        this.random = new Random();
        this.finished = false;
        this.started = false;
        this.points = 0;
    }

    public void startJumpAndRun() {
        this.started = true;

        Double blockX = (double) this.random.nextInt(10 - -10) + -10;
        Double blockY = (double) ((Location) JumpAndRun.getInstance().getConfig().get("JumpAndRunStartLocation")).getBlockY();
        Double blockZ = (double) this.random.nextInt(10 - -10) + -10;

        Location blockLocation = new Location(this.spawnLocation.getWorld(), ((Location) JumpAndRun.getInstance().getConfig().get("JumpAndRunStartLocation")).getBlockX() + blockX, blockY, ((Location) JumpAndRun.getInstance().getConfig().get("JumpAndRunStartLocation")).getBlockZ() + blockZ);
        this.currentLocation = blockLocation;

        this.player.teleport(new Location(this.currentLocation.getWorld(), this.currentLocation.getX(), this.currentLocation.getY() + 1, this.currentLocation.getZ()));

        this.startActionbarScheduler();
        this.createNextJumpAndRunBlock();
    }

    public void createNextJumpAndRunBlock() {
        this.currentLocation = player.getLocation().add(0, -1, 0);

        Double blockX = (double) this.random.nextInt(4 - -3) + -3;
        Double blockY = (double) this.random.nextInt(2);
        Double blockZ = (double) this.random.nextInt(4 - -3) + -3;

        Location blockLocation = new Location(this.spawnLocation.getWorld(), this.currentLocation.getBlockX() + blockX, this.currentLocation.getBlockY() + blockY, this.currentLocation.getBlockZ() + blockZ);

        if(blockLocation.getBlock().getType() != Material.AIR || blockLocation.distance(this.currentLocation) <= 3) {
            this.currentLocation = player.getLocation().add(0, 1, 0);
            this.createNextJumpAndRunBlock();
            return;
        }

        this.nextLocation = blockLocation;

        if(this.lastLocation != null) {
            this.lastLocation.getBlock().setType(Material.AIR);
            this.points++;
        }

        this.createBlock(this.currentLocation, Material.STONE_BRICKS);
        this.lastLocation = this.currentLocation;
        this.createBlock(this.nextLocation, Material.CHISELED_STONE_BRICKS);
        this.player.playSound(this.player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
    }

    public void stopJumpAndRun() {
        this.player.playSound(this.player.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1, 1);
        this.removeBlock(this.currentLocation);
        this.removeBlock(this.nextLocation);
        Bukkit.getScheduler().cancelTask(this.actionbarScheduler);
        this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(" "));
    }

    public void startActionbarScheduler() {
        this.actionbarScheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(JumpAndRun.getInstance(), new Runnable() {
            @Override
            public void run() {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aJumpAndRun §7| §cPunkte§7: §4" + points + " §7| §2" + player.getName()));
            }
        }, 0, 5);
    }

    public void createBlock(Location location, Material material) {
        location.getBlock().setType(material);
    }

    public void removeBlock(Location location) {
        location.getBlock().setType(Material.AIR);
    }

    public Location getNextLocation() {
        return nextLocation;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Player getPlayer() {
        return player;
    }
}
