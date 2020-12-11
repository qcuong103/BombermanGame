package bomberman.entities.bomb;

import bomberman.entities.Entity;
import bomberman.entities.EntityArr;
import bomberman.sound.Sound;
import bomberman.view.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Bomb là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass.
 * Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb.
 * Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình,
 *          Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh.
 * Sau khi kích hoạt 2s, Bomb sẽ tự nổ, các đối tượng Flame được tạo ra.
 */
public class Bomb extends Entity {

    //flameLength là độ dài của flame.
    private int flameLength;

    //flameLeft, flameRight, flameUp, flameDown là các list Flame có độ dài bằng flameLength
    private final List<Flame> flameLeft = new ArrayList<>();
    private final List<Flame> flameRight = new ArrayList<>();
    private final List<Flame> flameUp = new ArrayList<>();
    private final List<Flame> flameDown = new ArrayList<>();

    public List<Flame> flames = new ArrayList<>();

    //isExploded check bom có nổ không.
    private boolean isExploded = false;

    //timerEx để chạy 1 lần hiện flame và phát âm thanh
    public int timerEx = 0;

    //allowedToPassThru cho phép đi xuyên qua Bomb 1 lần khi đặt
    public boolean allowedToPassThru = true;

    public Bomb(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
    }

    @Override
    public void update() {
        animate += Sprite.DEFAULT_SIZE / 10;
        flameLength = EntityArr.bomberman.getFlameLength();
        if (this.isExploded()) {
            this.setImage(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1
                    , Sprite.bomb_exploded2, animate, Sprite.DEFAULT_SIZE).getFxImage());
            if (this.timerEx == 1) {
                this.timerEx++;
                this.addFlame();
                Sound.play("BOM_11_M");
            }
        } else {
            if (this.timerEx == 0) {
                this.timerEx++;
                setTimeExploded();
            }
            this.setImage(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1
                    , Sprite.bomb_2, animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
    }

    public void addFlame() {
        Flame flame;
        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameV(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE + 1 + i
                    , Sprite.explosion_vertical.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                flameDown.add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameV(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE - 1 - i
                    , Sprite.explosion_vertical.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                flameUp.add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameH(getX() / Sprite.SCALED_SIZE + i + 1, getY() / Sprite.SCALED_SIZE
                    , Sprite.explosion_horizontal.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                flameRight  .add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameH(getX() / Sprite.SCALED_SIZE - i - 1, getY() / Sprite.SCALED_SIZE
                    , Sprite.explosion_horizontal.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                flameLeft.add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }

    public List<Flame> getfLeft() {
        return flameLeft;
    }

    public List<Flame> getfRight() {
        return flameRight;
    }

    public List<Flame> getfUp() {
        return flameUp;
    }

    public List<Flame> getfDown() {
        return flameDown;
    }

    public void setTimeExploded() {
        Bomb bomb = this;
        TimerTask bombEx = new TimerTask() {
            @Override
            public void run() {
                bomb.setExploded(true);
            }
        };
        if (!this.isExploded()) {
            Timer timerEx = new Timer();
            timerEx.schedule(bombEx, 2000);
        }
        TimerTask removeFlame = new TimerTask() {
            @Override
            public void run() {
                EntityArr.removeBrick();
                EntityArr.removeBomb();
                EntityArr.removeEnemy();
            }
        };
        Timer timer = new Timer();
        timer.schedule(removeFlame, 2500L);
    }
}