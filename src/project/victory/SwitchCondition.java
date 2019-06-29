package project.victory;

import java.util.List;
import project.entity.Entity;
import project.entity.FloorSwitch;
import project.entity.Boulder;

/**
 * A condition for victory in which all floor switches in the map must have a boulder on them.
 */
public class SwitchCondition implements Condition {

    @Override
    public boolean isSatisfied(List<Entity> entities) {
        for (Entity e : entities) {
            if (e instanceof FloorSwitch && !boulderAtPos(entities, e.getxPos(), e.getyPos())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if there is a boulder at a given position.
     * @param entities The list of entities in which to search for a boulder.
     * @param xPos The desired x position for the boulder to be at.
     * @param yPos The desired y position for the boulder to be at.
     * @return true if there is a boulder at the given location and false otherwise.
     */
    private boolean boulderAtPos(List<Entity> entities, int xPos, int yPos) {
        for (Entity e : entities) {
            if (e instanceof Boulder) {
                Boulder b = (Boulder) e;
                if (xPos == b.getxPos() && yPos == b.getyPos()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Turn on switches";
    }
}
