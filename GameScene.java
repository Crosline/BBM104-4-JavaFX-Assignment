


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Button8Bit;
import models.Enemy;
import models.InfoLabel;
import models.Player;

public class GameScene {

	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;

	private static final int W = 450;
	private static final int H = 600;

	private Stage stage;
	private String icon = "models/resources/icon.png";

	public Player p = new Player();
	private ImageView playerCar;
	private ImageView road = new ImageView("/models/resources/game/road.png");
	private int score = 0;
	private int level = 1;

	InfoLabel scoreLabel = new InfoLabel("POINTS:"+ score);
	InfoLabel levelLabel = new InfoLabel("LEVEL:"+ level);

	Enemy[] enemyList = new Enemy[6];
	int eLoc[] = {-150, -250, -350, -450, -550, -650, -650};
	Random positionGenerator = new Random();

	private AnimationTimer gameTimer;


	public GameScene() {
		paintStage();
		buttonListener(p);
	}

	private void buttonListener(Player p) {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
					p.dx = -3.5;
				}
				else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
					p.dx = 3.5;
				}
				else if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
					p.dv = 0.4;
				}
				else if(event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.ENTER ||event.getCode() == KeyCode.BACK_SPACE) {
					stage.show();
					gameStage.hide();
					gameTimer.stop();
				}

			}


		});

		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
					p.dx = 0;
				}else if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
					p.dv = -0.3;
				}
			}
		});
	}

	private void paintStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, W, H);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
	}

	public void newGame(Stage stage) {
		gameStage.setTitle(stage.getTitle());
		gameStage.getIcons().add(new Image(icon));
		paintRoad(p);
		paintCar(p);
		backButton(340,540,"BACK");
		createEnemies(p);
		gameLoop(p);
		gameStage.show();

		this.stage = stage;
		this.stage.hide();
	}

	private void backButton(int x, int y, String string) {
		Button8Bit backButton = createButton(x, y, string);

		backButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				stage.show();
				gameStage.hide();
				gameTimer.stop();
			}
		});
	}	private Button8Bit createButton(int x, int y, String string) {
		Button8Bit button = new Button8Bit(string);
		gamePane.getChildren().add(button);
		button.setLayoutX(x);
		button.setLayoutY(y);
		return button;
	}

	private void paintCar(Player p) {
		playerCar = p.car;
		gamePane.getChildren().add(playerCar);
	}

	private void paintRoad(Player p) {
		road.setLayoutX(0);
		gamePane.getChildren().add(road);

		scoreLabel.setLayoutX(8);
		scoreLabel.setLayoutY(8);
		gamePane.getChildren().add(scoreLabel);


		levelLabel.setLayoutX(8);
		levelLabel.setLayoutY(24);
		gamePane.getChildren().add(levelLabel);
	}

	private void createEnemies(Player p) {
		for(int i = 0; i < enemyList.length; i++) {
			enemyList[i] = new Enemy(ThreadLocalRandom.current().nextInt(113,303), ThreadLocalRandom.current().nextInt(-850,245)/*eLoc[i]*/);
			gamePane.getChildren().add(enemyList[i].enemy);
		}
		checkCollission();
	}

	private void checkCollission() {
		for(int i = 0; i < enemyList.length; i++) {
			for(int j = 0; j < i; j++) {
				if (enemyList[i].getRekt().intersects(enemyList[j].getRekt())) {; //eLoc[(int) (positionGenerator.nextDouble()*6)]
				enemyList[j].x = ThreadLocalRandom.current().nextInt(113,303);
				enemyList[j].y = -ThreadLocalRandom.current().nextInt(150,650);
			}
			}
		}
	}

	private void moveEnemies(Player p) {
		for(int i = 0; i < enemyList.length; i++) {
			//if(p.v > 0)
				enemyList[i].move((int) p.v);
			if(enemyList[i].enemy.getLayoutY() > 600) {
				enemyList[i].y = (-ThreadLocalRandom.current().nextInt(550,1150));
				enemyList[i].x = (ThreadLocalRandom.current().nextInt(113,303));
				enemyList[i].scored = false;
				enemyList[i].begin();
			}
			if(510 <= enemyList[i].enemy.getLayoutY() && !enemyList[i].scored && enemyList[i].enemy.getLayoutY() < 600) {
				enemyList[i].scored = true;
				enemyList[i].after();
				setScore();
			}
			if(p.getHex().intersects(enemyList[i].getRekt())) {
				p.end();
				enemyList[i].end();
				gameTimer.stop();
				InfoLabel gameOver = new InfoLabel("GAME OVER!\nYour Score:"+score+"\nYour Level:"+level+"\n\nPress ENTER to restart!\n\n");
				scoreLabel.setText("");
				levelLabel.setText("");
				gameOver.setTextRed();
				gameOver.setTTF(15);
				gameOver.setTextAlignment(TextAlignment.CENTER);
				gameOver.setLayoutX(55);
				gameOver.setLayoutY(200);
				gamePane.getChildren().add(gameOver);
			}
		}
		checkCollission();
	}

	//new position


	private void setScore() {
		if (score >= level*level*5) {
			level += 1;
			score = score + level;
			}
		else
			score = score + level;
		p.setMaxV(6+level);
		scoreLabel.setText("POINTS:"+score);
		levelLabel.setText("LEVEL:"+level);
	}

	private void gameLoop(Player p) {
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				moveEnemies(p);
				p.move(road);
			}
		};
		gameTimer.start();

	}


}
