package bomberman.entities.bomb;

import bomberman.view.Sprite;
import javafx.scene.image.Image;

public class FlameH extends Flame {

    public FlameH(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
    }

    @Override
    public void update() {
        super.update();
        this.animate += Sprite.DEFAULT_SIZE / 10;
        this.setImage(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                , Sprite.explosion_horizontal2, animate, Sprite.DEFAULT_SIZE).getFxImage());
    }
}