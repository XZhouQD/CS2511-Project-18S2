/**
 * JUnit tests
 */
package project.unittest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import project.entity.*;
import project.map.*;
import project.collectable.*;


public class MapTest {

	Map map1;
	Map map2;
	Player p1;
	Key key;
	Item key1;
	
	@Before
	public void setup() {
		map1 = new Map(20, 15);
		map2 = new Map(-2, -5);
		p1 = new Player(5, 5, map1);
		key = new Key(Color.BLUE);
		key1 = new Item(5, 5, map1, key);
	}
	
	@Test
	public void testMapBorder() {
		assertEquals(map1.getxBorder(), 20);
		assertEquals(map1.getyBorder(), 15);
	}
	
	@Test
	public void testMapBorderOutOfRange() {
		assertEquals(map2.getxBorder(), 1);
		assertEquals(map2.getyBorder(), 1);
	}
	
	@Test
	public void testEntityOnPos() {
		map1.addEntity(p1);
		map1.addEntity(key1);
		List<Entity> eList = map1.entitiesAtPos(5, 5);
		assertEquals(eList.contains(p1), true);
		assertEquals(eList.contains(key1), true);
	}

}
