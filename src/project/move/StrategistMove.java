package project.move;

import project.entity.Direction;
import project.entity.Enemy;
import project.entity.Player;
import project.map.Map;

/**
 * Handles the movement of the strategist enemy.
 */
public class StrategistMove implements MoveStrat {
    int[] xPlayBack;    // Stores the player's most recent positions.
    int[] yPlayBack;

    public StrategistMove() {
        xPlayBack = new int[3];
        yPlayBack = new int[3];
    }
    
    @Override
    public Direction move(Enemy enemy, Map map) {
        Player player = findPlayer(map);
        if (player == null) return Direction.UNKNOWN;     // Do nothing if the player cannot be found.

        // Update most recent player positions
        for(int i = 0; i < 2; i++) {
        	this.xPlayBack[i] = this.xPlayBack[i+1];
        	this.yPlayBack[i] = this.yPlayBack[i+1];
        }
        this.xPlayBack[2] = player.getxPos();
        this.yPlayBack[2] = player.getyPos();
        
        int dx, dy;
        dx = player.getxPos() - enemy.getxPos();
        dy = player.getyPos() - enemy.getyPos();
        if(xPlayBack[0] != 0) { //prediction
        	dx = player.getxPos() + (xPlayBack[2]-xPlayBack[0]) - enemy.getxPos();
        	dy = player.getyPos() + (yPlayBack[2]-yPlayBack[0]) - enemy.getyPos();
        }
        return  Direction.getDir(dx, dy);
    }

    @Override
    public String getGraphic() {
        return "project/graphics/strategist.png";
    }

}
