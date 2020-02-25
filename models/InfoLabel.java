package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InfoLabel extends Label {

	private final String FONT_PATH = "models/resources/font.ttf";

	public InfoLabel(String text) {
		setText(text);
		setTTF(12);
		setTextFill(Color.WHITE);
	}
	public void setTTF(int i) {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), i));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Trebuchet",12));
		}
	}
	public void setTextRed(){
		this.setTextFill(Color.RED);
	}

}
