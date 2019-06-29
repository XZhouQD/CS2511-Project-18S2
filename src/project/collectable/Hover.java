package project.collectable;

import project.entity.Player;
import project.entity.Updatable;

/**
 * A hover potion that allows the player to traverse Pits.
 */
public class Hover implements Collectable {

    @Override
	public boolean collect(Updatable entity) {
		if (!(entity instanceof Player)) return false;

		Player p = (Player) entity;
		p.storeItemNum("Hover", 1);
		
		return true;
	}

	@Override
	public String toString() {
		return "Hover Potion";
	}

	@Override
    public String getGraphic() {
        return "project/graphics/potion_hover.png";
	}
	
}
