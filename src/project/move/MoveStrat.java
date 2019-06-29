package project.move;

import project.entity.Direction;
import project.entity.Enemy;
import project.entity.Entity;
import project.entity.Player;
import project.map.Map;

/**
 * Strategy pattern. Define which strategy each Enemy entity should use when
 * making a move.
 */
public interface MoveStrat {
	/**
     * Decides on a direction for the containing enemy to move in.
     * @return The Direction in which the entity should move.
     */
    public Direction move(Enemy enemy, Map map);
    
    /**
     * @return The URL of the graphic that represents the MoveStrat.
     */
    public String getGraphic();

    default Player findPlayer(Map map) {
        for (Entity e : map.getEntities()) {
            if (e instanceof Player) {
                return (Player) e;
            }
        }
        return null;
    }
}
