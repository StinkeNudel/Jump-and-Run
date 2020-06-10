package Entity;

import Main.Game;

import java.awt.*;

public class WorldShifter extends Entity{

    private int r = 255, n = 255, b = 255;

    public boolean shift = false;


    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public WorldShifter(Game game, double x, double y) {
        super(game, x, y);
    }



    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        System.out.println("Hey Im there");

        if(shift) {
            worldShift(g);
        }
    }

    public void worldShift(Graphics g) {
        g.setColor(new Color (r,n,b));
        g.fillRect(0, 0, game.width, game.height);

        if(r <=255 && n <=255 && b <=255) {
            r--;
            n--;
            b--;
            /**if (r >=0 && n >=0 && b >=0) {
                r++;
                n++;
                b++;
                shift = false;
            } */
        }
        System.out.println("r" + r);
        System.out.println("n"+n);
        System.out.println("b"+b);
    }
}
