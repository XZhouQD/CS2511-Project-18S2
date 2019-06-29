package project.collectable;

import project.entity.Updatable;

/**
 * Used to define what happens when an entity moves onto an item that can be collected.
 */
public interface Collectable {
    
    /**
     * The action to be taken by the collectable when an entity moves onto it.
     * @param entity The Updatable of the entity that walked onto the collectable.
     * @return true if the item is to be destroyed and false otherwise.
     * Note: If only a certain entity is allowed to collect the item, then the entity's type must be checked.
     */
    public boolean collect(Updatable entity);

    /**
     * Retrieves the name of the graphic to represent this collectable.
     * @return The path/filename of the graphic representing the collectable.
     */
    public String getGraphic();
    
}
