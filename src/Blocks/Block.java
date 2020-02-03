package Blocks;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends SolidBlocks {
    private BufferedImage stone = ImageLoader.loadImage("/Blocks/StoneBlock.png");
    private BufferedImage dirt = ImageLoader.loadImage("/Blocks/DirtBlock.png");
    private BufferedImage grass = ImageLoader.loadImage("/Blocks/GrassBlock.png");
    private BufferedImage leave = ImageLoader.loadImage("/Blocks/LeaveBlock.png");
    private BufferedImage wood = ImageLoader.loadImage("/Blocks/WoodBlock.png");
    private BufferedImage platform = ImageLoader.loadImage("/Blocks/PlatformBlock.png");

    private String type;

    /**
     * Constructor
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Block(Game game, double x, double y, String type) {
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
        if (type.equals("G"))
            g.drawImage(grass, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        if (type.equals("D"))
            g.drawImage(dirt, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        if (type.equals("S"))
            g.drawImage(stone, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        if (type.equals("L"))
            g.drawImage(leave, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        if (type.equals("W"))
            g.drawImage(wood, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        if (type.equals("P"))
            g.drawImage(platform, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }
}
