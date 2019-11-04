package Entity;

import Main.Game;

public class Player extends Entity {
    private Game game;

    public Player(Game game, double x, double y) {
        super(x, y);
        this.game = game;
    }
}
