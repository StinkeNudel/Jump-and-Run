package Entity;

import Main.Game;

import java.awt.*;

public abstract class Entity {

    public double x, y;
    protected Game game;

    public Entity(Game game, double x, double y) {
        this.x = x;
        this.y = y;
        this.game = game;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
