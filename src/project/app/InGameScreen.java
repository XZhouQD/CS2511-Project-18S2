package project.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import project.map.Mastermind;

/**
 * Manages (read: sets up) the view for the 'game' state.
 */
public class InGameScreen {
	Stage currStage;
	FXMLLoader fxmlloader;
	Mastermind game;
	DefaultGame defaultGame;

	public InGameScreen(Stage currStage, Mastermind game, DefaultGame defaultGame) {
		this.currStage = currStage;
		this.game = game;
		this.fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("project/app/InGame.fxml"));
		this.defaultGame = defaultGame;
	}

	public void start() {
		currStage.setTitle("Dungeon Game");
		fxmlloader.setController(new InGameController(game,currStage,defaultGame));

		try {
			Parent root = fxmlloader.load();
			Scene sc = new Scene(root, 637, 480);
			root.requestFocus();
			currStage.setScene(sc);
			currStage.show();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("In Game Tutorial");
            alert.setHeaderText(null);
            alert.setContentText("Use Arrow keys on keyboard to complete player Movement:\n" +
                    "   Up: Player moves up\n" +
                    "   Down: Player moves down\n" +
                    "   Left: Player moves left\n" +
                    "   Right: Player moves right\n\n" +
                    "Use keys to attack\n" +
                    "   W: Shot upwardly\n" +
                    "   S: Shot downwardly\n" +
                    "   A: Shot left\n" +
                    "   D: Shot right\n" +
                    "   R: Drop Bomb\n");
            alert.showAndWait();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
