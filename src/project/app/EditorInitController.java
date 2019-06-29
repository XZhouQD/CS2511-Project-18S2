package project.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.collectable.*;
import project.entity.*;
import project.entity.Color;
import project.map.Map;
import project.map.Mastermind;
import project.move.*;
import project.victory.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class EditorInitController {
    @FXML
    private Canvas playArea;

    @FXML
    private ChoiceBox<String> entities;

    @FXML
    private TextField xPos;

    @FXML
    private TextField yPos;

    @FXML
    private ListView<String> exitMode;

    @FXML
    private ListView<String> selectedMode;

    private Stage stage;
    private Mastermind game;
    private Map map;
    private Hashtable<Color,Integer> keyColor;
    private Hashtable<Color,Integer> doorColor;
    private ObservableList<String> selectList;
    private ObservableList<String> viewList;
    private ArrayList<Condition> conditionArrayList;
    private ArrayList<String> inputs;

    public EditorInitController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        this.game = new Mastermind();
        this.map = new Map(15, 15);
        this.keyColor = new Hashtable<>();
        this.doorColor = new Hashtable<>();
        keyColor.put(Color.BLUE, 0); doorColor.put(Color.BLUE, 0);
        keyColor.put(Color.RED, 0); doorColor.put(Color.RED, 0);
        keyColor.put(Color.GREEN, 0); doorColor.put(Color.GREEN, 0);
        this.inputs = new ArrayList<String>();

        // add walls around the map
        for (int i = 0; i < map.getxBorder(); i++) {
            for (int j = 0; j < map.getyBorder(); j++) {
                if (i ==0 || i == map.getyBorder() - 1 || j == 0 || j == map.getyBorder() - 1) {
                    map.addEntity(new Wall(i,j,map));
                }
            }
        }
        game.setMap(map);
        updatePlayArea();
        xPos.setText("0"); yPos.setText("0");

        // init the choice box of entities
        setUpChoiceBox();
        setUpListView();
    }

    /**
     * initialise the listview by adding conditions to it
     */
    private void setUpListView() {
        // set up conditions
        conditionArrayList = new ArrayList<>();
        conditionArrayList.add(new ExitCondition());
        conditionArrayList.add(new SwitchCondition());
        conditionArrayList.add(new TreasureCondition());
        conditionArrayList.add(new EnemyCondition());

        selectList = FXCollections.observableArrayList();
        viewList = FXCollections.observableArrayList();
        selectedMode.setItems(viewList);
        exitMode.setItems(selectList);
        for (Condition c : conditionArrayList) {
            selectList.add(c.toString());
            if (c instanceof  ExitCondition) {
                viewList.add(c.toString());
            }
        }
    }

    /**
     * add selections to the choice BOX of entites
     */
    private void setUpChoiceBox() {
        entities.getItems().add("Player");
        entities.getItems().add("Hover");
        entities.getItems().add("Hound");
        entities.getItems().add("Hunter");
        entities.getItems().add("Strategist");
        entities.getItems().add("Arrow");
        entities.getItems().add("Sword");
        entities.getItems().add("Bomb");
        entities.getItems().add("Invincibility");
        entities.getItems().add("Key");
        entities.getItems().add("Pit");
        entities.getItems().add("Door");
        entities.getItems().add("Boulder");
        entities.getItems().add("Exit");
        entities.getItems().add("Gold");
        entities.getItems().add("Wall");
        entities.getItems().add("Floor Switch");
        entities.getItems().add("Exit");
        entities.getItems().add("poisonMist");
        entities.getItems().add("Armor");
        entities.getItems().add("Horizontal Moving Wall");
        
        entities.setValue("Wall"); // default is wall
    }

    /**
     * add this entity to Map
     */
    @FXML
    public void handleEnter(){
        int x = Integer.parseInt(xPos.getText());
        int y = Integer.parseInt(yPos.getText());
        Entity e;
        switch(entities.getValue()) {
            // 10/15/2018 keyboard movements for player UP/DOWN is reversed, move method changed in playerUpdate, fixed
            case "Wall":
                map.addEntity(new Wall(x,y,map));  break;
            case "Hover":
                map.addEntity(new Item(x, y, map, new Hover())); break;
            case "Hound":
                map.addEntity(new Enemy(x, y, map, new HoundMove())); break;
            case "Hunter":
                map.addEntity(new Enemy(x, y, map, new HunterMove())); break;
            case "Strategist":
                map.addEntity(new Enemy(x, y, map, new StrategistMove())); break;
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
                map.addEntity(new Item(x, y, map, new Treasure()));  break;
            case "Exit":
                map.addEntity(new Exit(x, y, map)); break;
            case "Horizontal Moving Wall":
            	map.addEntity(new HorizontalMovingWall(x, y, map)); break;
        }
        inputs.add(entities.getValue() + " " + x + " " + y);
        updatePlayArea();
    }

    /**
     * iterate the hashMap to find a color
     */
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

    /**
     * exit to main menu
     */
    @FXML
    public void handleExit() {
        StartScreen startScreen = new StartScreen(stage);
        startScreen.start();
    }

    @FXML
    public void addConditionHandler(MouseEvent e) {
        String s = exitMode.getSelectionModel().getSelectedItem();
        if (!viewList.contains(s))  viewList.add(s);
    }

    /**
     * render the modified game to START with InGameScreen
     */
    @FXML
    public void handlePlay() {
        // temp condition need to be changed later
        ArrayList<Condition> conditions = new ArrayList<>();
        for (String s : viewList) {
            for (Condition e: conditionArrayList) {
                if (e.toString().equals(s)) {
                    System.out.println(s);
                    inputs.add(s + " 0 0");
                    conditions.add(e);
                }
            }
        }
        game.setEndGameConditions(new Victory(conditions));
        
        DefaultGame dg = new DefaultGame(inputs);

        InGameScreen sc = new InGameScreen(stage, dg.loadGame(), dg);
        sc.start();
    }

    @FXML
    public void removeConditionHandler(MouseEvent e) {
        String s = selectedMode.getSelectionModel().getSelectedItem();
        viewList.remove(s);
    }

    private void updatePlayArea() {
        GraphicsContext gc = playArea.getGraphicsContext2D();
        gc.clearRect(0, 0, playArea.getWidth(), playArea.getHeight());
        for (Entity e : game.getMapEntities()) {
            Image graphic = new Image(e.getGraphic());
            gc.drawImage(graphic, 32 * e.getxPos(), 32 * e.getyPos(), 32, 32);
        }
    }
}
