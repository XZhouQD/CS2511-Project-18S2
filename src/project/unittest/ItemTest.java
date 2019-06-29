package project.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import project.entity.*;
import project.map.Map;
import project.move.HunterMove;
import project.collectable.*;

public class ItemTest {

	Map map;
	Player p1;
	Enemy e1;
	Arrow arrow;
	Key key;	
	
	@Before
	public void setup() {
		map = new Map(20, 20);
		p1 = new Player(2, 7, map);
		e1 = new Enemy(1, 9, map, new HunterMove());
		arrow = new Arrow();
		key = new Key(Color.BLUE);
	}
	
	@Test
	public void testPlayerCollectKey() {
		Item key1 = new Item(2, 7, map, key);
		assertEquals(key1.onMove(p1), true);
		assertEquals(p1.getKey(), Color.BLUE);
	}
	
	@Test
	public void testEnemyCollectKey() {
		Item key1 = new Item(1, 9, map, key);
		assertEquals(key1.onMove(e1), false);
	}
	
	@Test
	public void testPlayerCollectArrow() {
		Item arrow1 = new Item(2, 7, map, arrow);
		assertEquals(arrow1.onMove(p1), true);
		assertEquals(p1.playerHas("Arrow"), 1);
	}
	
}
