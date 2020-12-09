package bomberman;

import bomberman.sound.Sound;
import bomberman.view.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class BombermanGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        ViewManager manager = new ViewManager();
        primaryStage = manager.getMainStage();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Sound.play("soundtrack");
        launch(args);
    }
}