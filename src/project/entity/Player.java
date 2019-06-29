package project.entity;

import java.util.HashMap;

import project.map.Map;
import project.move.Action;
import project.move.PlayerInput;

/**
 * The player. Can be controlled by the user playing the game.
 */
public class Player extends Entity implements Updatable {
    private HashMap<String,Integer> inventory;
    PlayerInput input;
    Color heldKey;

    /**
     * @param xPos The player's x position
     * @param yPos The player's y position
     * @param map The map that the player is a part of.
     */
    public Player(int xPos, int yPos, Map map) {
        super(xPos, yPos, map);
        inventory = new HashMap<String,Integer>();
        heldKey = Color.NONE;
        input = new PlayerInput();
        setGraph("project/graphics/player.png");
    }

    /**
     * Check if the player has a particular item in their inventory.
     * @param item The item type to be checked.
     * @return The amount of the item currently held or null if player does not have this item
     */
    public int playerHas(String item){
        if (inventory.get(item) == null) return 0;
        return inventory.get(item);
    }

    /**
     * Stores a specified quantity of an item in the inventory
     * @param item The item to be stored
     * @param amount The quantity of the item to be stored
     */
    public void storeItemNum(String item, int amount){
        int current = 0;
        if (inventory.containsKey(item)){
            current = inventory.get(item);
        }
        current += amount;
        if (item.equals("Invincibility")){
            current = amount;
        }
        inventory.put(item, current);
    }

    /**
     * Called by any enemies are on the same position as the player.
     * @return true if the player is to be victorious in this battle and false otherwise.
     * 
     * reduce durability of weapon and refresh status ===> used when enemy move onto player or player moves onto enemy
     */
    public Boolean battle(){
        if (playerHas("Invinciblility") > 0) {
            return true;
        } else if (playerHas("Sword") > 0) {
            return true;
        }
        return false;
    }

    /**
     * This method is called whenever an updateable entity tries to move onto the same location as this entity.
     * @param update The updateable entity that moved onto this entity.
     * @return true if the updateable is allowed to move onto this location, and false otherwise.
     */
    @Override
    public boolean onMove(Updatable update) {
        // this update can only be enemy
        // check if player can destroy the battle
        if (!battle()) return true;
        // enemy dsnt feed
        return false;
    }

    /**
     * @return A Map representing the player's current inventory state.
     */
    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

	@Override
    public String toString() {
        return "Player";
    }

    /**
     * Sets the key held by the player to the key color given.
     * If the player already has a key, then the key can only be set to Color.NONE.
     * @param key The key color to set on the player.
     * @return true if the key was successfully set, and false otherwise.
     */
    public Boolean setKey(Color key) {
        if (heldKey == Color.NONE || key == Color.NONE) {
            heldKey = key;
            return true;
        }
        return false;
    }

    /**
     * Retrieves the color of the key currently held by the player.
     * @return The aforementioned color, or Color.NONE if the player does not have a key.
     */
    public Color getKey() {
        return heldKey;
    }

    @Override
    public boolean update() {
        // Get input action
        Action action = input.getNext();
        while (action == null) {            // Keep trying until an action can be retrieved.
            try {
                System.out.println("No action for the player found. Waiting for input...");
                Thread.sleep(100);
            } catch (Exception e) {
                // Some amazing exception handling.
            }
            action = input.getNext();
        }
        
        execute(action);
        // update player status
        int invincibleTime = playerHas("Invincibility");
        if (invincibleTime > 0) {
            storeItemNum("Invincibility", --invincibleTime);
        }
        return true;
    }

    /**
     * Executes the given action using this player.
     * @param action The action to be executed.
     */
    private void execute(Action action) {
        switch(action) {
            case MOVE_UP:       move(Direction.UP); break;
            case MOVE_DOWN:     move(Direction.DOWN); break;
            case MOVE_LEFT:     move(Direction.LEFT); break;
            case MOVE_RIGHT:    move(Direction.RIGHT); break;
            case ARROW_UP:      fireArrow(Direction.UP); break;
            case ARROW_DOWN:    fireArrow(Direction.DOWN); break;
            case ARROW_LEFT:    fireArrow(Direction.LEFT); break;
            case ARROW_RIGHT:   fireArrow(Direction.RIGHT); break;
            case BOMB:          placeBomb(); break;
            default:            break;
        }
    }

    private void fireArrow(Direction arrowDir) {
        if (playerHas("Arrow") > 0 && arrowDir != null) {
            System.out.println("Arrow direction is " + arrowDir);
            map.addEntity(new FiredArrow(getxPos(), getyPos(), map, arrowDir));
            storeItemNum("Arrow", -1);
        }
    }
    
    private void placeBomb() {
        if (playerHas("Bomb") > 0) {
            map.addEntity(new LitBomb(getxPos(), getyPos(), map));
            storeItemNum("Bomb", -1);
        }
    }

    /**
     * Tries to move the player in the given direction.
     * @param dir The direction to move
     */
    private void move(Direction dir) {
        int moveX, moveY;
        moveX = getxPos();
        moveY = getyPos();
        switch (dir) {
            case LEFT:  moveX--; break;
            case RIGHT: moveX++; break;
            case UP:    moveY--; break;
            case DOWN:  moveY++; break;
            default: break;
        }
        
        for (Entity e : map.entitiesAtPos(moveX, moveY)) {
            if (e instanceof Enemy) {
                if (e.onMove(this)) {
                    // decrement durability by 1
                    storeItemNum("Sword",-1);
                    map.removeEntity(e);
                } else {
                    // otherwise player died
                    map.removeEntity(this);
                }
            }
            if (!e.onMove(this)) return;           // If can't move to the desired location, do nothing.
        }
        // Otherwise, move successful. Update position to the new position.
        setxPos(moveX);
        setyPos(moveY);
    }

    /**
     * Adds the given action to the player's list of actions to be executed.
     * @param action The action that the player is to execute.
     */
    public void addAction(Action action) {
    	input.addAction(action);
    }

    public void setGraph(String src) {
        setGraphic(src);
    }
}
