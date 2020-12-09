package bomberman.model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BomberManButton extends Button {

    private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent;-fx-background-image: url('/model/yellow_button_pressed.png')";
    private static final String BUTTON_FREE_STYLE = "-fx-background-color: transparent;-fx-background-image: url('/model/yellow_button.png');";

    public BomberManButton(String text) {
        setText(text);
        setButtonFont();
        setPrefWidth(118.75);
        setPrefHeight(30.675);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    private void setButtonFont() {

        try {
            String FONT_PATH = "resources/model/kenvector_future.ttf";
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 14.375));
        } catch (FileNotFoundException fileNotFoundException) {
            setFont(Font.font("Verdana", 14.375));
        }
    }

    private void setButtonPressedStyle() {

        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(28.125);
        setLayoutY(getLayoutY() + 2.5);
    }

    private void setButtonReleasedStyle() {

        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(28.125);
        setLayoutY(getLayoutY() - 2.5);
    }

    private void initializeButtonListeners() {

        setOnMousePressed(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });

        setOnMouseReleased(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });

        setOnMouseEntered(event -> setEffect(new DropShadow(10, Color.YELLOW)));

        setOnMouseExited(event -> setEffect(null));
    }
}