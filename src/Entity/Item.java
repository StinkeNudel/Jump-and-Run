package Entity;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {

    private BufferedImage key = ImageLoader.loadImage("/Items/key.png");
    private BufferedImage axe = ImageLoader.loadImage("/Items/axe.png");


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
        width = game.blockSize;
        height = game.blockSize;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        if(type.equals("key")) {
            g.drawImage(key, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), (int)(1.5*game.blockSize), (int)(0.5*game.blockSize), null);
        }
        else if(type.equals("axe")){
            g.drawImage(axe, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), (int)(1*game.blockSize), (int)(3*game.blockSize), null);
        }
        g.setColor(Color.blue);
        g.drawRect((int) (getBounds().x - game.getGameCamera().getxOffset()), (int) (getBounds().y - game.getGameCamera().getyOffset()), getBounds().width, getBounds().height);

    }
}
