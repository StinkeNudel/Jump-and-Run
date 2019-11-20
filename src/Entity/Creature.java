package Entity;

import Main.Game;

public abstract class Creature extends Entity {
    int health;

    public Creature(Game game, double x, double y) {
        super(game, x, y);
        int health = 1;
    }
}
