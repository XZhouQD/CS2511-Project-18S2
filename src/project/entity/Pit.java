package project.entity;

import project.map.Map;
import project.entity.Updatable;

public class Pit extends Entity{
    /**
     * @param xPos The pit's x position
     * @param yPos The pit's y position
     * @param map The map that the pit is a part of.
     */
    public Pit(int xPos, int yPos, Map map) {
        super(xPos, yPos, map);
        this.setGraphic("project/graphics/pit.png");
    }

    @Override
    public boolean onMove(Updatable update) {
        if (update instanceof Player) {
            if (((Player) update).playerHas("Hover") > 0) {
                return true;
            }
        }
        map.removeEntity((Entity) update);
        return true;    // There are no restrictions on what can move onto a pit.
    }

	@Override
    public String toString() {
        return "Pit";
    }

}
