package Entity;

import Main.Game;

import java.awt.*;

public abstract class Entity {

    public double x, y; // Coordinates
    public int width, height; //width and height of the Entity
    protected Game game; //Game Object

    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Entity(Game game, double x, double y) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    /**
     * tick
     */
    public abstract void tick();

    /**
     * render
     *
     * @param g Graphics g
     */
    public abstract void render(Graphics g);

    /**
     * returns a Rectangle that has the size of the Entity
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    /**
     * return the x-Coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * return the y-Coordinate
     */
    public double getY() {
        return y;
    }
}
