package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class Button8Bit extends Button{

	private final String FONT_PATH = "models/resources/font.ttf";
	private final String BUTTON_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('/models/resources/pressed.png');";
	private final String BUTTON_RELEASED = "-fx-background-color: transparent; -fx-background-image: url('/models/resources/released.png');";

	public Button8Bit(String text) {
		setText(text);
		setTTF();
		setPrefHeight(45);
		setPrefWidth(110);
		setStyle(BUTTON_RELEASED);
		buttonListener();
	}
	private void setTTF() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 20));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			setFont(Font.font("Trebuchet",20));
		}
	}

	private void setPressedStyle() {
		setStyle(BUTTON_PRESSED);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+5);
	}

	private void setReleasedStyle() {
		setStyle(BUTTON_RELEASED);
		setPrefHeight(45);
		setLayoutY(getLayoutY()-5);
	}

	private void buttonListener() {
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					setPressedStyle();
				}

			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					setReleasedStyle();
				}

			}
		});

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}
}
