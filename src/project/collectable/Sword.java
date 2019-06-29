package project.collectable;

import project.entity.Player;
import project.entity.Updatable;

/**
 * A sword with limited use that allows the player to eliminate enemies
 */
public class Sword implements Collectable {
    private final int durability = 5;

    @Override
    public boolean collect(Updatable entity) {
        if (!(entity instanceof Player)) return false;

        // check if already has a sword
        Player p = (Player) entity;
        if (p.playerHas("Sword") < 1 ) {
            p.storeItemNum("Sword", durability);
            return true;
        }
        System.out.println("Cannot pick this up");
        return false; // cannot collect items
    }

    @Override
    public String toString() {
        return "Sword with durability " + durability;
    }

    @Override
    public String getGraphic() {
        return "project/graphics/sword.png";
    }
    
}
