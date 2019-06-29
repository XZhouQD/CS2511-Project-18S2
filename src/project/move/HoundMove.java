package project.move;

import project.entity.Direction;
import project.entity.Enemy;
import project.entity.Entity;
import project.entity.Player;
import project.map.Map;

/**
 * Represents the 'hound' enemy. Attempts to position itself such that the player is between the hunter and the hound.
 */
public class HoundMove implements MoveStrat {

    @Override
    public Direction move(Enemy enemy, Map map) {
        Player player = findPlayer(map);
        if (player == null) return Direction.UNKNOWN;     // Do nothing if the player cannot be found.

        Direction dir = Direction.getDir(enemy.getxPos(), enemy.getyPos(), player.getxPos(), player.getyPos());
        
        // Find the position of player
        int PX, PY, dx, dy;
        PX = player.getxPos();
        PY = player.getyPos();
        
        dx = PX - enemy.getxPos();
        dy = PY - enemy.getyPos();

        boolean inline = (dx == 0 || dy == 0);
        boolean nextToPlayer = Math.abs(dx*dx) + Math.abs(dy*dy) == 1;
        if (inline && nextToPlayer) { // then move to the player
        	return dir;
        } else {
        	Enemy hunter = null;
        	for (Entity e : map.getEntities()) {
        		if (e instanceof Enemy ) {
        			if(((Enemy) e).getType() instanceof HunterMove)
        				hunter = (Enemy) e;
        		}
        	}
        	if (hunter == null) return Direction.UNKNOWN; // Do nothing if there is no hunter 

        	int HX, HY, targetx, targety;
        	HX = hunter.getxPos();
        	HY = hunter.getyPos();
        
        	targetx = 2*PX-HX;  // Get pos. opposite player from hunter
        	targety = 2*PY-HY;
            
            return Direction.getDir(targetx, targety, enemy.getxPos(), enemy.getyPos());
        }
    }

    @Override
    public String getGraphic() {
        return "project/graphics/hound.png";
    }

}
