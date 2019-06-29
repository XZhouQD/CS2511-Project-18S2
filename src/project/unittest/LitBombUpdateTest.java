package project.unittest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import project.entity.*;
import project.map.*;
import project.move.HunterMove;

public class LitBombUpdateTest {

	Map map;
	LitBomb lb;
	Enemy e1;
	Enemy e2;
	FloorSwitch fs1;
	Wall w1;
	
	@Before
	public void setup() {
		map = new Map(20, 20);
		lb = new LitBomb(5, 5, map);
		e1 = new Enemy(4, 5, map, new HunterMove());
		e2 = new Enemy(5, 4, map, new HunterMove());
		fs1 = new FloorSwitch(5, 6, map);
		w1 = new Wall(6, 5, map);
		map.addEntity(e1);
		map.addEntity(e2);
		map.addEntity(fs1);
		map.addEntity(w1);
	}
	
	@Test
	public void testExplosion() {
		lb.update();
		lb.update();
		lb.update();
		lb.update();
		lb.update();
		//haven't explode yet, enemy should remain there
		assertEquals(map.entitiesAtPos(4, 5).contains(e1), true);
		lb.update();
		//enemy should be removed
		assertEquals(map.entitiesAtPos(4, 5).contains(e1), false);
		assertEquals(map.entitiesAtPos(5, 4).contains(e2), false);
		//floor switch and wall should remain
		assertEquals(map.entitiesAtPos(5, 6).contains(fs1), true);
		assertEquals(map.entitiesAtPos(6, 5).contains(w1), true);
	}

}
