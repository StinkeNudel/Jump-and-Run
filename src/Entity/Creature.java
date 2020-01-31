package Entity;

import Main.Game;

abstract class Creature extends Entity {

    int health; //Health for a Class

    /**
     * Constructor
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    Creature(Game game, double x, double y) {
        super(game, x, y);
    }
}