package bomberman.entities.enemy;

import bomberman.entities.EntityArr;
import bomberman.view.Sprite;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * Oneal có tốc độ di chuyển thay đổi, lúc nhanh, lúc chậm
 * và di chuyển "thông minh" hơn so với Balloom (biết đuổi theo Bomber)
 */
public class Oneal extends Enemy {

    public Oneal(int xPoint, int yPoint, Image img) {
        super(xPoint, yPoint, img);
    }

    @Override
    public void update() {
        super.update();
        go();
        choseSprite();
        if (!isAlive()) {
            super.enemyDead();
        }
    }

    @Override
    protected void choseSprite() {
        if (isAlive()) {
            if (this.getSpeedX() > 0) {
                this.image = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2
                        , Sprite.oneal_right3, this.animate, 60).getFxImage();
            } else {
                this.image = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2
                        , Sprite.oneal_left3, this.animate, 60).getFxImage();
            }
        } else {
            this.image = Sprite.oneal_dead.getFxImage();
        }
    }

    @Override
    protected void go() {
        if (isAlive()) {
            int diffX = Math.abs(getX() - EntityArr.bomberman.getX());
            int diffY = Math.abs(getY() - EntityArr.bomberman.getY());
            if (this.getSpeedX() == 0) {
                this.y += this.getSpeedY();
                if (diffX <= Sprite.SCALED_SIZE * 3 && diffY <= Sprite.SCALED_SIZE * 3) {
                    this.setSpeed(1);
                    findBomber();
                    if (checkBounds() || checkBomb()) {
                        this.y -= this.getSpeedY();
                        findBomber();
                    }
                } else
                if (checkBounds() || checkBomb() || getY() % Sprite.SCALED_SIZE == 0) {
                    if (checkBounds() || checkBomb()) {
                        this.y -= this.getSpeedY();
                    }
                    this.randomVector();
                    this.randomSpeed();
                }
            } else {
                this.x += this.getSpeedX();
                if (diffX <= Sprite.SCALED_SIZE * 3 && diffY <= Sprite.SCALED_SIZE * 3) {
                    this.setSpeed(1);
                    findBomber();
                    if (checkBounds() || checkBomb()) {
                        this.x -= this.getSpeedX();
                        findBomber();
                    }
                } else
                if (checkBounds() || checkBomb() || getX() % Sprite.SCALED_SIZE == 0) {
                    if (checkBounds() || checkBomb()) {
                        this.x -= this.getSpeedX();
                    }
                    this.randomVector();
                    this.randomSpeed();
                }
            }
        } else {
            this.enemyDead();

        }
    }

    protected void randomSpeed() {
        Random random = new Random();
        int num = random.nextInt();
        if (num % 100 <= 70) this.setSpeed(1);
        else this.setSpeed(2);
    }

    protected void findBomber() {
        if (getY() == EntityArr.bomberman.getY()) {
            choseVector(diffRaw());
        } else if (getX() == EntityArr.bomberman.getX()) {
            choseVector(diffCol());
        }
    }
}