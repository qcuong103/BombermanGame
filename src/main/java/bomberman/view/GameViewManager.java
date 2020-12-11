package bomberman.view;

import bomberman.entities.*;
import bomberman.entities.blocks.Brick;
import bomberman.entities.bomb.*;
import bomberman.entities.enemy.Enemy;
import bomberman.sound.Sound;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GameViewManager {
    public static int WIDTH = 50;
    public static int HEIGHT = 15;
    public static int TIME = -1;
    public static int POINTS = 0;
    public boolean isGameOver;
//    public boolean isGameOver2 = false;
    public int abc;
    private int timeGameOver;

    private final Stage stage = new Stage();
    private final GraphicsContext gc;
    private final Canvas canvas;
    public BorderPane root;

    boolean up, down, left, right;

    private static final GameViewManager gameViewManager = new GameViewManager();

    public static int level = 1;

    public GameViewManager() {
        // Tao Canvas
        CreateMap.createMapByLevel(1);
        isGameOver = false;
        timeGameOver = 0;
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

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

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);

        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                if(!isGameOver) {
                    if (up) {
                        EntityArr.bomberman.goUp();
                    } else if (down) {
                        EntityArr.bomberman.goDown();
                    } else if (left) {
                        EntityArr.bomberman.goLeft();
                    } else if (right) {
                        EntityArr.bomberman.goRight();
                    }
                }
                render();
                update();
                timeLabel.setText("Time: " + TIME);
                pointLabel.setText("Point: " + POINTS);
            }
        };

        timer.start();

        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if ((TIME == 0) || isGameOver) timer1.cancel();
                --TIME;
                if (abc>211) {
                    timer.stop();
                }
            }
        }, 1000L, 1000);


        stage.setOnCloseRequest(event -> {
            timer1.cancel();
        });

        timer1.purge();
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

            //FOR admin :)
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
            endGame();
        }
    }

    public void showGame() {
        abc=0;
        isGameOver = false;
        stage.show();
    }

    public static void addPoints(int points) {
        POINTS += points;
    }

    public void drawEndGame(GraphicsContext gc, int points) {

        abc++;
        if (abc > 70) {
            Sound.stopSound = true;
            Sound.isStopSound = true;

            timeGameOver = 1;
            if (timeGameOver == 1) {
                timeGameOver++;
                Sound.play("endgame3");
            }

            gc.setFill(Color.BLACK);

            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            Font font = new Font("Arial", 40);

            gc.setFont(font);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.setFill(Color.WHITE);
            gc.fillText("GAME OVER", canvas.getWidth() / 2, canvas.getHeight() / 2 - 25);

            font = new Font("Arial", 20);
            gc.setFont(font);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.setFill(Color.YELLOW);
            gc.fillText("POINTS: " + points, canvas.getWidth() / 2, canvas.getHeight() / 2 + 25);
        }
    }


    public void endGame() {
        isGameOver = true;
        drawEndGame(gc, POINTS);

    }

    public static GameViewManager getInstance() {
        return gameViewManager;
    }
}