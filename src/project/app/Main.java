package project.app;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The entrypoint into the javafx application.
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
        try {
            StartScreen startScreen = new StartScreen(primaryStage);
            startScreen.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
