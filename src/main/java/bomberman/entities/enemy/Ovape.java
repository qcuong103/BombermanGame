package bomberman.entities.enemy;

import bomberman.view.Sprite;
import javafx.scene.image.Image;

public class Ovape extends Enemy {
    public Ovape(int xPoint, int yPoint, Image img) {
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
                this.image = Sprite.movingSprite(Sprite.ovape_right1, Sprite.ovape_right2
                        , Sprite.ovape_right3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.image = Sprite.movingSprite(Sprite.ovape_left1, Sprite.ovape_left2
                        , Sprite.ovape_left3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            }
        } else {
            this.image = Sprite.ovape_dead.getFxImage();
        }
    }

    @Override
    protected void go() {
        if (isAlive()) {
            if (this.getSpeedX() == 0) {
                this.y += this.getSpeedY();
                if (checkWall() || checkBomb() || getY() % Sprite.SCALED_SIZE == 0) {
                    if (getY() % Sprite.SCALED_SIZE != 0) {
                        this.y -= this.getSpeedY();
                    }
                    this.randomVector();
                }
            } else {
                this.x += this.getSpeedX();
                if (checkWall() || checkBomb() || getX() % Sprite.SCALED_SIZE == 0) {
                    if (getX() % Sprite.SCALED_SIZE != 0) {
                        this.x -= this.getSpeedX();
                    }
                    this.randomVector();
                }
            }
        } else {
            this.enemyDead();

        }
    }
}