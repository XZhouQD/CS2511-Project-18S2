package project.entity;

import project.entity.Updatable;
import project.map.Map;

/**
 * A dungeon exit. Causes the player to be victorious upon moving onto it. Only the Player may move onto it.
 */
public class Exit extends Entity {
	
	/**
     * @param xPos The exit's x position
     * @param yPos The exit's y position
     * @param map The map that the exit is a part of.
     */
	public Exit(int xPos, int yPos, Map map) {
		super(xPos, yPos, map);
		this.setGraphic("project/graphics/exit.png");
	}
	
	@Override
	public boolean onMove(Updatable entity) {
		if (entity instanceof Player) {
			return true;
		} else {
			return false;
		}
	}

    @Override
    public String toString() {
        return "Exit";
    }
}
