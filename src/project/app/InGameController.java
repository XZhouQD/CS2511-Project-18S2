package project.app;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import project.entity.Entity;
import project.entity.Player;
import project.map.Mastermind;
import project.move.Action;

import java.util.HashMap;

/**
 * A controller for the application's 'game' state.
 */
public class InGameController extends  DefaultGame{
    @FXML
	private Canvas playArea;

    @FXML
	private Label sword;

	@FXML
	private Label arrow;

	@FXML
	private Label bomb;

	@FXML
	private Label invincibility;

	@FXML
	private Label hover;

	@FXML
	private Label gold;

	@FXML
	private Label key;

    @FXML
    private Label Armor;

    private Mastermind game;
    private Stage stage;
    private DefaultGame defaultGame;

    public InGameController (Mastermind game, Stage stage, DefaultGame defaultGame) {
        this.game = game;
        this.defaultGame = defaultGame;
        this.stage = stage;
    }

    @FXML
	public void initialize() {
		updatePlayArea();
		sword.setText("0");
        arrow.setText("0");
        bomb.setText("0");
        invincibility.setText("0");
        hover.setText("0");
        gold.setText("0");
        key.setText("0");
        Armor.setText("0");
	}

	/**
	 * Refreshes the playArea canvas to reflect any changes made to the current layout of entities.
	 */
	private void updatePlayArea() {
		GraphicsContext gc = playArea.getGraphicsContext2D();
		gc.clearRect(0, 0, playArea.getWidth(), playArea.getHeight());
		for (Entity e : game.getMapEntities()) {
			Image graphic = new Image(e.getGraphic());
			gc.drawImage(graphic, 32 * e.getxPos(), 32 * e.getyPos(), 32, 32);
		}
	}

    /**
     *  Display the Inventory that player collected
     */
	private void updateInventory() {
        HashMap<String,Integer> Inventory = game.delegateGetInventory();
        if (Inventory.containsKey("Sword")) {
            sword.setText(Inventory.get("Sword").toString());
        }
        if (Inventory.containsKey("Arrow")) {
            arrow.setText(Inventory.get("Arrow").toString());
        }

        if (Inventory.containsKey("Bomb")) {
            bomb.setText(Inventory.get("Bomb").toString());
        }

        if (Inventory.containsKey("Invincibility")) {
            invincibility.setText(Inventory.get("Invincibility").toString());
        }

        if (Inventory.containsKey("Hover")) {
            hover.setText(Inventory.get("Hover").toString());
        }

        if (Inventory.containsKey("Treasure")) {
            gold.setText(Inventory.get("Treasure").toString());
        }

        if (Inventory.containsKey("Key")) {
            key.setText(Inventory.get("Key").toString());
        }

        if (Inventory.containsKey("Armor")) {
            Armor.setText(Inventory.get("Armor").toString());
        }
    }

	@FXML
    public void handleKeyPress(KeyEvent event) {
	    // find current player
        Player currPlayer = null;
        for (Entity e : game.getMapEntities()) {
            if (e instanceof Player) {
                currPlayer = ((Player) e); break;
            }
        }

        Action action;
        switch(event.getCode()) {
            case UP:	action = Action.MOVE_UP;	    break;
            case DOWN:	action = Action.MOVE_DOWN;	    break;
            case LEFT:	action = Action.MOVE_LEFT;	    break;
            case RIGHT:	action = Action.MOVE_RIGHT;	    break;
            case W:     action = Action.ARROW_UP;       break;
            case A:     action = Action.ARROW_LEFT;     break;
            case S:     action = Action.ARROW_DOWN;     break;
            case D:     action = Action.ARROW_RIGHT;    break;
            case R:     action = Action.BOMB; break;
            default:	return;
        }

        currPlayer.addAction(action);

        game.updateGame();
        // if game finishes
        if (!game.playerExit()) {
            dialogBox("Oooops","Your warrior died  (＞﹏＜)");
            return;
        }
        if (game.endGame()) {
            dialogBox("Congratulations","(～o▔▽▔)～o   VICTORY!!  o～(▔▽▔o～) ");
        }
        updateInventory();
        updatePlayArea();
    }

    @FXML
    public void handleRestart(){
        InGameScreen sc = new InGameScreen(stage,defaultGame.loadGame(),defaultGame);
        sc.start();
    }

    @FXML
    public void handleExit() {
        StartScreen startScreen = new StartScreen(stage);
        startScreen.start();
    }

    private void dialogBox(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
        handleRestart();
    }
}
