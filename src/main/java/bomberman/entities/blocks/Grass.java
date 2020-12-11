package bomberman.entities.blocks;

import bomberman.entities.Entity;
import javafx.scene.image.Image;

/**
 * Grass là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua,
 * và cho phép đặt Bomb lên vị trí của nó
 */
public class Grass extends Entity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}
