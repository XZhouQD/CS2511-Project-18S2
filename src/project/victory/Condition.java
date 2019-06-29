package project.victory;

import java.util.List;
import project.entity.Entity;

/**
 * Interface for victory conditions.
 */
public interface Condition {
	/**
	 * Checks if the victory condition is satisfied.
	 * @param entities A list of all entities in the dungeon.
	 * @return true if the condition is satisfied and false otherwise.
	 */
	public boolean isSatisfied(List<Entity> entities);
}
