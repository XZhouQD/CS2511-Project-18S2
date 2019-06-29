package project.collectable;

import project.entity.Player;
import project.entity.Updatable;

/**
 * A stationary arrow that can be collected by the player.
 */
public class Armor implements Collectable {

    @Override
    public boolean collect(Updatable entity) {
        if (!(entity instanceof Player)) return false;

        Player p = (Player) entity;
        p.storeItemNum("Armor", 3);
        p.setGraph("project/graphics/armoured_player.png");
        return true;
    }

    @Override
    public String toString() {
        return "Armor";
    }

    @Override
    public String getGraphic() {
        return "project/graphics/armour.png";
    }
}
