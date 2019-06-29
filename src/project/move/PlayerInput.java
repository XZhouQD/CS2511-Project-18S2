package project.move;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Maintains a list of actions for the player to execute.
 */
public class PlayerInput {
	private Queue<Action> actions;

	public PlayerInput() {
		actions = new ArrayDeque<Action>();
	}

	/**
	 * Retrieve the next action to be executed by the player.
	 * @return The next action for the Player to execute.
	 */
	public Action getNext() {
		return actions.poll();
	}

	/**
	 * Temporary: Simulates the relevant key being pressed and translated into an action for processing.
	 * Used for test testing.
	 * @param action The desired action to be performed.
	 */
	public void addAction(Action action) {
		actions.add(action);
	}
	
}
