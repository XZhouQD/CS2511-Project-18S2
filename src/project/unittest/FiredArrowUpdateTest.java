package project.unittest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import project.entity.*;
import project.map.*;
import project.move.HunterMove;

public class FiredArrowUpdateTest {

	Map map;
	FiredArrow faw;
	Enemy e1;
	FloorSwitch fs1;
	Wall w1;
	
	@Before
	public void setup() {
		map = new Map(20, 20);
		faw = new FiredArrow(3, 3, map, Direction.RIGHT);
		e1 = new Enemy(5, 3, map, new HunterMove());
		fs1 = new FloorSwitch(4, 3, map);
		w1 = new Wall(6, 3, map);
		map.addEntity(faw);
		map.addEntity(w1);
		map.addEntity(fs1);
	}
	
	@Test
	public void testPassFSandStopWall() {
		faw.update();
		assertEquals(faw.getxPos(), 4); //move through floor switch
		faw.update();
		faw.update();
		assertEquals(map.entitiesAtPos(6, 3).contains(faw), false); //arrow break at wall
	}
	
	@Test
	public void testKillEnemy() {
		map.addEntity(e1);
		faw.update();
		faw.update();
		//test if killed the enemy
		assertEquals(map.entitiesAtPos(5, 3).contains(faw), false);
		assertEquals(map.entitiesAtPos(5, 3).contains(e1), false);
	}

}
