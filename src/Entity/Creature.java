package Entity;

import Main.Game;

public abstract class Creature extends Entity {

    public int health; //Health for a Class

    /**
     * Constructor
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Creature(Game game, double x, double y) {
        super(game, x, y);
    }
}