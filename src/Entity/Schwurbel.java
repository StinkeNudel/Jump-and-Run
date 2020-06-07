package Entity;

import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Schwurbel extends Entity {

    Random random = new Random();
    int color;
    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Schwurbel(Game game, double x, double y) {
        super(game, x, y);

        color = random.nextInt(3);
    }

    @Override
    public void tick() {
movement();
    }

    @Override
    public void render(Graphics g) {
        Color color1 = new Color(108, 42, 108);
        Color color2 = new Color(85, 40, 85);
        Color color3 = new Color(71, 22, 71);


        ArrayList player = ArrayLists.getPlayer();
        for (Object o : player) {
            Player m = (Player) o;
            double playerX = m.getX();

            int drawAtX = (int) x - (int) (game.getGameCamera().getxOffset() / 8);
            if (drawAtX > playerX - game.getGameCamera().getxOffset() - 3000 && drawAtX < playerX - game.getGameCamera().getxOffset() + 970) {
                switch (color) {
                    case 0:
                        g.setColor(color1);
                        break;
                    case 1:
                        g.setColor(color2);
                        break;
                    case 2:
                        g.setColor(color3);
                        break;
                }
                g.fillRect((int) x, (int) y, 7, 7);

            }
        }
    }


    public void movement() {
        int whenMove;
        whenMove = random.nextInt(100);

        int moveX;
        moveX = random.nextInt(30);

        int moveY;
        moveY = random.nextInt(20);

        if(0 < whenMove && whenMove < 20) {
            x = x - moveX;
            y = y - moveY;
        }
    }
}
