package project.map;

import project.entity.*;
import project.victory.*;

import java.util.HashMap;
import java.util.List;

/**
 * The mastermind behind the game's operation. (Manages and orchestrates the operation of the game.)
 */
public class Mastermind implements Cloneable {
    private Map map;
    private Victory endGameConditions;

    /**
     * Updates the current state of the game, progressing the game by one tick.
     */
    public void updateGame() {
        map.updateMap();
    }

    /**
     * Checks if the player has satisfied all conditions for victory.
     * @return true if all conditions are satisfied, and false otherwise.
     */
    public boolean endGame() {
        return endGameConditions.victorySatisfied(map.getEntities());
    }

    /**
     * Checks if the current map contains a player.
     * @return true if there exists a player in the map, and false otherwise.
     */
    public boolean playerExit() {
        for (Entity e : map.getEntities()) {
            if (e instanceof Player) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the current game's map to the given map.
     * @param map The aforementioned map.
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * @return A list of entities currently in the map.
     */
    public List<Entity> getMapEntities() {
        return map.getEntities();
    }

    public void setEndGameConditions(Victory endGameConditions) {
        this.endGameConditions = endGameConditions;
    }

    /**
     * @return A player's current inventory
     */
    public HashMap<String,Integer> delegateGetInventory() {
        return map.getInventory();
    }

}
