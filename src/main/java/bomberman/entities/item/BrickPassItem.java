package bomberman.entities.item;

import bomberman.entities.EntityArr;
import bomberman.sound.Sound;
import javafx.scene.image.Image;

/**
 * BrickPassItem
 * Item này giúp người chơi đi xuyên Brick.
 */
public class BrickPassItem extends Item {
    public BrickPassItem(int xPoint, int yPoint, Image img) {
        super(xPoint, yPoint, img);
    }

    @Override
    public void update() {
        super.update();
        if (this.used == 1) {
            EntityArr.bomberman.setWallPass(true);
            Sound.play("Item");
        }
    }
}