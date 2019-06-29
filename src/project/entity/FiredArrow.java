package project.entity;

import project.map.Map;

/**
 * Represents an arrow that has been fired. Constantly moves in the direction in which it was fired.
 */
public class FiredArrow extends Entity implements Updatable {
    Direction moveDir;

    /**
     * @param moveDir The direction that the arrow was fired.
     */
    public FiredArrow(int x, int y, Map map, Direction moveDir) {
		super(x, y, map);
		this.moveDir = moveDir;
		
        setGraphic("project/graphics/arrow.png");
        switch(moveDir) {
            case LEFT:  setGraphic("project/graphics/arrowLeft.png"); break;
            case RIGHT: setGraphic("project/graphics/arrowRight.png"); break;
            case UP:    setGraphic("project/graphics/arrowUp.png"); break;
            case DOWN:  setGraphic("project/graphics/arrowDown.png"); break;
		default:
			break;
        }
    }

    @Override
    public boolean update() {
        int nextX = getxPos();
        int nextY = getyPos();
        // check the next position if
        switch (moveDir) {
            case LEFT:  nextX--; break;
            case RIGHT: nextX++; break;
            case UP:    nextY--; break;
            case DOWN:  nextY++; break;
            default: return true;           // Unknown direction. Do nothing.
        }

        // find what's on the next position
        for (Entity e : map.entitiesAtPos(nextX, nextY)) {
            // except empty switch
            if (e instanceof FloorSwitch) continue;
            if (!e.onMove(this)) {
                if (e instanceof Enemy) {
                    // destroy this enemy and remove
                    map.removeEntity(e);
                }
                // arrow break, removed from map and from orderList
                map.removeEntity(this); return false;
            }
        }

        setxPos(nextX);
        setyPos(nextY);
        return true;
    }

	@Override
	public boolean onMove(Updatable entity) {
		return false;
	}

    
}
