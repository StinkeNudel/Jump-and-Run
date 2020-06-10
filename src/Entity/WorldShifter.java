package Entity;

import Main.Game;

import java.awt.*;

public class WorldShifter extends Entity{

    private int r = 255, n = 255, b = 255;
            double a = 0.0;

    public boolean shift = false;
    public boolean shift2 = false;


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

        worldShift(g);

    }

    public void worldShift(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.width, game.height);

        if(r <=255 && n <=255 && b <=255 && shift) {
            r -= 2;
            n -= 2;
            b -= 2;
            shift = false;
            shift2 = true;
        }

            if (r >=0 && n >=0 && b >=0 && shift2) {
                r+=2;
                n+=2;
                b+=2;
                shift2 = false;
            }

            System.out.println("r" + r);
        System.out.println("n"+n);
        System.out.println("b"+b);
        }


}
