

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Assignment4 extends Application {

	private String icon = "models/resources/icon.png";

	@Override
	public void start(Stage primaryStage) {
		try {
			MenuScene scene = new MenuScene();
			primaryStage = scene.mainStage;
			primaryStage.getIcons().add(new Image(icon));
			primaryStage.setTitle("HUBBM-Racer");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
