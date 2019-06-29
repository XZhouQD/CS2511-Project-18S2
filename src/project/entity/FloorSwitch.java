package project.entity;

import project.map.Map;
import project.entity.Updatable;

/**
 * A floor switch. Does nothing. Acts as a barrier to victory when the relevant "Switch" victory condition is set.
 */
public class FloorSwitch extends Entity{
    /**
     * @param xPos The switch's x position
     * @param yPos The switch's y position
     * @param map The map that the switch is a part of.
     */
    public FloorSwitch(int xPos, int yPos, Map map) {
        super(xPos, yPos, map);
        this.setGraphic("project/graphics/switch.png");
    }

    @Override
    public boolean onMove(Updatable entity) {
        return true;    // Do nothing. Switches are checked in the 'SwitchCondition' victory condition.
    }

    @Override
    public String toString() {
        return "Floor Switch";
    }
}
