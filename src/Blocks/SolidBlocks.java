package Blocks;

import Entity.Entity;
import Main.Game;

import java.awt.*;

public abstract class SolidBlocks extends Entity {

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

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 64, 64);
    }
}
