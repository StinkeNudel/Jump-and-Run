package Background;

import Entity.Entity;
import Entity.Player;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tree extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/Background/Tree.png");

    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Tree(Game game, double x, double y) {
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

            int drawAtX = (int) x - (int) (game.getGameCamera().getxOffset() / 4);
            int drawAtY = (int) (y - game.getGameCamera().getyOffset());
            if (drawAtX > playerX - game.getGameCamera().getxOffset() - game.blockSize*31.25 && drawAtX < playerX - game.getGameCamera().getxOffset() + game.blockSize*15.15625)
                g.drawImage(image, drawAtX, drawAtY, (int)(game.blockSize*7.8125), (int)(game.blockSize*7.8125), null);
        }
    }
}
