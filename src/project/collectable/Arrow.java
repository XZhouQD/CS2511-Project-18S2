package project.collectable;

import project.entity.Player;
import project.entity.Updatable;

/**
 * A stationary arrow that can be collected by the player.
 */
public class Arrow implements Collectable {

    @Override
    public boolean collect(Updatable entity) {
        if (!(entity instanceof Player)) return false;

        Player p = (Player) entity;
        p.storeItemNum("Arrow", 1);
        
        return true;
    }
    
    @Override
    public String toString() {
        return "Arrow";
    }

    @Override
    public String getGraphic() {
        return "project/graphics/arrow.png";
    }

}
