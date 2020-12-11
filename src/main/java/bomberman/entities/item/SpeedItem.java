package bomberman.entities.item;

import bomberman.entities.EntityArr;
import bomberman.sound.Sound;
import bomberman.view.Sprite;
import javafx.scene.image.Image;

/**
 * SpeedItem
 * Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp
 */
public class SpeedItem extends Item {

    public SpeedItem(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
    }

    @Override
    public void update() {
        super.update();
        if (this.used == 1) {
            EntityArr.bomberman.setSpeed(EntityArr.bomberman.getSpeed() + Sprite.DEFAULT_SIZE / 12);
            Sound.play("Item");
        }
    }
}