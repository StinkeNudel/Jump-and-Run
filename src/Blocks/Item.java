package Blocks;

import Entity.Entity;
import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {

    private BufferedImage item = ImageLoader.loadImage("/noodle.jpg");


    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */

    public String type;

    public Item(Game game, double x, double y, String type) {
        super(game, x, y);
        this.type = type;
        width = 64;
        height = 64;
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

    public void pickedUp(){
        if(type.equals("shoe")) {

        }
    }



}
