package project.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import project.collectable.*;
import project.entity.*;
import project.map.Map;
import project.move.Action;

public class PlayerUpdateTest {

    Map map;
    Player p1;
    Invincibility invincibility;
    Sword sword;

    @Before
    public void setup() {
        map = new Map(20, 20);
        p1 = new Player(3, 5, map);
        map.addEntity(p1);
        sword = new Sword();
        invincibility = new Invincibility();
    }

    @Test
    public void invincibilityUpdateTest() {
        Item inv1 = new Item(3, 5, map, invincibility);
        assertEquals(inv1.onMove(p1), true);
        assertEquals(p1.playerHas("Invincibility"), 30);

        for (int i = 0; i < 15; i++) {
        	p1.addAction(Action.ARROW_DOWN);
            p1.update();
        }
        assertEquals(p1.playerHas("Invincibility"), 15);

        for (int i = 0; i < 15; i++) {
        	p1.addAction(Action.ARROW_DOWN);
            p1.update();
        }
        assertEquals(p1.playerHas("Invincibility"), 0);
    }
    
    @Test
    public void moveSuccessUpdateTest() {
    	//single move action test
    	p1.addAction(Action.MOVE_LEFT);
    	p1.update();
    	assertEquals(p1.getxPos(), 2);
    	p1.addAction(Action.MOVE_DOWN);
    	p1.update();
    	assertEquals(p1.getyPos(), 6);
    	
    	//double update test
    	p1.addAction(Action.MOVE_RIGHT);
    	p1.addAction(Action.MOVE_UP);
    	p1.update();
    	p1.update();
    	assertEquals(p1.getxPos(), 3);
    	assertEquals(p1.getyPos(), 5);
    }
    
    @Test
    public void moveFailureUpdateTest() {
    	Wall w1 = new Wall(2, 5, map);
    	map.addEntity(w1);
    	p1.addAction(Action.MOVE_LEFT);
    	p1.update();
    	assertEquals(p1.getxPos(), 3);
    }
	
	//
    /*
     * These tests are not applicable due to refactoring of design
     * 
    @Test
    public void moveBorderUpdateTest() {
    	p1.addAction(Action.MOVE_LEFT);
    	p1.update();
    	p1.addAction(Action.MOVE_LEFT);
    	p1.update();
    	p1.addAction(Action.MOVE_LEFT);
    	p1.update();
    	p1.addAction(Action.MOVE_LEFT);
    	p1.update();
    	
    	assertEquals(p1.getxPos(), 0);
    }
    
    @Test
	public void testSwordUse() {
		Item sword1 = new Item(2, 8, map, sword);
		sword1.onMove(p1);
		assertEquals(p1.playerHas("Sword"), 5);

		p1.battle();
		assertEquals(p1.playerHas("Sword"), 4);
		p1.battle();
		p1.battle();
		p1.battle();
		p1.battle();
		assertEquals(p1.playerHas("Sword"), 0);
	}
    */

}
