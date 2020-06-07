package Background;

import Entity.Entity;
import Entity.Player;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mountain extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/Background/Berg.png");

    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Mountain(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        ArrayList player = ArrayLists.getPlayer();
        for (Object o : player) {
            Player m = (Player) o;
            double playerX = m.getX();

            int drawAtX = (int) x - (int) (game.getGameCamera().getxOffset() / 8);
            int drawAtY = (int) (y - game.getGameCamera().getyOffset());
            if (drawAtX > playerX - game.getGameCamera().getxOffset() - game.blockSize*46.875 && drawAtX < playerX - game.getGameCamera().getxOffset() + game.blockSize*15.15625)
                g.drawImage(image, drawAtX, drawAtY, (int)(game.blockSize*12.8), (int)(game.blockSize*12.8), null);
        }
    }
}
