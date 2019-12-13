package Entity;

import GFX.ImageLoader;
import Input.MouseHandler;
import Main.Game;
import Worlds.SaveWorld;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Door extends Entity {

    BufferedImage image = ImageLoader.loadImage("/door.png");

    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */

    int doorWidth = 68;
    int doorHeight = 128;

    public Door(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
        openMe();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), doorWidth, doorHeight, null);

    }

    public void openMe() {
        if (y < MouseHandler.clickY && y + doorHeight > MouseHandler.clickY) {
            System.out.println("Y funktioniert");
            SaveWorld saveWorld = new SaveWorld(game);
            World.setWorld(saveWorld);
            MouseHandler.resetClicks();
        }
    }


}
