package bomberman.entities.blocks;

import bomberman.entities.Entity;
import javafx.scene.image.Image;

/**
 * Wall là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được,
 * Bomber và Enemy không thể di chuyển vào đối tượng này
 */
public class Wall extends Entity {

    public Wall(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
    }

    @Override
    public void update() {

    }
}