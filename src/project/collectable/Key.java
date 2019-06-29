package project.collectable;

import project.entity.Color;
import project.entity.Player;
import project.entity.Updatable;

/**
 * A key that can be used by the player to unlock doors.
 */
public class Key implements Collectable {
    private Color color;

    /**
     * @param color The color of the key (to match with a door)
     */
    public Key(Color color) {
        this.color = color;
    }

    @Override
	public boolean collect(Updatable entity) {
        if (!(entity instanceof Player)) return false;

        Player p = (Player) entity;
        return p.setKey(color);
    }
    
    @Override
    public String toString() {
        return  color.name() + " Key";
    }

    // ===========================Getter and Setter==========================

    @Override
    public String getGraphic() {
        switch (color) {
            case RED:   return "project/graphics/red_key.png";
            case BLUE:  return "project/graphics/blue_key.png";
            case GREEN: return "project/graphics/green_key.png";
            default:    return "project/graphics/key.png";
        }
    }

}
