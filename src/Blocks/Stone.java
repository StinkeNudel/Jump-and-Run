package Blocks;

import Main.Game;

import java.awt.*;

public class Stone extends SolidBlocks {
    //private BufferedImage image = ImageLoader.loadImage("/grass_block.png");

    /**
     * Constructor
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Stone(Game game, double x, double y) {
        super(game, x, y);
    }

    /**
     * tick Class
     */
    public void tick() {
    }

    /**
     * render Class
     *
     * @param g Game Object
     */
    public void render(Graphics g) {
        //g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), 64, 64, null);
        g.setColor(Color.GRAY);
        g.fillRect((int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), 64, 64);
        g.setColor(Color.black);
        g.drawRect((int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), 64, 64);
    }
}
