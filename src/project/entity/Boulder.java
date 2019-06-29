package project.entity;

import java.util.List;

import project.map.Map;
import project.entity.Updatable;

/**
 * A movable entity that can be used to activate floor switches or cover up pits.
 */
public class Boulder extends Entity {

    /**
     * @param xPos The boulder's x position
     * @param yPos The boulder's y position
     * @param map The map that the boulder is a part of.
     */
    public Boulder(int xPos, int yPos, Map map) {
        super(xPos, yPos, map);
        this.setGraphic("project/graphics/boulder.png");
    }

    @Override
    public boolean onMove(Updatable entity) {
        if (!(entity instanceof Player)) return false;
        
        Player p = (Player) entity;
        int playerX, playerY;
        Direction pushDir;
        
        playerX = p.getxPos();
        playerY = p.getyPos();
        pushDir = Direction.getDir(playerX, playerY, getxPos(), getyPos());
        
        int x, y;
        x = getxPos();
        y = getyPos();
        switch(pushDir) {
            case UP:    y++; break;
            case DOWN:  y--; break;
            case LEFT:  x--; break;
            case RIGHT: x++; break;
            default:    break;
        }

        List<Entity> entitiesAtDest = map.entitiesAtPos(x, y);
        if (!boulderObstructed(entitiesAtDest)) {
            Pit hole = getPit(entitiesAtDest);
            if (hole != null) {
                map.removeEntity(this);
                map.removeEntity(hole);
            }

            setxPos(x);
            setyPos(y);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Boulder";
    }
    
    /**
     * Checks if any of the provided entities obstruct the boulder.
     * @param entities A list of entities containing possible obstructions.
     * @return true if the boulder is obstructed by an entity in entities, and false otherwise.
     */
    private boolean boulderObstructed(List<Entity> entities) {
        for (Entity e : entities) {
            if (e instanceof Exit) return true;
            if (e instanceof Door) return true;
            if (e instanceof Wall) return true;
        }
        return false;
    }

    /**
     * Searches for a Pit in a given list of entities.
     * @param entities A list of entities in which a Pit will be searched for.
     * @return null if no Pit could be found, or the first Pit in the list.
     */
    private Pit getPit(List<Entity> entities) {
        for (Entity e : entities) {
            if (e instanceof Pit) return (Pit) e;
        }
        return null;
    }
}
