package project.move;

import project.entity.Direction;
import project.entity.Enemy;
import project.entity.Player;
import project.map.Map;

/**
 * Represents the 'Coward' enemy. Attempts to maintain a set distance from the player.
 */
public class CowardMove implements MoveStrat {

    @Override
    public Direction move(Enemy enemy, Map map) {
        Player player = findPlayer(map);
        if (player == null) return Direction.UNKNOWN;     // Do nothing if the player cannot be found.

        Direction dir = Direction.getDir(enemy.getxPos(), enemy.getyPos(), player.getxPos(), player.getyPos());

        // Reverse movement direction if too close to the player
        int PX = player.getxPos();
        int PY = player.getyPos();
        int EX = enemy.getxPos();
        int EY = enemy.getyPos();
        
        if (Math.abs(PX - EX) < map.getxBorder()/5) {
            if (dir == Direction.UP)
                dir = Direction.DOWN;
            else if (dir == Direction.DOWN)
                dir = Direction.UP;
        }
        if (Math.abs(PY - EY) < map.getyBorder()/5) {
            if (dir == Direction.LEFT)
                dir = Direction.RIGHT;
            else if (dir == Direction.RIGHT)
                dir = Direction.LEFT;
        }
        
        return dir;
    }

    @Override
    public String getGraphic() {
        return "project/graphics/coward.png";
    }

}
