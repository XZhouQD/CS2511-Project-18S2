package project.entity;

import project.map.Map;

public class HorizontalMovingWall extends Wall implements Updatable {

	Direction currDir;
	
	public HorizontalMovingWall(int xPos, int yPos, Map map) {
		super(xPos, yPos, map);
		this.currDir = Direction.LEFT;
		this.setGraphic("project/graphics/horizontalMovingWall.png");
	}

	@Override
	public boolean update() {
		int nextX = getxPos();
        int nextY = getyPos();
        // check the next position if
        switch (currDir) {
            case LEFT:  nextX--; break;
            case RIGHT: nextX++; break;
            case UP:    nextY--; break;
            case DOWN:  nextY++; break;
            default: return true;           // Unknown direction. Do nothing.
        }
        // find what's on the next position
        if(map.entitiesAtPos(nextX, nextY).size() > 0)
        	swapDirection();
        else {
        	setxPos(nextX);
        	setyPos(nextY);
        }
        return true;
	}
	
	@Override
    public boolean onMove(Updatable entity) {
		return false;
		
    }
	
	@Override
	public String toString() {
		return "Horizontal Moving Wall";
	}
	
	private void swapDirection() {
		if(currDir == Direction.LEFT)
			currDir = Direction.RIGHT;
		else
			currDir = Direction.LEFT;
	}

}
