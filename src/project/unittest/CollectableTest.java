package project.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import project.collectable.*;
import project.entity.*;
import project.map.Map;

public class CollectableTest {

	Player p1;
	Arrow arrow;
	Sword sword;
	Invincibility inv;
	Hover hov;
	Map map;
	
	@Before
	public void setup() {
		map = new Map(15, 15);
		p1 = new Player(3, 10, map);
		arrow = new Arrow();
		sword = new Sword();
		inv = new Invincibility();
		hov = new Hover();
	}
	
	@Test
	public void testSingleArrowCollection() {
		Item arrow1 = new Item(5, 7, map, arrow);
		arrow1.onMove(p1);
		assertEquals(p1.playerHas("Arrow"), 1);
	}
	
	@Test
	public void testMultipleArrowCollection() {
		Item arrow1 = new Item(5, 7, map, arrow);
		Item arrow2 = new Item(3, 6, map, arrow);

		arrow1.onMove(p1);
		assertEquals(p1.playerHas("Arrow"), 1);

		arrow2.onMove(p1);
		assertEquals(p1.playerHas("Arrow"), 2);
	}
	
	//The above two method matches with all other normal items
	
	//The tests below are for special types
	@Test
	public void testSwordCollection() {
		Item sword1 = new Item(2, 8, map, sword);
		sword1.onMove(p1);
		assertEquals(p1.playerHas("Sword"), 5);
	}
	
	@Test
	public void testSwordRestore() {
		Item sword1 = new Item(2, 8, map, sword);
		sword1.onMove(p1);
		assertEquals(p1.playerHas("Sword"), 5);
		p1.battle();
		assertEquals(p1.playerHas("Sword"), 4);

		Item sword2 = new Item(3, 7, map, sword);
		sword2.onMove(p1);
		assertEquals(p1.playerHas("Sword"), 4);	// Since player already has a sword
	}
	
	@Test
	public void testInvincibilityCollection() {
		Item inv1 = new Item(4, 9, map, inv);
		inv1.onMove(p1);
		assertEquals(p1.playerHas("Invincibility"), Invincibility.duration);
	}
	
	@Test
	public void testHoverCollection() {
		Item hov1 = new Item(4, 9, map, hov);
		hov1.onMove(p1);
		assertNotEquals(p1.playerHas("Hover"), 0);
	}
}
