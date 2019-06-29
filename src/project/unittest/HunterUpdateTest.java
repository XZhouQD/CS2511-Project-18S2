package project.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import project.entity.*;
import project.map.*;
import project.move.HunterMove;


public class HunterUpdateTest {

	Map map;
	Player p1;
	Enemy e1;
	Enemy e2;
	
	
	@Before
	public void setup() {
		map = new Map(20, 20);
		p1 = new Player(4, 4, map);
		e1 = new Enemy(4, 10, map, new HunterMove());
		e2 = new Enemy(10, 4, map, new HunterMove());
		map.addEntity(p1);
		map.addEntity(e1);
		map.addEntity(e2);
	}
	
	@Test
	public void testMoveSuccess() {
		e1.update();
		e2.update();
		assertEquals(e1.getxPos(), 4);
		assertEquals(e1.getyPos(), 9);
		assertEquals(e2.getxPos(), 9);
		assertEquals(e2.getyPos(), 4);
	}

	@Test
	public void testMoveFailure() {
		Wall w1 = new Wall(4, 9, map);
		Wall w2 = new Wall(9, 4, map);
		map.addEntity(w1);
		map.addEntity(w2);
		e1.update();
		e2.update();
		assertEquals(e1.getyPos(), 10);
		assertEquals(e2.getxPos(), 10);
	}
	
}
