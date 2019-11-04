package Entity;

import GFX.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Letter extends Entity {
    private BufferedImage image; //= ImageLoader.loadImage("");
    public Letter(double x, double y) {
        super(x, y);
    }

    public void tick() {
        move();
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public void move() {

    }
}
