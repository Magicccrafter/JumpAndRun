# JumpAndRun
 
This is a JumpAndRun Plugin based on the SpigotAPI. The Plugin creates a random JumpAndRun for every player. For example, you can use it in a Minigame Lobby. Also you can set the JumpAndRun spawn with a Command.

**Temporary Test Server** dev.magiccrafter.de

## Current Features

- Actionbar with the current Score
- Admin command to set the jump and run spawn and test the jump and run
- API
- Highscore System
- Commands: /jumpandrun, /highscore

## Work in progress Features

- Better API
- Custom Jump And Run Block

## Installation
1. Put the plugin in the Plugins folder. You can find it in the Releases tab
2. Restart the server

3. If you want that the player right clicks on a button, or go to a pressure plate, you can use the setup stick (/jumpandrun setupstick). If you left click on someting, its set as the block, pressure plate or button. Every time you can reset this.

## Configuration
- `showActionbar: true`
You can configure if the actionbar is shown
- `activateHighscoreSystem: true`
You can configure if the highscore system is active

# API
**Important!** This API is not the final API of the plugin. Every time there can be a change.
**Important!** The Listener in the example used, is from the Bukkit API and not from the JumpAndRunAPI

**Example**
```java
import de.magicccrafter.jumpandrun.utils.PlayerJumpAndRun;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class APIExample implements Listener {

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        PlayerJumpAndRun playerJumpAndRun = JumpAndRun.getInstance().getJumpAndRunAPI().createAndStartJumpAndRun(event.getPlayer());

        //Get the current JumpAndRun score
        Integer score = JumpAndRun.getInstance().getJumpAndRunAPI().getCurrentPlayerScore(event.getPlayer());
        //or
        Integer otherScore = playerJumpAndRun.getPoints();

        //Methods from the PlayerJumpAndRun class
        playerJumpAndRun.getPlayer();
        playerJumpAndRun.stopJumpAndRun();
        playerJumpAndRun.startJumpAndRun();
        playerJumpAndRun.createNextJumpAndRunBlock();
        playerJumpAndRun.getPoints();
        playerJumpAndRun.getCurrentLocation();
        playerJumpAndRun.getNextLocation();


        //Stop JumpAndRun after 1 Minute
        Bukkit.getScheduler().runTaskLater(JumpAndRun.getInstance(), new Runnable() {
            @Override
            public void run() {
                //Check if player is Jumping and if, stop the JumpAndRun
                if(JumpAndRun.getInstance().getJumpAndRunAPI().isPlayerCurrentlyJumping(event.getPlayer())) {
                    JumpAndRun.getInstance().getJumpAndRunAPI().stopPlayersJumpAndRun(event.getPlayer());
                }
            }
        }, 1200);

    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event) {
        //If the player jumps a message will be broadcastet to the chat
        if(JumpAndRun.getInstance().getJumpAndRunAPI().isPlayerCurrentlyJumping(event.getPlayer())) {
            Bukkit.broadcastMessage(event.getPlayer().getName() + " has quit whilst jumping with a score of " + JumpAndRun.getInstance().getJumpAndRunAPI().getCurrentPlayerScore(event.getPlayer()) + " Points 0_0");
        }
    }
}
```

## Pictures

![jumpandrun1](https://user-images.githubusercontent.com/67484571/113927046-4411e980-97ed-11eb-9488-d69422a3b461.PNG)
![jumpandrun2](https://user-images.githubusercontent.com/67484571/113927053-470cda00-97ed-11eb-9f4d-79ae55f5b158.png)
![jumpandrun3](https://user-images.githubusercontent.com/67484571/113927063-4aa06100-97ed-11eb-8c29-d2495f7271da.png)


