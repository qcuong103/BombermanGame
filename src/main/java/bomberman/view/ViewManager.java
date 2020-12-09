package bomberman.view;

import bomberman.entities.CreateMap;
import bomberman.model.BomberManButton;
import bomberman.model.BomberManSubScene;
import bomberman.model.InfoLabel;
import bomberman.sound.Sound;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int HEIGHT = 480;
    private static final int WIDTH = 640;
    private final AnchorPane mainPane;
    public static Stage mainStage = new Stage();

    private final static double MENU_BUTTON_START_X = 62.5;
    private final static double MENU_BUTTON_START_Y = 93.75;

    private BomberManSubScene creditsScene;
    private BomberManSubScene helpScene;
    private BomberManSubScene scoreScene;
//    private BomberManSubScene shipChooserSubScene;

    private BomberManSubScene sceneToHide = null;

    List<BomberManButton> menuButtons;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
//        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButton();
        createBackground();
        createLogo();
    }

    private void showSubscene(BomberManSubScene subscene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subscene.moveSubScene();
        sceneToHide = subscene;
    }

    private void createSubScenes() {
        createScoreSubScene();
        createHelpSubScene();
        createCreditsSubScene();
    }

    private void createCreditsSubScene() {
        creditsScene = new BomberManSubScene();
        mainPane.getChildren().add(creditsScene);

        InfoLabel creditsLabel = new InfoLabel("   <<< CREDITS >>>");
        creditsLabel.setLayoutX(68.75);
        creditsLabel.setLayoutY(15.675);
        creditsScene.getPane().getChildren().add(creditsLabel);
    }

    private void createHelpSubScene() {
        helpScene = new BomberManSubScene();
        mainPane.getChildren().add(helpScene);

        InfoLabel helpLabel = new InfoLabel("   <<<< HELP >>>>");
        helpLabel.setLayoutX(68.75);
        helpLabel.setLayoutY(15.675);

        GridPane helpGrid = new GridPane();
        helpGrid.setLayoutX(68.75);
        helpGrid.setLayoutY(15.675);
        helpGrid.setHgap(20);
        helpGrid.setVgap(20);


        helpScene.getPane().getChildren().addAll(helpLabel, helpGrid);
    }

    private void createScoreSubScene() {
        scoreScene = new BomberManSubScene();
        mainPane.getChildren().add(scoreScene);

        InfoLabel scoreLabel = new InfoLabel("<<<< SCORE >>>>");
        scoreLabel.setLayoutX(68.75);
        scoreLabel.setLayoutY(15.675);
        VBox scoreContainer = new VBox();
        scoreContainer.setLayoutX(68.75);
        scoreContainer.setLayoutY(80);

        Label scoreHeading = new Label("     Name			Score   ");
        scoreHeading.setUnderline(true);
        Label score1 = new Label("Astronaught 1		  100");
        Label score2 = new Label("Astronaught 2		  100");
        Label score3 = new Label("Astronaught 3		  100");
        scoreHeading.setFont(Font.font("Verdana",15.675));
        score1.setFont(Font.font("Verdana",15.675));
        score2.setFont(Font.font("Verdana",15.675));
        score3.setFont(Font.font("Verdana",15.675));
        scoreContainer.setBackground(new Background(new BackgroundFill(Color.MEDIUMAQUAMARINE,
                new CornerRadii(20), new Insets(-20,-20,-20,-20))));
        scoreContainer.getChildren().addAll(scoreHeading, score1, score2, score3);
        scoreScene.getPane().getChildren().addAll(scoreLabel, scoreContainer);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButtons(BomberManButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 62.5);
        menuButtons.add(button);
        mainPane.getChildren().add(button);

    }

    private void createButton() {
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton() {
        BomberManButton startButton = new BomberManButton("PLAY");
        addMenuButtons(startButton);
        startButton.setOnAction(event -> {
//                try {
//                    SoundEffects.playSound(new URI(BUTTON_SFX));
//                } catch (URISyntaxException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                showSubscene(shipChooserSubScene);
//                shipChooserSubScene.moveSubScene();
            Sound.play("robotSFX");
            mainStage.hide();
//            GameViewManager gameViewManager = new GameViewManager();
//            gameViewManager.showGame();

            GameViewManager.getInstance().showGame();
            CreateMap.createMapByLevel(1);

        });
    }

    private void createScoresButton() {
        BomberManButton scoresButton = new BomberManButton("SCORES");
        addMenuButtons(scoresButton);
        scoresButton.setOnAction(event -> {
//                try {
//                    SoundEffects.playSound(new URI(BUTTON_SFX));
//                } catch (URISyntaxException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                scoreSubScene.moveSubScene();
            Sound.play("robotSFX");
            showSubscene(scoreScene);
        });
    }

    private void createHelpButton() {
        BomberManButton helpButton = new BomberManButton("HELP");
        addMenuButtons(helpButton);
        helpButton.setOnAction(arg0 -> {
//                try {
//                    SoundEffects.playSound(new URI(BUTTON_SFX));
//                } catch (URISyntaxException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            Sound.play("robotSFX");
            showSubscene(helpScene);
//                helpSubScene.moveSubScene();
        });
    }

    private void createCreditsButton() {
        BomberManButton creditsButton = new BomberManButton("CREDITS");
        addMenuButtons(creditsButton);

        creditsButton.setOnAction(arg0 -> {
//                try {
//                    SoundEffects.playSound(new URI(BUTTON_SFX));
//                } catch (URISyntaxException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            Sound.play("robotSFX");
            showSubscene(creditsScene);
//                creditsSubScene.moveSubScene();
        });
    }

    private void createExitButton() {
        BomberManButton exitButton = new BomberManButton("EXIT");
        addMenuButtons(exitButton);

        exitButton.setOnAction(event -> {
            Sound.play("robotSFX");
            mainStage.close();
//                try {
//                    SoundEffects.playSound(new URI(BUTTON_SFX));
//                } catch (URISyntaxException e) {

//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                Platform.exit();
//                // mainStage.close();
        });
    }

    private void createBackground() {
        Image bgImage = new Image("view/deep_blue.png", 256, 256, false, true);
        BackgroundImage bG = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(bG));
    }

    private void createLogo() {
        Image logoImage = new Image("./view/bomberman_logo.png", 312.5, 98.75, false, false);
        ImageView logo = new ImageView(logoImage);
        logo.setLayoutX(250);
        logo.setLayoutY(15);
        logo.setOnMouseEntered(e -> logo.setEffect(new DropShadow(20, Color.YELLOW)));
        logo.setOnMouseExited(e -> logo.setEffect(null));
        mainPane.getChildren().add(logo);
    }
}