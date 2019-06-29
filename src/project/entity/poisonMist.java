package project.entity;

import project.map.Map;

public class poisonMist extends Entity{
    /**
     * @param xPos The pit's x position
     * @param yPos The pit's y position
     * @param map The map that the pit is a part of.
     */
    public poisonMist(int xPos, int yPos, Map map) {
        super(xPos, yPos, map);
        this.setGraphic("project/graphics/poison_mist.png");
    }

    @Override
    public boolean onMove(Updatable update) {
        if (update instanceof Player) {
            if (((Player) update).playerHas("Hover") > 0) {
                ((Player) update).storeItemNum("Hover",-1);
            }
            if (((Player) update).playerHas("Invincibility") > 0) {
                ((Player) update).storeItemNum("Invincibility",0);
            }
        }
        return true;    // There are no restrictions on what can move onto a pit.
    }

    @Override
    public String toString() {
        return "poisonMist";
    }

}
