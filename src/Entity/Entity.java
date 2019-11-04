package Entity;

import java.awt.*;

public abstract class Entity {

    double x, y;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
