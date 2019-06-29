package project.collectable;

import project.entity.Player;
import project.entity.Updatable;

/**
 * An unlit bomb. Can be picked up and later placed and lit to create an explosion at the desired location.
 */
public class UnlitBomb implements Collectable {
	
	@Override
	public boolean collect(Updatable entity) {
		if (!(entity instanceof Player)) return false;

		Player p = (Player) entity;
		p.storeItemNum("Bomb", 1);
		
		return true;
	}

	@Override
	public String toString() {
		return "Unlit Bomb";
	}

	@Override
    public String getGraphic() {
        return "project/graphics/bomb_unlit.png";
    }
}
