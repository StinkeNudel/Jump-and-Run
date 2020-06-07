package Background;


import Entity.Entity;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;
import Entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Cloud2 extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/Background/Wolke2.png");//Image of the Object

    /**
     * Constructor
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Cloud2(Game game, double x, double y) {
        super(game, x, y);
    }

    /**
     * tick
     */
    @Override
    public void tick() {
    }

    /**
     * renders Cloud1s in range if the player
     *
     * @param g Graphics g
     */
    @Override
    public void render(Graphics g) {

        ArrayList player = ArrayLists.getPlayer();
        for (Object o : player) {
            Player m = (Player) o;
            double playerX = m.getX();

            int drawAtX = (int) x - (int) (game.getGameCamera().getxOffset() / 4);
            int drawAtY = (int) (y - game.getGameCamera().getyOffset());
            if (drawAtX > playerX - game.getGameCamera().getxOffset() - game.blockSize*21.09375 && drawAtX < playerX - game.getGameCamera().getxOffset() + game.blockSize*15.15625)
                g.drawImage(image, drawAtX, drawAtY, (int)(game.blockSize*6.25), (int)(game.blockSize*6.25), null);
        }
    }
}
