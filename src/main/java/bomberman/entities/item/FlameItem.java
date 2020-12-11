package bomberman.entities.item;

import bomberman.entities.EntityArr;
import bomberman.sound.Sound;
import javafx.scene.image.Image;

/**
 * FlameItem
 * Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn)
 */
public class FlameItem extends Item {
    public FlameItem(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
    }

    @Override
    public void update() {
        super.update();
        if (this.used == 1) {
            EntityArr.bomberman.setFlameLength(EntityArr.bomberman.getFlameLength() + 1);
            Sound.play("Item");
        }
    }
}