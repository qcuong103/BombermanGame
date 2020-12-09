package bomberman.view;

import bomberman.entities.CreateMap;
import bomberman.entities.Entity;
import bomberman.entities.EntityArr;
import bomberman.entities.blocks.Brick;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.bomb.Flame;
import bomberman.entities.enemy.Enemy;
import bomberman.sound.Sound;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GameViewManager {
    public static int WIDTH = 50;
    public static int HEIGHT = 15;
    public static int TIME = -1;
    public static int POINTS = 0;
    public static int numOfScreen = -1;
    public boolean isGameOver = false;
//    public boolean isGameOver2 = false;
    public int abc = 0;

    private final Stage stage = new Stage();
    private final GraphicsContext gc;
    private final Canvas canvas;
    public BorderPane root;

    boolean up, down, left, right;

    private static GameViewManager gameViewManager = new GameViewManager();

    public static int level = 1;

    public GameViewManager() {
        // Tao Canvas
        CreateMap.createMapByLevel(1);
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
//        Group root = new Group();
        root = new BorderPane();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefWidth(Sprite.SCALED_SIZE * WIDTH);

        Label timeLabel = new Label("Time: " + TIME);
        timeLabel.setBackground(new Background(new BackgroundFill(Color.BLACK,null, null)));
        timeLabel.setTextFill(Color.WHITE);
        timeLabel.setPrefWidth(Sprite.SCALED_SIZE * WIDTH / 2);
        timeLabel.setAlignment(Pos.CENTER);

        Label pointLabel = new Label("Point: 0");
        pointLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pointLabel.setTextFill(Color.WHITE);
        pointLabel.setPrefWidth(Sprite.SCALED_SIZE * WIDTH / 2);
        pointLabel.setAlignment(Pos.CENTER);

        gridPane.add(timeLabel,0,0);
        gridPane.add(pointLabel, 1, 0);

        root.setTop(gridPane);
        root.setBottom(canvas);
//        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
//        stage.show();

//        Sound.play("soundtrack");
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                if (up) {
                    EntityArr.bomberman.goUp();
                } else if (down) {
                    EntityArr.bomberman.goDown();
                } else if (left) {
                    EntityArr.bomberman.goLeft();
                } else if (right) {
                    EntityArr.bomberman.goRight();
                }
                render();
                update();
//                if (l%1000000000 == 0) {
//                    TIME--;
//                    timeLabel.setText("Time: " + TIME);
//                }
//                TIME -= l%1000000000;

//                if (lOLD != l) {
//                    lOLD = l;
//                    numChange++;
//                    System.out.println(numChange);
//                }
//                if (numChange == 119) {
//                    TIME--;
//                    timeLabel.setText("Time: " + TIME);
//                    numChange = 0;
//                }
//                System.out.println(l%100000000);
                timeLabel.setText("Time: " + TIME);
                pointLabel.setText("Point: " + POINTS);
//                drawScreen(gc);

            }
        };
        timer.start();
//        if (isGameOver2) {
//            timer.stop();
//        }


        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (TIME == 0) timer1.cancel();
//                if (scene.)
//                timeLabel.setText("Time: " + TIME);
                --TIME;

                if (isGameOver) {
//                    drawScreen(gc);
//                    timer.stop();
//                    drawEndGame(canvas.getGraphicsContext2D(), POINTS);
                    timer1.cancel();
                }
            }

