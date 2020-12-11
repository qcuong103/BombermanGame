package bomberman.entities.enemy;

import bomberman.view.Sprite;
import javafx.scene.image.Image;

/**
 * Balloom là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định.
 */
public class Balloom extends Enemy {

    public Balloom(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
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
                this.image = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2
                        , Sprite.balloom_right3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.image = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2
                        , Sprite.balloom_left3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            }
        } else {
            this.image = Sprite.balloom_dead.getFxImage();
        }
    }

    @Override
    protected void go() {
        if (isAlive()) {
            if (this.getSpeedX() == 0) {
                this.y += this.getSpeedY();
                if (checkBounds() || checkBomb() || getY() % Sprite.SCALED_SIZE == 0) {
                    if (getY() % Sprite.SCALED_SIZE != 0) {
                        this.y -= this.getSpeedY();
                    }
                    this.randomVector();
                }
            } else {
                this.x += this.getSpeedX();
                if (checkBounds() || checkBomb() || getX() % Sprite.SCALED_SIZE == 0) {
                    if (getX() % Sprite.SCALED_SIZE != 0) {
                        this.x -= this.getSpeedX();
                    }
                    this.randomVector();
                }
            }
        }
    }
}