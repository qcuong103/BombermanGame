package bomberman.entities;

import bomberman.view.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image image;

    protected int animate;

    protected boolean isVisible = true;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xPoint, int yPoint, Image image) {
        this.x = xPoint * Sprite.SCALED_SIZE;
        this.y = yPoint * Sprite.SCALED_SIZE;
        this.image = image;
        this.animate = Sprite.DEFAULT_SIZE;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }
    public abstract void update();

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }



    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean intersects(Entity s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public boolean checkBounds() {
        for (Entity e : EntityArr.walls) {
            if (this.intersects(e)) return true;
        }

        for (Entity e : EntityArr.bricks) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public boolean checkBomb() {
        for (Entity e : EntityArr.bomberman.bombs) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public boolean checkWall() {
        for (Entity e : EntityArr.walls) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public boolean checkBrick() {
        for (Entity e : EntityArr.bricks) {
            if (this.intersects(e)) return true;
        }
        return false;
    }
}