//        if (!EntityArr.bomberman.isAlive()) {
////            drawScreen(gc);
//                endGame();
//            }
        }, 1000, 1000);
        timer1.purge();

        stage.setOnCloseRequest(event -> {
            timer1.cancel();
//                stage.close();
        });

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    up = true;
                    break;
                case DOWN:
                    down = true;
                    break;
                case LEFT:
                    left = true;
                    break;
                case RIGHT:
                    right = true;
                    break;
            }
            if (e.getCode() == KeyCode.SPACE) {
                EntityArr.bomberman.putBomb();
            }

            if (e.getCode() == KeyCode.A) {
                EntityArr.enemies.clear();
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP:
                    up = false;
                    break;
                case DOWN:
                    down = false;
                    break;
                case LEFT:
                    left = false;
                    break;
                case RIGHT:
                    right = false;
                    break;
            }
        });
    }

    // update
    public void update() {
        EntityArr.bomberman.update();
        EntityArr.enemies.forEach(Enemy::update);
        EntityArr.bomberman.bombs.forEach(Bomb::update);
        EntityArr.bricks.forEach(Brick::update);
        // update flame
        EntityArr.bomberman.bombs.forEach(g -> g.getfUp().forEach(Flame::update));
        EntityArr.bomberman.bombs.forEach(g -> g.getfDown().forEach(Flame::update));
        EntityArr.bomberman.bombs.forEach(g -> g.getfLeft().forEach(Flame::update));
        EntityArr.bomberman.bombs.forEach(g -> g.getfRight().forEach(Flame::update));
        // Update item
        EntityArr.items.forEach(g -> {
            if (g.isVisible()) g.update();
        });
        EntityArr.portals.forEach(Entity::update);
    }

    // vẽ
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        EntityArr.grasses.forEach(g -> g.render(gc));
        EntityArr.portals.forEach(g -> g.render(gc));
        EntityArr.items.forEach(g -> {
            if (g.isVisible()) g.render(gc);
        });
        EntityArr.walls.forEach(g -> g.render(gc));
        EntityArr.bricks.forEach(g -> g.render(gc));
        EntityArr.bomberman.bombs.forEach(g -> g.flames.forEach(g1 -> g1.render(gc)));
        EntityArr.bomberman.bombs.forEach(g -> g.render(gc));
        EntityArr.enemies.forEach(g -> {
            if (g.isVisible()) g.render(gc);
        });
        EntityArr.bombers.forEach(g -> g.render(gc));

        if (!EntityArr.bomberman.isAlive() || (TIME == 0)) {
//            drawScreen(gc);
            endGame();
//            gameOver = true;
        }
//        drawScreen(gc);

    }

    public void showGame() {
        stage.show();
    }

    public static void addPoints(int points) {
        POINTS += points;
    }

//    public void drawScreen(GraphicsContext gc) {
//        switch (numOfScreen) {
//            case 1:
//                drawEndGame(gc, POINTS);
//                break;
//            case 2:
////                drawEndGame(gc, level);
//                drawNextLevel(gc, level);
//                break;
//        }
////        drawEndGame(gc, POINTS);
//    }
    public void drawEndGame(GraphicsContext gc, int points) {
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        abc++;
        if (abc > 210) {
//            abc = 0;
            Sound.stopsound = true;
            gc.setFill(Color.BLACK);

            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            Font font = new Font("Arial", 40);

            gc.setFont(font);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.setFill(Color.WHITE);
//        drawCenteredString("GAME OVER", canvas.getWidth(), canvas.getHeight(), gc);
            gc.fillText("GAME OVER", canvas.getWidth() / 2, canvas.getHeight() / 2 - 25);

            font = new Font("Arial", 20);
            gc.setFont(font);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.setFill(Color.YELLOW);
//        drawCenteredString("POINTS: " + points, canvas.getWidth(), canvas.getHeight() + 20, gc);
            gc.fillText("POINTS: " + points, canvas.getWidth() / 2, canvas.getHeight() / 2 + 25);

//            BomberManButton button = new BomberManButton("MENU");
//            button.setLayoutX(canvas.getWidth() / 2);
//            button.setLayoutY(canvas.getHeight() + 50);
//            root.getChildren().add(button);
        }

        if (abc > 500) {
//            isGameOver2 = true;
            stage.hide();
            ViewManager.mainStage.show();
            abc = 0;
        }
    }


    public void endGame() {
        numOfScreen = 1;
        isGameOver = true;
//        isGameOver2 = true;
//        drawScreen(gc);
        drawEndGame(gc, POINTS);
    }

    public static GameViewManager getInstance() {
        return gameViewManager;
    }
}