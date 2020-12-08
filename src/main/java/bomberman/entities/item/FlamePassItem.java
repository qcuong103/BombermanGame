package bomberman.entities.item;

import bomberman.entities.EntityArr;
import bomberman.sound.Sound;
import javafx.scene.image.Image;

public class FlamePassItem extends Item {
    public FlamePassItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        super.update();
        if (this.used == 1) {
            EntityArr.bomberman.setFlamePass(true);
            Sound.play("Item");
        }
    }
}