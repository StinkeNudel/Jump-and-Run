package Blocks;

import Entity.Entity;
import Main.Game;

import java.awt.*;

public abstract class SolidBlocks extends Entity {
    public int width = game.blockSize;
    public int height = game.blockSize;

    /**
     * Constructor
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public SolidBlocks(Game game, double x, double y) {
        super(game, x, y);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
