package project.entity;

import project.map.Map;
import project.entity.Updatable;

/**
 * A wall. Obstructs all movement.
 */
public class Wall extends Entity{
    /**
     * @param xPos The wall's x position
     * @param yPos The wall's y position
     * @param map The map that the wall is a part of.
     */
    public Wall(int xPos, int yPos, Map map) {
        super(xPos, yPos, map);
        this.setGraphic("project/graphics/wall.png");
    }

    @Override
    public boolean onMove(Updatable entity) {
        return false;
    }

	@Override
    public String toString() {
        return "Wall";
    }

}
