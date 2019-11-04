package Entity;

import Main.Game;

import java.awt.*;

public class Player extends Entity {
    Image
    private Game game;

    public Player(Game game, double x, double y) {
        super(x, y);
        this.game = game;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
