package bomberman.entities.bomb;

import bomberman.view.Sprite;
import javafx.scene.image.Image;

public class FlameV extends Flame {
    public FlameV(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        super.update();
        this.animate += Sprite.DEFAULT_SIZE / 10;
        this.setImg(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1
                , Sprite.explosion_vertical2, animate, Sprite.DEFAULT_SIZE).getFxImage());
    }
}