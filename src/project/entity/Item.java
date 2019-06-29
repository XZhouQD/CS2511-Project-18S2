package project.entity;

import project.collectable.Collectable;
import project.map.Map;
import project.entity.Updatable;

/**
 * An item that may be collected by the player.
 */
public class Item extends Entity {
    private Collectable type;

    /**
     * @param xPos The item's x position
     * @param yPos The item's y position
     * @param map The map that the item is a part of.
     */
    public Item(int xPos, int yPos, Map map, Collectable type) {
        super(xPos, yPos, map);
        this.type = type;
        this.setGraphic(type.getGraphic());
    }

    @Override
    public boolean onMove(Updatable entity) {
        // entity is player and can collect
        if ((entity instanceof Player) && type.collect(entity)) {
            map.removeEntity(this);
            return true;
        }
        return false;
    }

    /**
     * @return The collectable representing the item's type
     */
    public Collectable getType() {
        return type;
    }

	@Override
    public String toString() {
        return type.toString();
    }
}
