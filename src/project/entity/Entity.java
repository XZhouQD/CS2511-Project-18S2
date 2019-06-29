package project.entity;

import project.map.Map;
import project.entity.Updatable;

/**
 * Objects that are drawn onto the screen.
 */
public abstract class Entity {
    protected Map map;
    private int xPos;
    private int yPos;
    private String graphic;

    /**
     * @param xPos The entity's x position.
     * @param yPos The entity's y position.
     * @param map The map that the entity is a part of.
     */
    public Entity(int xPos, int yPos, Map map) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.map = map;
        this.graphic = null;
    }

    /**
     * This method is called whenever an updateable entity tries to move onto the same location as this entity.
     * @param entity The updateable entity that moved onto this entity.
     * @return true if the updateable is allowed to move onto this location, and false otherwise.
     */
    public abstract boolean onMove(Updatable entity);

    /**
     * Sets the graphic representing this entity to the given graphic.
     * @param graphic The path/filename to the graphic.
     */
    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    /**
     * @return The path/filename to the graphic representing this entity.
     */
    public String getGraphic() {
        return graphic;
    }

    /**
     * @return This entity's current x position
     */
    public int getxPos() {
		return xPos;
    }
    
    /**
     * @return This entity's current y position
     */
	public int getyPos() {
		return yPos;
    }

    /**
     * Sets this entity's current x position to the position given.
     * @param xPos The entity's new x position.
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Sets this entity's current y position to the position given.
     * @param xPos The entity's new y position.
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

}
