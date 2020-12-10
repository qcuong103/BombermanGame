package bomberman.entities.bomb;

import bomberman.entities.Entity;
import bomberman.entities.EntityArr;
import bomberman.entities.blocks.Brick;
import bomberman.entities.enemy.Enemy;
import bomberman.sound.Sound;
import javafx.scene.image.Image;

public abstract class Flame extends Entity {
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        checkEnemy();
        checkBomb();
        checkBomber();
    }

    @Override
    public boolean checkWall() {
        for (Entity w : EntityArr.walls) {
            if (this.getX() == w.getX() && this.getY() == w.getY()) {
                this.setVisible(false);
                return true;
            }
        }
        return false;
    }

    public boolean checkBrick() {
        for (Brick b : EntityArr.bricks) {
            if (this.getX() == b.getX() && this.getY() == b.getY()) {
                this.setVisible(false);
                b.setBroken(true);
                return true;
            }
        }
        return false;
    }

    protected void checkEnemy() {
        for (Enemy e : EntityArr.enemies) {
            if (this.intersects(e)) {
                e.setAlive(false);
            }
        }
    }

    @Override
    public boolean checkBomb() {
        for (Bomb bomb : EntityArr.bomberman.bombs) {
            if (this.intersects(bomb)) {
                bomb.setExploded(true);
                return true;
            }
        }
        return false;
    }

    protected void checkBomber() {
        if (EntityArr.bomberman.isFlamePass()) return;
        if (this.intersects(EntityArr.bomberman))
            EntityArr.bomberman.setAlive(false);
    }
}