package Entity;

import Main.Game;

import java.awt.*;
import java.util.Random;

public class Schwurbel extends Entity {

    Random random = new Random();
    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Schwurbel(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
movement();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect((int)x, (int)y, 20,20);

    }


    public void movement() {
        int whenMove;
        whenMove = random.nextInt(100);

        int moveX;
        moveX = random.nextInt(15);

        int moveY;
        moveY = random.nextInt(10);

        if(0 < whenMove && whenMove < 10) {
            x = x - moveX;
            y = y - moveY;
        }
    }
}
