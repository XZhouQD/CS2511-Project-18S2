package project.entity;

import project.map.Map;
import project.entity.Updatable;

/**
 * A door that obstructs movement unless unlocked by a player with the corresponding key.
 */
public class Door extends Entity {
    private boolean open;
    private Color color;

    /**
     * @param xPos The door's x position
     * @param yPos The door's y position
     * @param map The map that the door is a part of.
     * @param color The color of the key that is used to unlock this door.
     */
    public Door(int xPos, int yPos, Map map, Color color) {
        super(xPos, yPos, map);
        this.open = false;
        if (color == Color.RED) {
            this.setGraphic("project/graphics/red_door.png");
        } else if (color == Color.BLUE) {
            this.setGraphic("project/graphics/blue_door.png");
        } else if (color == Color.GREEN){
            this.setGraphic("project/graphics/green_door.png");
        } else {
            this.setGraphic("project/graphics/door_closed.png");
        }

        this.color = color;
    }

    @Override
    public boolean onMove(Updatable entity) {
        if (this.open) return true;     // once open always open

        if (entity instanceof Player){
            Player p = (Player) entity;

            if (p.getKey() == color) {
                p.setKey(Color.NONE);
                this.open = true;
                this.setGraphic("project/graphics/door_open.png");
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return color.name()+" Door";
    }

}
