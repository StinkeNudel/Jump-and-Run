package Blocks;

import Entity.Entity;
import Main.Game;

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
}
