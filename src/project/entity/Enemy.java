package project.entity;

import project.map.Map;
import project.move.MoveStrat;

/**
 * An enemy container
 */
public class Enemy extends Entity implements Updatable {
	private MoveStrat type;

	/**
     * @param xPos The enemy's x position
     * @param yPos The enemy's y position
     * @param map The map that the enemy is a part of.
     */
	public Enemy(int xPos, int yPos, Map map, MoveStrat type) {
		super(xPos, yPos, map);
		this.type = type;
		setGraphic(type.getGraphic());
	}

	@Override
	public boolean onMove(Updatable entity) {
	    // only player can move on the enemy
        // if player can destroy
		if (entity instanceof Player){
            return ((Player) entity).battle();
        }
        // cannot stack
		return false;
	}

	@Override
	public String toString() {
		String typeName = type.getClass().getName();
		return typeName.replace("Update", "");
	}

	/**
	 * @return The Updatable defining the enemy's type.
	 */
	public MoveStrat getType() {
		return type;
	}

	@Override
	public boolean update() {
		Direction moveDir = type.move(this, map);

		// Convert move direction to a resulting coordinate
        int moveX, moveY;
        moveX = getxPos();
        moveY = getyPos();
        switch (moveDir) {
            case LEFT:  moveX--; break;
            case RIGHT: moveX++; break;
            case UP:    moveY++; break;
            case DOWN:  moveY--; break;
            default: break;
        }

        // find what's on the next position
        for (Entity e : map.entitiesAtPos(moveX, moveY)) {
            if (e instanceof Player && !((Player) e).battle()) {
                if (((Player) e).playerHas("Armor") > 0){
                    ((Player) e).storeItemNum("Armor", -1);
                } else {
                    map.removeEntity(e);
                }
                return true;
            }

            if (!e.onMove(this)) return true;           // If can't move to the desired location, do nothing.
        }
        // Otherwise, move successful. Update position to the new position.
        setxPos(moveX);
        setyPos(moveY);

        return true;
	}
}
