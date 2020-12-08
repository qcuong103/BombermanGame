package bomberman.entities.enemy;

import bomberman.entities.EntityArr;
import bomberman.view.Sprite;
import javafx.scene.image.Image;

public class Minvo extends Enemy {
    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.setSpeed(2);
    }

    @Override
    public void update() {
        super.update();
        go();
        choseSprite();
    }

    @Override
    protected void choseSprite() {
        if (isAlive()) {
            if (this.getSpeedX() > 0) {
                this.img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2
                        , Sprite.minvo_right3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2
                        , Sprite.minvo_left3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            }
        } else {
            this.img = Sprite.minvo_dead.getFxImage();
        }
    }

    @Override
    protected void go() {
        if (isAlive()) {
            int diffX = Math.abs(getX() - EntityArr.bomberman.getX());
            int diffY = Math.abs(getY() - EntityArr.bomberman.getY());
            if (this.getSpeedX() == 0) {
                this.y += this.getSpeedY();
                if (diffX <= Sprite.SCALED_SIZE * 7 && diffY <= Sprite.SCALED_SIZE * 7 || getY() % Sprite.SCALED_SIZE == 0) {
                    if (checkBounds() || checkBomb()) {
                        this.y -= this.getSpeedY();
                        if (getY() % Sprite.SCALED_SIZE == 0 && getY() == EntityArr.bomberman.getY()) {
                            choseVector(diffRaw());
                        } else {
                            randomVector();
                        }
                    }
                    if (getY() % Sprite.SCALED_SIZE == 0) {
                        if (getY() == EntityArr.bomberman.getY())
                            choseVector(diffRaw());
                        else
                            choseVector(calculateVector());
                    }
                } else
                if (checkBounds() || checkBomb() || getY() % Sprite.SCALED_SIZE == 0) {
                    if (checkBounds() || checkBomb()) {
                        this.y -= this.getSpeedY();
                    }
                    this.randomVector();
                }
            } else {
                this.x += this.getSpeedX();
                if (diffX <= Sprite.SCALED_SIZE * 7 && diffY <= Sprite.SCALED_SIZE * 7 && getX() % Sprite.SCALED_SIZE == 0) {
                    if (checkBounds() || checkBomb()) {
                        this.x -= this.getSpeedX();
                        if (getX() == EntityArr.bomberman.getX()) {
                            choseVector(diffCol());
                        } else {
                            randomVector();
                        }
                    }
                    if (getX() % Sprite.SCALED_SIZE == 0) {
                        if (getX() == EntityArr.bomberman.getX())
                            choseVector(diffCol());
                        else
                            choseVector(calculateVector());
                    }
                } else
                if (checkBounds() || checkBomb() || getX() % Sprite.SCALED_SIZE == 0) {
                    if (checkBounds() || checkBomb()) {
                        this.x -= this.getSpeedX();
                    }
                    this.randomVector();
                }
            }
            choseSprite();
        } else {
            this.enemyDead();

        }
    }

    protected int calculateVector() {
        if(getVector() == 1) {
            int v = diffRaw();
            if(v != -1)
                return v;
            else
                return diffCol();

        } else {
            int h = diffCol();

            if(h != -1)
                return h;
            else
                return diffRaw();
        }
    }
}