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
    BufferedImage image = ImageLoader.loadImage("/Wolke2.png");

    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Cloud2(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {

        ArrayList player = ArrayLists.getPlayer();
        for (int w = 0; w < player.size(); w++) {
            Player m = (Player) player.get(w);
            double playerX = m.getX();

            int drawAtX = (int) x - (int) (game.getGameCamera().getxOffset() / 4);
            int drawAtY = (int) (y - game.getGameCamera().getyOffset());
            if (drawAtX > playerX - game.getGameCamera().getxOffset() - 1350 && drawAtX < playerX - game.getGameCamera().getxOffset() + 970)
                g.drawImage(image, drawAtX, drawAtY, 400, 400, null);
        }
    }
}
