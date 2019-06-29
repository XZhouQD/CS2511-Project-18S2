package project.unittest;

import org.junit.Before;
import org.junit.Test;

import project.entity.Enemy;
import project.entity.Player;
import project.map.Map;
import project.move.HoundMove;
import project.move.HunterMove;

public class HoundUpdateTest {

	Map map;
	Player p1;
	Enemy e1;
	Enemy e2;
	
	@Before
	public void setup() {
		map = new Map(20, 20);
		p1 = new Player(8, 8, map);
		e1 = new Enemy(8, 15, map, new HunterMove());
		e2 = new Enemy(7, 1, map, new HoundMove());
		map.addEntity(p1);
		map.addEntity(e1);
		map.addEntity(e2);
	}
	
	@Test
	public void test() {
		e1.update();
		e2.update();
		System.out.println(e1.getxPos() + " " + e1.getyPos());
		System.out.println(e2.getxPos() + " " + e2.getyPos());
	}

}
