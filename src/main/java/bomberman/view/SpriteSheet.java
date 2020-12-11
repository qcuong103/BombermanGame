package bomberman.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {

    private final String _path;
    public final int SIZE;
    public int[] _pixels;

    public static SpriteSheet tiles = new SpriteSheet("/view/textures/classic.png", 256);

    public SpriteSheet(String path, int size) {
        _path = path;
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            URL a = SpriteSheet.class.getResource(_path);
            BufferedImage image = ImageIO.read(a);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, _pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
