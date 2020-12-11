package bomberman.entities.blocks;

import bomberman.entities.*;
import bomberman.entities.item.*;
import bomberman.view.Sprite;
import javafx.scene.image.Image;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Brick là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb
 * được đặt gần đó. Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.
 */
public class Brick extends Entity {

    //isBroken check xem Brick đã bị phá hủy hay chưa.
    private boolean isBroken = false;

    //isBrokenDone để random Item 1 lần
    private boolean isBrokenDone = false;

    public Brick(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
    }

    @Override
    public void update() {
        //nếu Brick bị phá hủy, hiện animate của Brick
        if (isBroken) {
            this.animate += Sprite.DEFAULT_SIZE / 10;
            this.setImage(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1
                    , Sprite.brick_exploded2, animate, Sprite.DEFAULT_SIZE).getFxImage());
            //Random Item 1 lần
            if (!isBrokenDone) {
                this.isBrokenDone = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Item item = randomItem();
                        if (item != null) {
                            EntityArr.items.add(item);
                            item.setVisible(true);
                        }
                    }
                }, 400L);
            }
        }
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    public boolean isBroken() {
        return isBroken;
    }

    /**
     * Random Item theo xác suất 80% :)
     * @return Item được chọn.
     */
    private Item randomItem() {
        Random random = new Random();
        switch (random.nextInt(5)) {
            case 1:
                return new BombItem(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
                        , Sprite.powerup_bombs.getFxImage());
            case 2:
                return new FlameItem(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
                        , Sprite.powerup_flames.getFxImage());
            case 3:
                return new SpeedItem(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
                        , Sprite.powerup_speed.getFxImage());
            case 4:
                return new BrickPassItem(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
                        , Sprite.powerup_brickpass.getFxImage());
            default:
                return null;
        }
    }
}
