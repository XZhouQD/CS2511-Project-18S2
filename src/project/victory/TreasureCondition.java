package project.victory;

import java.util.List;
import project.entity.Entity;
import project.entity.Item;
import project.collectable.Treasure;

/**
 * A condition for victory in which all treasure must be collected to be satisfied.
 * (i.e. There is no treasure on the map)
 */
public class TreasureCondition implements Condition {

	@Override
	public boolean isSatisfied(List<Entity> entities) {
		for (Entity e : entities) {
			if (e instanceof Item) {
				Item i = (Item) e;
				if (i.getType() instanceof Treasure) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Collect treasure";
	}
}
