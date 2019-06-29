package project.app;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StartController extends DefaultGame {

    @FXML
    private ImageView startField;

    private Stage stage;

    public StartController(Stage s) {
        stage = s;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleStart() throws CloneNotSupportedException{
//        load
        InGameScreen sc = new InGameScreen(stage, super.loadDefaultGame(), this);
        sc.start();
    }

    @FXML
    public void handleEditorMode() {
        EditorInitScreen sc = new EditorInitScreen(stage);
        sc.start();
    }
}
