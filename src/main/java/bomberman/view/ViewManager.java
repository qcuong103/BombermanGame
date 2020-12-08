package bomberman.view;

import bomberman.model.BomberManButton;
import bomberman.model.BomberManSubScene;
import bomberman.model.InfoLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {
    //    private static final int HEIGHT = 768;
//    private static final int WIDTH = 1024;
    private static final int HEIGHT = 480;
    private static final int WIDTH = 640;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

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
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
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

        InfoLabel creditsLabel = new InfoLabel("CREDITS");
        creditsLabel.setLayoutX(68.75);
        creditsLabel.setLayoutY(15.675);
        creditsScene.getPane().getChildren().add(creditsLabel);
    }

    private void createHelpSubScene() {
        helpScene = new BomberManSubScene();
        mainPane.getChildren().add(helpScene);

        InfoLabel helpLabel = new InfoLabel("HELP");
        helpLabel.setLayoutX(68.75);
        helpLabel.setLayoutY(15.675);
        helpScene.getPane().getChildren().add(helpLabel);
    }

    private void createScoreSubScene() {
        scoreScene = new BomberManSubScene();
        mainPane.getChildren().add(scoreScene);

        InfoLabel scoreLabel = new InfoLabel("SCORE");
        scoreLabel.setLayoutX(68.75);
        scoreLabel.setLayoutY(15.675);
        scoreScene.getPane().getChildren().add(scoreLabel);
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
        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
//                try {
//                    SoundEffects.playSound(new URI(BUTTON_SFX));
//                } catch (URISyntaxException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                showSubscene(shipChooserSubScene);
//                shipChooserSubScene.moveSubScene();
                mainStage.hide();
                GameViewManager gameViewManager = new GameViewManager();
                gameViewManager.showGame();
//                new Frame();
//                Frame gameViewManager = new Frame();
//                gameViewManager.createNewGame(mainStage);

            }
        });
    }

    private void createScoresButton() {
        BomberManButton scoresButton = new BomberManButton("SCORES");
        addMenuButtons(scoresButton);
        scoresButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
//                try {
//                    SoundEffects.playSound(new URI(BUTTON_SFX));
//                } catch (URISyntaxException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                scoreSubScene.moveSubScene();
                showSubscene(scoreScene);
            }
        });
    }

    private void createHelpButton() {
        BomberManButton helpButton = new BomberManButton("HELP");
        addMenuButtons(helpButton);
        helpButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
//                try {
//                    SoundEffects.playSound(new URI(BUTTON_SFX));
//                } catch (URISyntaxException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                showSubscene(helpScene);
//                helpSubScene.moveSubScene();
            }
        });
    }

    private void createCreditsButton() {
        BomberManButton creditsButton = new BomberManButton("CREDITS");
        addMenuButtons(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
//                try {
//                    SoundEffects.playSound(new URI(BUTTON_SFX));
//                } catch (URISyntaxException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                showSubscene(creditsScene);
//                creditsSubScene.moveSubScene();
            }
        });
    }

    private void createExitButton() {
        BomberManButton exitButton = new BomberManButton("EXIT");
        addMenuButtons(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
//                try {
//                    SoundEffects.playSound(new URI(BUTTON_SFX));
//                } catch (URISyntaxException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                Platform.exit();
//                // mainStage.close();
            }
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