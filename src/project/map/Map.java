package project.map;
import project.entity.Entity;
import project.entity.Player;
import project.entity.Updatable;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * A map of a dungeon.
 */
public class Map implements Cloneable{
    private ArrayList<Entity> entities;
    private ArrayList<Updatable> ordersList;
    private int xBorder;
    private int yBorder;

    /**
     * @param xBorder The width of the map.
     * @param yBorder The height of the map.
     */
    public Map(int xBorder, int yBorder) {
		if (xBorder < 1) xBorder = 1;
		if (yBorder < 1) yBorder = 1;
		this.xBorder = xBorder;
		this.yBorder = yBorder;
        this.entities = new ArrayList<Entity>();
        this.ordersList = new ArrayList<Updatable>();
	}

    /**
     * Adds the given entity to the map.
     * @param e The entity to be added to the map.
     */
    public void addEntity(Entity e) {
    	entities.add(e);
        if (e instanceof Updatable) {
            ordersList.add((Updatable) e);
        }
    }

    /**
     * Removes the given entity from the map (if possible).
     * @param e The entity to be removed from the map.
     */
    public void removeEntity(Entity e){
        entities.remove(e);
        if (e instanceof Updatable) {
            ordersList.remove((Updatable) e);
        }
    }

    /**
     * Retrieves a list of entities at the specified position.
     * @param xPos The position's x coordinate.
     * @param yPos The position's y coordinate.
     * @return A list of entities at the given position.
     */
    public List<Entity> entitiesAtPos(int xPos, int yPos) {
        ArrayList<Entity> ret = new ArrayList<Entity>();
        for (Entity e : entities) {
            if (e.getxPos() == xPos && e.getyPos() == yPos) {
                ret.add(e);
            }
        }
        return ret;
    }

    public void updateMap() {
        for (int i = 0; i < ordersList.size(); i++) {
            Updatable currUpdatable = ordersList.get(i);
            if (!currUpdatable.update()) removeEntity((Entity) currUpdatable);
        }
    }

    /**
     * @return The map's width;
     */
	public int getxBorder() {
		return xBorder;
	}

    /**
     * @return The map's height.
     */
	public int getyBorder() {
		return yBorder;
	}

    /**
     * @return A list of all entities in the map.
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * @return current player Inventory
     */
    public HashMap<String,Integer> getInventory() {
        // find player first
        for (Entity e: entities) {
            if (e instanceof Player) {
                return ((Player) e).getInventory();
            }
        }
        return null;
    }

    /**
     * Checks if the map contains an instance of the given entity's class.
     * @param entity The entity who's class is to be searched for.
     * @return true if there is an instance of the entity's class in the map, and false otherwise.
     */
    public boolean onMap(Entity entity) {
        for (Entity e : entities) {
            if (entity.getClass() == e.getClass()) {
                return true;
            }
        }
        return false;
    }
}
