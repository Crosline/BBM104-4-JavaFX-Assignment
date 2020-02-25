package models;

import java.awt.Rectangle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {

	private String image = "models/resources/game/car/enemy.png";
	private String imageAfter = "models/resources/game/car/enemyAfter.png";
	private String end = "models/resources/game/car/end.png";


	public ImageView enemy = new ImageView(image);

	public double y;
	public double x;
	public boolean scored = false;

	public Rectangle getRekt() {
		return new Rectangle((int) this.x,(int) this.y, 35, 55);
	}

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		enemy.setLayoutX(x);
		enemy.setLayoutY(y);
	}

	public void move(double dy) {
		y = y + dy/5*2.75;
		/*/5*3*/
		enemy.setLayoutX(x);
		enemy.setLayoutY(y);
	}

	public void end() {
		Image c = new Image(end);
		enemy.setImage(c);
	}	public void after() {
		Image c = new Image(imageAfter);
		enemy.setImage(c);
	}	public void begin() {
		Image c = new Image(image);
		enemy.setImage(c);
	}
}
