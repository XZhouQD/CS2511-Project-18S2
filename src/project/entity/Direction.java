package project.entity;

/**
 * Defines the four cardinal directions, in addition to functions for determining direction.
 */
public enum Direction {
	LEFT,
	RIGHT,
	UP,
	DOWN,
	UNKNOWN;

	/**
	 * Finds the most significant cardinal direction from the direction coordinate (dx,dy).
	 * @param dx The x component of the coordinate.
	 * @param dy The y component of the coordinate.
	 * @return The aforementioned Direction, or Direction.UNKNOWN if dx == dy == 0.
	 */
	public static Direction getDir(int dx, int dy) {
		if (dx == 0 && dy == 0) return Direction.UNKNOWN;

		if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                return Direction.RIGHT;
            } else {
                return Direction.LEFT;
            }
        } else {
            if (dy > 0) {
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        }
	}

	/**
	 * Finds the most significant cardinal direction from position (fromX,fromY) to position (toX,toY).
	 * @param fromX The origin x coordinate.
	 * @param fromY The origin y coordinate.
	 * @param toX The destination x coordinate.
	 * @param toY The destination y coordinate.
	 * @return The aforementioned Direction, or Direction.UNKNOWN if the positions are equal.
	 */
	public static Direction getDir(int fromX, int fromY, int toX, int toY) {
		int dx = toX - fromX;
        int dy = toY - fromY;
		
		if (dx == 0 && dy == 0) return Direction.UNKNOWN;

		if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                return Direction.RIGHT;
            } else {
                return Direction.LEFT;
            }
        } else {
            if (dy > 0) {
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        }
	}
}
