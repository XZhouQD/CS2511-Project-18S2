package project.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreen {
    private Stage stage;
    private String title;
    private FXMLLoader fxmlLoader;

    public StartScreen(Stage s) {
        this.stage = s;
        this.title = "Start InGameScreen";
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("project/app/Menu.fxml"));
    }

    public void start()  {
        stage.setTitle(title);
        // set controller for start.fxml
        fxmlLoader.setController(new StartController(stage));

        try {
            // load into a Parent node called root
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root, 800, 600);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
