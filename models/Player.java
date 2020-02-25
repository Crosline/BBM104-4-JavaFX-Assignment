package models;


import java.awt.Polygon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {

	public int maxV = 6;
	public double v = 0;
	public int layer = -1200;
	public double dv = 0;
	public double dx = 0;

	private String image = "models/resources/game/car/player.png";
	private String end = "models/resources/game/car/end.png";

	public ImageView car = new ImageView(image);

	int x = 207; // first positions
	int y = 510; // of the player

	public Polygon getHex() {
		Polygon hex = new Polygon();
		hex.addPoint(this.x + 11, this.y + 1);
		hex.addPoint(this.x + 26, this.y + 1);
		hex.addPoint(this.x + 35, this.y + 8);
		hex.addPoint(this.x + 35, this.y + 45);
		hex.addPoint(this.x + 26, this.y + 54);
		hex.addPoint(this.x + 11, this.y + 54);
		hex.addPoint(this.x + 1, this.y + 45);
		hex.addPoint(this.x + 1, this.y + 11);
		return hex;
	}

	public Player() {
		car.setLayoutX(x);
		car.setLayoutY(y);
	}

	public void move(ImageView road) {
		//MAX MIN SPEED
		if (v + dv < maxV && v + dv > 0)
			v += dv;
		else if (v + dv <= 0)
			v = 0;
		// borders
		if (x + dx < 303 && x + dx > 113)
			x += dx;
		// road
		if (layer >= -v) {
			layer = -1200;
		}
		else {
			layer += v+2.5;
		}

		road.setLayoutY(layer);

		car.setLayoutX(x);
		car.setLayoutY(y);
	}

	public void end() {
		Image c = new Image(end);
		car.setImage(c);
	}	public void begin() {
		Image c = new Image(image);
		car.setImage(c);
	}
	public void setMaxV(int a) {
		maxV = a;
	}

}
