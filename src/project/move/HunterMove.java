package project.move;

import project.entity.Direction;
import project.entity.Enemy;
import project.entity.Player;
import project.map.Map;

/**
 * Represents the 'hunter' enemy. Attempts to move towards the player when possible.
 */
public class HunterMove implements MoveStrat {

    @Override
    public Direction move(Enemy enemy, Map map) {
        Player player = findPlayer(map);
        if (player == null) return Direction.UNKNOWN;     // Do nothing if the player cannot be found.

        return Direction.getDir(enemy.getxPos(), enemy.getyPos(), player.getxPos(), player.getyPos());
    }

    @Override
    public String getGraphic() {
        return "project/graphics/hunter.png";
    }

}
