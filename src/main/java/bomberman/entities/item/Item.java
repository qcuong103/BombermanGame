package bomberman.entities.item;

import bomberman.entities.Entity;
import bomberman.entities.EntityArr;
import javafx.scene.image.Image;

public abstract class Item extends Entity {

    protected int used = 0;

    public Item(int xPoint, int yPoint, Image image) {
        super(xPoint, yPoint, image);
    }

    @Override
    public void update() {
        if (this.isVisible() && this.checkBoundBomber()) {
            this.setVisible(false);
            this.used++;
        }
    }

    protected boolean checkBoundBomber() {
        return this.intersects(EntityArr.bomberman);
    }
}