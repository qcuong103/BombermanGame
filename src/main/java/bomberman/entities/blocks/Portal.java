package bomberman.entities.blocks;

import bomberman.entities.Entity;
import javafx.scene.image.Image;

/**
 * Portal là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu
 * tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.
 */
public class Portal extends Entity {

    public Portal(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
    }

    @Override
    public void update() {

    }
}