package bomberman.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoLabel extends Label {

    public final static String FONT_PATH = "resources/model/kenvector_future.ttf";
    public final static String BG_IMAGE = "view/yellow_button06.png";

    public InfoLabel(String text) {
        setPrefWidth(237.5);
        setPrefHeight(30.625);
//        setPadding(new Insets(40, 40, 40, 40));
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER);

        BackgroundImage bgImage = new BackgroundImage(new Image(BG_IMAGE, 237.5, 30.625, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(bgImage));
    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 14.375));
        } catch (FileNotFoundException e) {
            System.out.println("Could not load font. Using defaults...");
            setFont(Font.font("Verdana", 14.375));
        }
    }
}
