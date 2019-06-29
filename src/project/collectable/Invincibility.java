package project.collectable;

import project.entity.Player;
import project.entity.Updatable;

/**
 * An invincibility potion that makes the player invincible for some duration.
 */
public class Invincibility implements Collectable {
    public static final int duration = 30; // in seconds

    @Override
    public boolean collect(Updatable entity) {
        if (!(entity instanceof Player)) return false;

        Player p = (Player) entity;
        p.storeItemNum("Invincibility", duration);
        
        return true;
    }
    
    @Override
    public String toString() {
        return "Invincibility Potion with duration " + duration;
    }

    @Override
    public String getGraphic() {
        return "project/graphics/potion_invincibility.png";
    }

}
