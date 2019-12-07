package Blocks;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grass extends SolidBlocks {
    private BufferedImage image = ImageLoader.loadImage("/grass_block.png");

    /**
     * Constructor
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Grass(Game game, double x, double y) {
        super(game, x, y);
    }

    /**
     * tick Class
     */
    public void tick() {
    }

    /**
     * render Class
     * @param g Game Object
     */
    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), 64, 64, null);
    }
}
