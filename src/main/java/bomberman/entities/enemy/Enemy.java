package bomberman.entities.enemy;

import bomberman.entities.Entity;
import bomberman.entities.EntityArr;
import bomberman.sound.Sound;
import bomberman.view.Sprite;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * Enemy là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level.
 * Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy.
 * Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
 */
public abstract class Enemy extends Entity {

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int UP = 3;

    private int speed = 1;


    protected int speedX = this.speed;

    protected int speedY = 0;

    private boolean isAlive = true;

    public Enemy(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
    }

    @Override
    public void update() {
        this.animate += Sprite.DEFAULT_SIZE / 10;
        checkBoundBomber();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void enemyDead() {
//        GameViewManager.POINTS += 1;
        setImage(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3
                , this.animate, 20).getFxImage());
    }

    protected void randomVector() {
        Random random = new Random();
        int num = random.nextInt(4);
        choseVector(num);
    }

    public int getSpeedX() {
        return speedX;
    }



    public int getSpeedY() {
        return speedY;
    }


    protected void checkBoundBomber() {
        if (EntityArr.bomberman.intersects(this)) {
            EntityArr.bomberman.setAlive(false);
        }
    }

    protected abstract void choseSprite();

    protected void go() {
    }

    protected void choseVector(int num) {
        switch (num) {
            case RIGHT:
                this.speedX = this.getSpeed();
                this.speedY = 0;
                break;
            case LEFT:
                this.speedX = this.getSpeed() * -1;
                this.speedY = 0;
                break;
            case DOWN:
                this.speedY = this.getSpeed();
                this.speedX = 0;
                break;
            case UP:
                this.speedY = this.getSpeed() * -1;
                this.speedX = 0;
                break;
        }
    }

    protected int diffRaw() {
        if (getX() > EntityArr.bomberman.getX()) return Enemy.LEFT;
        else if (getX() < EntityArr.bomberman.getX()) return Enemy.RIGHT;
        return -1;
    }

    protected int diffCol() {
        if (getY() > EntityArr.bomberman.getX()) return Enemy.UP;
        else if (getY() < EntityArr.bomberman.getY()) return Enemy.DOWN;
        return -1;
    }

    protected int getVector() {
        if (speedY == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}