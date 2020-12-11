package bomberman.entities.item;

import bomberman.entities.EntityArr;
import bomberman.sound.Sound;
import javafx.scene.image.Image;

/**
 * BombItem
 * Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt,
 *                  Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb.
 * Item này giúp tăng số lượng Bomb có thể đặt thêm một.
 */
public class BombItem extends Item {

    public BombItem(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
    }

    @Override
    public void update() {
        super.update();
        if (this.used == 1) {
            EntityArr.bombers.forEach(g -> g.setNumBombs(g.getNumBombs() + 1));
            Sound.play("Item");
        }
    }
}