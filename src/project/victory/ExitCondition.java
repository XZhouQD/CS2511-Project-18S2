package project.victory;

import java.util.List;
import project.entity.Entity;
import project.entity.Exit;
import project.entity.Player;

/**
 * A condition for victory in which the player must reach a dungeon exit.
 * Note: If the dungeon has multiple exits, then the player only needs to reach one such exit.
 */
public class ExitCondition implements Condition {
	
	@Override
	public boolean isSatisfied(List<Entity> entities) {
		for (Entity e : entities) {
			if (e instanceof Exit && playerAtPos(entities, e.getxPos(), e.getyPos())) {
				System.out.println("exit!!!!!!!!!!");
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if there is a player at a given position.
	 * @param entities The list of entities in which to search for a player.
	 * @param xPos The desired x position for the player to be at.
	 * @param yPos The desired y position for the player to be at.
	 * @return true if there is a player at the given location and false otherwise.
	 */
	private boolean playerAtPos(List<Entity> entities, int xPos, int yPos) {
		for (Entity e : entities) {
			if (e instanceof Player) {
				Player b = (Player) e;
				if (xPos == b.getxPos() && yPos == b.getyPos()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Escape";
	}
}
