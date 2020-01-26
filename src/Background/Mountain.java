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
    BufferedImage image = ImageLoader.loadImage("/Background/Berg.png");

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
        for (int w = 0; w < player.size(); w++) {
            Player m = (Player) player.get(w);
            double playerX = m.getX();

            int drawAtX = (int) x - (int) (game.getGameCamera().getxOffset() / 8);
            int drawAtY = (int) (y - game.getGameCamera().getyOffset());
            if (drawAtX > playerX - game.getGameCamera().getxOffset() - 3000 && drawAtX < playerX - game.getGameCamera().getxOffset() + 970)
                g.drawImage(image, drawAtX, drawAtY, 800, 800, null);
        }
    }
}
