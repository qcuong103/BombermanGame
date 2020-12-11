package bomberman.model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class BomberManSubScene extends SubScene {

    private final static String FONT_PATH = "resources/model/kenvector_future.ttf";
    private final static String BG_IMAGE = "model/yellow_panel.png";

    private boolean isHidden;

    public BomberManSubScene() {
        super(new AnchorPane(), 375, 250);
        prefWidth(375);
        prefHeight(250);
        BackgroundImage image = new BackgroundImage(new Image(BG_IMAGE, 375, 250, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(new Background(image));

        isHidden = true;

        setLayoutX(640);
        setLayoutY(112.5);
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if(isHidden) {
            transition.setToX(-421.875);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
