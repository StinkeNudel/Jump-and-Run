package Blocks;

import Entity.Entity;
import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {

    private BufferedImage item = ImageLoader.loadImage("/noodle.jpg");

    int width = 300;
    int height = 300;

    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public Item(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(item, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), 64, 64, null);
        g.setColor(Color.blue);
        g.drawRect((int) (getBounds().x - game.getGameCamera().getxOffset()), (int) (getBounds().y - game.getGameCamera().getyOffset()), getBounds().width, getBounds().height);

    }



}
