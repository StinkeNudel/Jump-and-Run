package Blocks;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dirt extends SolidBlocks {
    private BufferedImage image = ImageLoader.loadImage("/dirt.png");


    public Dirt(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), 64, 64, null);
    }
}
