package project.victory;

import java.util.List;
import project.entity.Entity;
import project.entity.Enemy;

/**
 * A condition for victory in which all enemies must be destroyed to be satisfied.
 * (i.e. There are no enemies on the map)
 */
public class EnemyCondition implements Condition {
	
	@Override
	public boolean isSatisfied(List<Entity> entities) {
		for (Entity e : entities) {
			if (e instanceof Enemy) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Eliminate enemies";
	}
}
