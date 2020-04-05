package Blocks;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackBlock extends SolidBlocks {
    private BufferedImage stone = ImageLoader.loadImage("/Blocks/StoneBackground.png");
    private BufferedImage dirt = ImageLoader.loadImage("/Blocks/DirtBackground.png");
    private BufferedImage leave = ImageLoader.loadImage("/Blocks/LeaveBackground.png");

    public String type;

    /**
     * Constructor
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public BackBlock(Game game, double x, double y, String type) {
        super(game, x, y);
        this.type = type;
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
        if (type.equals("d"))
            g.drawImage(dirt, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        if (type.equals("s"))
            g.drawImage(stone, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        if (type.equals("l"))
            g.drawImage(leave, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }
}
