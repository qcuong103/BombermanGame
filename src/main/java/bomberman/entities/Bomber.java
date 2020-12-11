package bomberman.entities;

import bomberman.entities.blocks.Brick;
import bomberman.entities.bomb.Bomb;
import bomberman.sound.Sound;
import bomberman.view.GameViewManager;
import bomberman.view.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Bomber là nhân vật chính của trò chơi.
 * Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi.
 */
public class Bomber extends bomberman.entities.Entity {
    private int numBombs = 1;

    private int flameLength = 1;

    private int speed = Sprite.DEFAULT_SIZE / 8;

    private boolean isAlive = true;

    private boolean wallPass = false;

    private boolean flamePass = false;

    private int time = 0;

    private int timeDead = 0;

    public List<Bomb> bombs = new ArrayList<>();

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        this.animate += Sprite.DEFAULT_SIZE / 10;
        if (!isAlive()) {
            bomberDead();
        }
        if (checkPortal()) {
            if (this.time == 0) {
                this.time++;
                GameViewManager.level++;
                CreateMap.createMapByLevel(GameViewManager.level);
//                GameViewManager.numOfScreen = 2;
//                GameViewManager.TIME = 200;
            }
        }
    }

    public void goRight() {
        for (int i = 1; i <= this.speed; ++i) {
            this.x += 1;
            if (checkBrick() || checkWall() || checkBomb()) {
                this.x -= 1;
                if (this.y % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                    this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                } else if (this.y % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                    this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
                }
                break;
            }
        }
        setImage(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                Sprite.player_right_2, this.animate, Sprite.DEFAULT_SIZE).getFxImage());
    }

    public void goLeft() {
        for (int i = 1; i <= this.speed; ++i) {
            this.x -= 1;
            if (checkBrick() || checkWall() || checkBomb()) {
                this.x += 1;
                if (this.y % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                    this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                } else if (this.y % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                    this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
                }
                break;
            }
        }
        setImage(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                Sprite.player_left_2, this.animate, Sprite.DEFAULT_SIZE).getFxImage());
    }

    public void goUp() {
        for (int i = 1; i <= this.speed; ++i) {
            this.y -= 1;
            if (checkBrick() || checkWall() || checkBomb()) {
                this.y += 1;
                if (this.x % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                    this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                } else if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                    this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
                }
                break;
            }
        }
        setImage(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                Sprite.player_up_2, this.animate, Sprite.DEFAULT_SIZE).getFxImage());
    }

    public void goDown() {
        for (int i = 1; i <= this.speed; ++i) {
            this.y += 1;
            if (checkBrick() || checkWall() || checkBomb()) {
                this.y -= 1;
                if (this.x % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                    this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                } else if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                    this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
                }
                break;
            }
        }
        setImage(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                Sprite.player_down_2, this.animate, Sprite.DEFAULT_SIZE).getFxImage());
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this. speed= speed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public void setNumBombs(int numBombs) {
        this.numBombs = numBombs;
    }

    public int getFlameLength() {
        return flameLength;
    }

    public void setFlameLength(int flameLength) {
        this.flameLength = flameLength;
    }

    public boolean isBombPass() {
        return false;
    }



    public void setWallPass(boolean wallPass) {
        this.wallPass = wallPass;
    }

    public boolean isFlamePass() {
        return flamePass;
    }

    public void setFlamePass(boolean flamePass) {
        this.flamePass = flamePass;
    }

    private boolean duplicateBomb(Bomb bomb) {
        for (Bomb b : this.bombs) {
            if (b.getX() == bomb.getX() && b.getY() == bomb.getY()) {
                return true;
            }
        }
        for (Brick b : EntityArr.bricks) {
            if (b.getX() == bomb.getX() && b.getY() == bomb.getY()) {
                return true;
            }
        }

        return false;
    }

    public void putBomb() {
        int xBomb, yBomb;
        if (getX() % Sprite.SCALED_SIZE > Sprite.SCALED_SIZE / 3) {
            xBomb = (getX() / Sprite.SCALED_SIZE)  + 1;
        } else {
            xBomb = (getX() / Sprite.SCALED_SIZE);
        }
        if (getY() % Sprite.SCALED_SIZE > Sprite.SCALED_SIZE / 3) {
            yBomb = (getY() / Sprite.SCALED_SIZE) + 1;
        } else {
            yBomb = (getY() / Sprite.SCALED_SIZE);
        }
        Bomb bomb = new Bomb(xBomb, yBomb, Sprite.bomb.getFxImage());

        if (!this.duplicateBomb(bomb)
                && getNumBombs() >= this.bombs.size() + 1) {
            Sound.play("BOM_SET");
            this.bombs.add(bomb);
        }
    }

    @Override
    public boolean checkBomb() {
        if (isBombPass()) return false;
        for (Bomb e : EntityArr.bomberman.bombs) {
            double diffX = this.getX() - e.getX();
            double diffY = this.getY() - e.getY();
            if (!(diffX > -32 && diffX < 32 && diffY > -32 && diffY < 32)) {
                e.allowedToPassThru = false;
            }
            if (e.allowedToPassThru) return false;
            if (this.intersects(e)) return true;
        }
        return false;
    }


    private void bomberDead() {
//        Sound.play("endgame3");
        timeDead = 1;
        if (timeDead == 1) {
            timeDead++;
            Sound.play("AA126_11");
        }
        setImage(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3
                , this.animate, 120).getFxImage());
    }

    @Override
    public boolean checkBrick() {
        if (this.wallPass) return false;
        return super.checkBrick();
    }

    public boolean checkPortal() {
        for (bomberman.entities.Entity portal : EntityArr.portals) {
            if (EntityArr.enemies.size() != 0) break;
            if (this.intersects(portal)) {
                return true;
            }
        }
        return false;
    }
}