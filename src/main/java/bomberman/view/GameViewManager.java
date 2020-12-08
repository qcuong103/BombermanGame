package bomberman.view;

import bomberman.entities.CreateMap;
import bomberman.entities.EntityArr;
import bomberman.entities.blocks.Brick;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.enemy.Enemy;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GameViewManager {
    public static int WIDTH = 50;
    public static int HEIGHT = 15;
    public static int TIME = 200;
    public static int POINTS = 0;

    private Stage stage = new Stage();
    private GraphicsContext gc;
    private Canvas canvas;

    boolean up, down, left, right;

    public static boolean gameOver = false;

    public static int level = 1;

    public GameViewManager() {
        // Tao Canvas
        CreateMap.createMapByLevel(1);
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
//        Group root = new Group();
        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefWidth(Sprite.SCALED_SIZE * WIDTH);
        Label timeLabel = new Label("Time: " + TIME);
        timeLabel.setPrefWidth(Sprite.SCALED_SIZE * WIDTH / 2);
        timeLabel.setAlignment(Pos.CENTER);
        Label pointLabel = new Label("Point: 0");
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
        stage.show();

//        Sound.play("soundtrack");
        AnimationTimer timer = new AnimationTimer() {
            long lOLD = 0;
            int numChange = 0;

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
            }
        };
        timer.start();

        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (TIME == 1) timer1.cancel();
//                timeLabel.setText("Time: " + TIME);
                --TIME;
            }
        }, 1000, 1000);

        /**
         * Hanh dong cua bomber
         */
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
        EntityArr.bomberman.bombs.forEach(g -> g.getfUp().forEach(g1 -> g1.update()));
        EntityArr.bomberman.bombs.forEach(g -> g.getfDown().forEach(g1 -> g1.update()));
        EntityArr.bomberman.bombs.forEach(g -> g.getfLeft().forEach(g1 -> g1.update()));
        EntityArr.bomberman.bombs.forEach(g -> g.getfRight().forEach(g1 -> g1.update()));
        // Update item
        EntityArr.items.forEach(g -> {
            if (g.isVisible()) g.update();
        });
        EntityArr.portals.forEach(g -> g.update());
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

    }

    public void showGame() {
        stage.show();
    }

    public static void addPoints(int points) {
        POINTS += points;
    }
}