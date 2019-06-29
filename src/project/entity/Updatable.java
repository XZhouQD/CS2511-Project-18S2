package project.entity;

/**
 * Observer pattern. Notifying moving entities to make a move.
 */
public interface Updatable {
    /**
     * Determines and executes whatever the corresponding entity wants to do.
     * @return true if the updatable is to continue existing, and false if it is to be removed from play.
     */
    public boolean update();
}
