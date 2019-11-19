package Blocks;

import GFX.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grass extends SolidBlocks {
    private BufferedImage image = ImageLoader.loadImage("/grass_block.png");


    public Grass(double x, double y) {
        super(x, y);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, 64, 64, null);
    }
}
