package project.unittest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import project.entity.*;
import project.move.HoundMove;
import project.victory.*;
import project.collectable.Treasure;


public class VictoryTest {
	Victory victory;
	List<Condition> conditions;
	Condition enemyVic, switchVic, treasureVic, exitVic;
	List<Entity> entities;

	@Before
	public void setup() {
		conditions = new ArrayList<Condition>();
		enemyVic = new EnemyCondition();
		switchVic = new SwitchCondition();
		treasureVic = new TreasureCondition();
		exitVic = new ExitCondition();

		entities = new ArrayList<Entity>();
		entities.add(new FloorSwitch(3, 7, null));
		entities.add(new FloorSwitch(5, 12, null));
		entities.add(new Exit(5, 5, null));
	}

	@Test
	public void testExitVicSatisfied() {
		entities.add(new Player(5, 5, null));
		assertTrue(exitVic.isSatisfied(entities));
	}
	@Test
	public void testExitVicNotSatisfied() {
		entities.add(new Player(4, 5, null));
		assertFalse(exitVic.isSatisfied(entities));
	}

	@Test
	public void testEnemyVicSatisfied() {
		assertTrue(enemyVic.isSatisfied(entities));
	}
	@Test
	public void testEnemyVicNotSatisfied() {
		entities.add(new Enemy(4, 5, null, new HoundMove()));
		assertFalse(enemyVic.isSatisfied(entities));
	}

	@Test
	public void testSwitchVicSatisfied() {
		entities.add(new Boulder(5, 12, null));
		entities.add(new Boulder(3, 7, null));
		assertTrue(switchVic.isSatisfied(entities));
	}
	@Test
	public void testSwitchVicNotSatisfied() {
		assertFalse(switchVic.isSatisfied(entities));
		entities.add(new Boulder(3, 7, null));
		assertFalse(switchVic.isSatisfied(entities));		// Only one of the two switches are activated.
	}

	@Test
	public void testTreasureVicSatisfied() {
		assertTrue(treasureVic.isSatisfied(entities));
	}
	@Test
	public void testTreasureVicNotSatisfied() {
		entities.add(new Item(4, 6, null, new Treasure()));
		entities.add(new Item(9, 8, null, new Treasure()));
		assertFalse(treasureVic.isSatisfied(entities));
	}

	@Test
	public void testVictorySingleSatisfied() {
		conditions.add(treasureVic);

		victory = new Victory(conditions);
		assertTrue(victory.victorySatisfied(entities));
	}
	@Test
	public void testVictorySingleNotSatisfied() {
		conditions.add(switchVic);
		entities.add(new Boulder(3, 7, null));

		victory = new Victory(conditions);
		assertFalse(victory.victorySatisfied(entities));
	}
	@Test
	public void testVictoryMultipleSatisfied() {
		conditions.add(treasureVic);
		conditions.add(enemyVic);
		
		victory = new Victory(conditions);
		assertTrue(victory.victorySatisfied(entities));
	}
	@Test
	public void testVictoryMultipleNotSatisfied() {
		conditions.add(treasureVic);
		conditions.add(enemyVic);
		conditions.add(switchVic);

		victory = new Victory(conditions);
		assertFalse(victory.victorySatisfied(entities));
	}
	@Test
	public void testVictoryMultipleExit() {
		conditions.add(treasureVic);
		conditions.add(exitVic);
		conditions.add(switchVic);
		entities.add(new Player(5, 5, null));
	}
}
