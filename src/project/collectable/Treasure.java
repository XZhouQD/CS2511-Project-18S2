package project.collectable;

import project.entity.Player;
import project.entity.Updatable;

/**
 * Treasure. Does nothing. Collection of all treasure can be set as a requirement for victory.
 */
public class Treasure implements Collectable{

	@Override
    public boolean collect(Updatable entity) {
		if (!(entity instanceof Player)) return false;

		Player p = (Player) entity;
		p.storeItemNum("Treasure", 1);
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Treasure";
	}

	@Override
    public String getGraphic() {
        return "project/graphics/treasure.png";
	}
	
}
