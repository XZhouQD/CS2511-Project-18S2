package project.app;

import project.collectable.*;
import project.entity.*;
import project.map.Map;
import project.map.Mastermind;
import project.victory.*;
import project.move.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultGame {
	
	Mastermind game;
	Map map;
	boolean defaultStatus;
	ArrayList<String> inputs;
	private Hashtable<Color,Integer> keyColor;
    private Hashtable<Color,Integer> doorColor;
	
	public DefaultGame() {
		defaultStatus = true;
	}
	
	public DefaultGame(ArrayList<String> inputs) {
		this.inputs = inputs;
		defaultStatus = false;
		this.keyColor = new Hashtable<>();
	    this.doorColor = new Hashtable<>();
	    keyColor.put(Color.BLUE, 0); doorColor.put(Color.BLUE, 0);
	    keyColor.put(Color.RED, 0); doorColor.put(Color.RED, 0);
	    keyColor.put(Color.GREEN, 0); doorColor.put(Color.GREEN, 0);
	}
	
	public Mastermind loadGame() {
		if(defaultStatus)
			return loadDefaultGame();
		return loadCustomGame();
	}
	
	public Mastermind loadCustomGame() {
		Mastermind game = new Mastermind();
        Map map = new Map(15, 15);
        ArrayList<Condition> conditions = new ArrayList<>();
        
        ArrayList<Condition> conditionArrayList = new ArrayList<>();
        conditionArrayList.add(new ExitCondition());
        conditionArrayList.add(new SwitchCondition());
        conditionArrayList.add(new TreasureCondition());
        conditionArrayList.add(new EnemyCondition());

        Entity e;
        // add walls around the map
        for (int i = 0; i < map.getxBorder(); i++) {
            for (int j = 0; j < map.getyBorder(); j++) {
                if (i ==0 || i == map.getyBorder() - 1 || j == 0 || j == map.getyBorder() - 1) {
                    map.addEntity(new Wall(i,j,map));
                }
            }
        }
        
        String pattern = "(\\D+)\\s+(\\d+)\\s+(\\d+)";
        Pattern r = Pattern.compile(pattern);
        
        for (String s : inputs) {
        	Matcher m = r.matcher(s);
        	if(m.find()) {
        		String name = m.group(1);
        		int x = Integer.parseInt(m.group(2));
        		int y = Integer.parseInt(m.group(3));
        		System.out.println(name + " find at " + x + " " + y);
        		switch (name) {
        		case "Wall":
                    map.addEntity(new Wall(x,y,map)); break;
                case "Hover":
                    map.addEntity(new Item(x, y, map, new Hover())); break;
                case "Hound":
                    e = new Enemy(x, y, map, new HoundMove());
                    map.addEntity(e); break;
                case "Hunter":
                    e = new Enemy(x, y, map, new HunterMove());
                    map.addEntity(e); break;
                case "Strategist":
                    e = new Enemy(x, y, map, new StrategistMove());
                    map.addEntity(e); break;
                case "Player":
                    e = new Player(x, y, map);
                    // if already has a player then break
                    if (map.onMap(e)) break;
                    map.addEntity(e); break;
                case "Arrow":
                    map.addEntity(new Item(x, y, map, new Arrow())); break;
                case "Armor":
                    map.addEntity(new Item(x, y, map, new Armor())); break;
                case "Sword":
                    map.addEntity(new Item(x, y, map, new Sword())); break;
                case "Bomb":
                    map.addEntity(new Item(x, y, map, new UnlitBomb())); break;
                case "Invincibility":
                    map.addEntity(new Item(x, y, map, new Invincibility())); break;
                case "Key":
                    map.addEntity(new Item(x, y, map, new Key(pickColor(keyColor)))); break;
                case "Pit":
                    map.addEntity(new Pit(x, y, map)); break;
                case "poisonMist":
                    map.addEntity(new poisonMist(x, y, map)); break;
                case "Boulder":
                    map.addEntity(new Boulder(x, y, map)); break;
                case "Door":
                    map.addEntity(new Door(x, y, map, pickColor(doorColor))); break;
                case "Floor Switch":
                    map.addEntity(new FloorSwitch(x, y, map)); break;
                case "Gold":
                    map.addEntity(new Item(x, y, map, new Treasure())); break;
                case "Exit":
                    map.addEntity(new Exit(x, y, map)); break;
                case "Horizontal Moving Wall":
                	map.addEntity(new HorizontalMovingWall(x, y, map)); break;
                default:
                	for (Condition c: conditionArrayList) {
                        if (c.toString().equals(name)) {
                            System.out.println(name);
                            conditions.add(c);
                        }
                    }
        		}
        	} else {
        		//Do Nothing
        	}
        }
        game.setEndGameConditions(new Victory(conditions));
        game.setMap(map);
        return game;
	}
	
    public Mastermind loadDefaultGame() {
        Mastermind game = new Mastermind();
        Map map = new Map(15, 15);

        // add walls around the map
        for (int i = 0; i < map.getxBorder(); i++) {
            for (int j = 0; j < map.getyBorder(); j++) {
                if (i ==0 || i == map.getyBorder() - 1 || j == 0 || j == map.getyBorder() - 1) {
                    map.addEntity(new Wall(i,j,map));
                }
            }
        }

        // player set
        map.addEntity(new Player(13, 1, map));
        map.addEntity(new Item(13, 2, map, new Armor()));
        map.addEntity(new Item(9, 2, map, new Sword()));

        map.addEntity(new Enemy(4, 5, map, new HunterMove()));
        map.addEntity(new Enemy(10, 12, map, new HoundMove()));
        map.addEntity(new Enemy(5, 2, map, new CowardMove()));
        map.addEntity(new Enemy(6, 3, map, new StrategistMove()));

        map.addEntity(new Item(7, 8, map, new Arrow()));
        map.addEntity(new Item(1, 2, map, new Hover()));
        map.addEntity(new Item(2, 2, map, new Invincibility()));


        map.addEntity(new Item(6, 8, map, new UnlitBomb()));


        map.addEntity(new Pit(4, 3, map));
        map.addEntity(new poisonMist(3, 8, map));

        // getting key to exit
        map.addEntity(new Door(1, 3, map, Color.BLUE));
        map.addEntity(new Item(3, 5, map, new Key(Color.BLUE)));
        map.addEntity(new Wall(2, 3, map));
        map.addEntity(new Wall(3, 3, map));
        map.addEntity(new Wall(3, 2, map));
        map.addEntity(new Wall(3, 1, map));

        // barrier
        map.addEntity(new HorizontalMovingWall(10, 9, map));
        map.addEntity(new HorizontalMovingWall(11, 9, map));

        map.addEntity(new Boulder(5, 12, map));
        map.addEntity(new FloorSwitch(8, 12, map));

        // build a room of exit and door
        map.addEntity(new Item(1, 1, map, new Key(Color.GREEN)));
        map.addEntity(new Wall(1, 11, map));
        map.addEntity(new Wall(2, 11, map));
        map.addEntity(new Wall(3, 11, map));
        map.addEntity(new Door(3, 12, map, Color.GREEN));
        map.addEntity(new Wall(3, 13, map));
        map.addEntity(new Exit(1, 13, map));
        map.addEntity(new Item(1, 12, map, new Treasure()));

        game.setMap(map);
        ArrayList<Condition> conditions = new ArrayList<>();
        conditions.add(new ExitCondition());
        game.setEndGameConditions(new Victory(conditions));
        return game;
    }
    
    private Color pickColor(Hashtable<Color,Integer> table) {
        Color temp;
        Set<Color> keys = table.keySet();
        Iterator<Color> itr = keys.iterator();
        while (itr.hasNext()) {
            temp = itr.next();
            if (table.get(temp) == 0) {
                // increment by 1
                table.put(temp,1);
                return temp;
            }
        }
        return Color.NONE;
    }
}
