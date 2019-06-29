package project.victory;

import java.util.List;
import java.util.Iterator;
import project.entity.Entity;

/**
 * Maintains and determines the conditions for victory have been satisfied.
 */
public class Victory {
	private List<Condition> conditions;

	/**
	 * @param conditions A list of all the conditions required for victory.
	 * Note: If ExitCondition is among the required conditions, then all other conditions will be removed.
	 */
	public Victory(List<Condition> conditions) {
		// Check if there is an exit condition.
		boolean hasExitCondition = false;
		for (Condition c : conditions) {
			if (c instanceof ExitCondition) {
				hasExitCondition = true;
				break;
			}
		}

		// If there is, then remove all other conditions.
		if (hasExitCondition) {
			Iterator<Condition> it = conditions.iterator();
			while (it.hasNext()) {
				Condition c = it.next();
				if (!(c instanceof ExitCondition)) {
					it.remove();
				}
			}
		}
		this.conditions = conditions;
	}

	/**
	 * Determines whether the conditions for victory have been satisfied.
	 * @param entities A list of all entities in the dungeon.
	 * @return true if all conditions are currently satisfied and false otherwise.
	 */
	public boolean victorySatisfied(List<Entity> entities) {
		for (Condition c : conditions) {
			if (!c.isSatisfied(entities)) {
				return false;
			}
		}
		return true;
	}
	
}
