package project.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import project.entity.*;
import project.map.*;
import project.collectable.*;

public class EntityTest {

    Map map;
    Player p1;
    Door d1;
    Key keyG;
    Key keyB;
    Wall w1;

    @Before
    public void setup() {
        map = new Map(15, 15);

        p1 = new Player(4, 4, map);
        keyG = new Key(Color.GREEN);
        keyB = new Key(Color.BLUE);
        d1 = new Door(4, 4, map, Color.BLUE);
        w1 = new Wall(4, 4, map);
    }

    @Test
    public void testDoorLock() {
        assertEquals(d1.onMove(p1), false);
    }

    @Test
    public void testDoorLockColor() {

        Item key1 = new Item(4, 4, map, keyG);
        key1.onMove(p1);
        assertEquals(d1.onMove(p1), false);
    }

    @Test
    public void testDoorOpenColor() {
        Item key1 = new Item(4, 4, map, keyB);
        key1.onMove(p1);
        assertEquals(d1.onMove(p1), true);
    }
}
