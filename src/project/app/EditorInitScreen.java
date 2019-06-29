package project.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EditorInitScreen {
    private Stage stage;
    private String title;
    private FXMLLoader fxmlLoader;

    public EditorInitScreen(Stage stage) {
        this.stage = stage;
        this.title = "Editor Mode";
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("project/app/EditorCreatingPhase.fxml"));
    }

    public void start()  {
        stage.setTitle(title);
        // set controller for start.fxml
        fxmlLoader.setController(new EditorInitController(stage));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 480);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
