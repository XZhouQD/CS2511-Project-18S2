package project.entity;

import java.util.List;

import project.map.Map;

/**
 * A weapon that has been used by the player.
 */
public class LitBomb extends Entity implements Updatable {
    private int countDown; // default 5 gameticks

    /**
     * @param xPos The weapon's x position
     * @param yPos The weapon's y position
     * @param map The map that the weapon is a part of.
     */
    public LitBomb(int xPos, int yPos, Map map) {
        super(xPos, yPos, map);
        countDown = 5;
        setGraphic("project/graphics/bomb_lit_3.png");
    }

    @Override
    public boolean onMove(Updatable update) {
        return false;   // Nothing can move onto bombs.
    }

    @Override
    public boolean update() {
        if (countDown > 0){
            countDown--;
        	if (countDown <= 3) setGraphic("project/graphics/bomb_lit_2.png");        	
        	if (countDown <= 1) setGraphic("project/graphics/bomb_lit_1.png");
        } else {
            // bomb count down
            System.out.println("Banggg!!!!");

            // find the position on TOP, RIGHT, LEFT, DOWN and remove entities;
            int currX = getxPos();
            int currY = getyPos();

            // TOP
            explosion(currX, currY + 1, map);
            // RIGHT
            explosion(currX + 1,currY, map);
            // DOWN
            explosion(currX,currY - 1, map);
            // LEFT
            explosion(currX - 1,currY, map);

            // bomb is disposed after explosion
            return false;
        }
        return true;
    }

    /**
     * 'Creates' an explosion that destroys any players and enemies at the given location in the map
     * @param currX The x position of the explosion.
     * @param currY The y position of the explosion.
     * @param current The map in which the explosion is to occur.
     */
    private void explosion(int currX, int currY, Map current) {
        List<Entity> stack = current.entitiesAtPos(currX,currY);
        for (Entity temp : stack){
            if (temp instanceof Player || temp instanceof Enemy){
                // destroy enemy or player
                current.removeEntity(temp);
            }
        }
    }

}
