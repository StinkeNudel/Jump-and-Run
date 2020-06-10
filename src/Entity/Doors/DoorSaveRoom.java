package Entity.Doors;

import Entity.Entity;
import GFX.ImageLoader;
import Main.Game;
import Entity.WorldShifter;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DoorSaveRoom extends Entity {

    private BufferedImage image = ImageLoader.loadImage("/door.png");

    public DoorSaveRoom(Game game, double x, double y) {
        super(game, x, y);

        width = game.blockSize;
        height = 2 * game.blockSize;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawRect((int) (this.getBounds().x - game.getGameCamera().getxOffset()), (int) (this.getBounds().y - game.getGameCamera().getyOffset()), this.getBounds().width, this.getBounds().height);

        //g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }
}