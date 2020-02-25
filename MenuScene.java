

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Button8Bit;
import models.InfoLabel;

public class MenuScene {

	private static final int W = 450;
	private static final int H = 600;
	private AnchorPane mainPane;
	private Scene mainScene;
	public Stage mainStage = new Stage();
	private InfoLabel label;
	int it = 0;

	public MenuScene() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, W, H);
		mainStage.setScene(mainScene);
		startButton(170, 200, "PLAY");
		infoButton(170, 275, "INFO");
		exitButton(170, 350, "EXIT");
		setBackground();
		setLogo();
	}

	private void info() {
		label = new InfoLabel(""
				+ "To move the car\n"
				+ "use A W D or Arrow Keys\n");
		label.setTextAlignment(TextAlignment.LEFT);
		label.setLayoutX(-500);
		label.setLayoutY(550);
		mainPane.getChildren().add(label);
	}

	private void startButton(int x, int y, String string) {
		Button8Bit startButton = createButton(x, y, string);

		startButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				GameScene game = new GameScene();
				game.newGame(mainStage);

			}
		});

	}

	private void infoButton(int x, int y, String string) {
		Button8Bit infoButton = createButton(x, y, string);

		infoButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (it == 0) {
					info();
					it +=1;
				}
				if (label.getLayoutX()!= 8) {
					label.setLayoutX(8);
				}
				else if(label.getLayoutX() == 8) {
					label.setLayoutX(-500);
				}
			}
		});

	}

	private void exitButton(int x, int y, String string) {
		Button8Bit exitButton = createButton(x, y, string);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {System.exit(0);}});
		}

	private Button8Bit createButton(int x, int y, String string) {
		Button8Bit button = new Button8Bit(string);
		mainPane.getChildren().add(button);
		button.setLayoutX(x);
		button.setLayoutY(y);
		return button;
	}

	private void setBackground() {
		Image bg = new Image("/models/resources/menu.png", 450, 600, false, false);
		BackgroundImage background = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

	private void setLogo() {
		ImageView logo = new ImageView("models/resources/logo.png");
		logo.setLayoutX(96);
		logo.setLayoutY(30);
		mainPane.getChildren().add(logo);
	}

}
