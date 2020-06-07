package Entity.Doors;

import Entity.Entity;
import GFX.ImageLoader;
import Input.MouseHandler;
import Main.ArrayLists;
import Main.Game;
import Worlds.SaveWorld;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DoorLevel1 extends Entity {

    private BufferedImage image = ImageLoader.loadImage("/door.png");

    private int doorWidth = game.blockSize;
    private int doorHeight = game.blockSize*2;

    public DoorLevel1(Game game, double x, double y) {
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

    private void openMe() {
        if (x < MouseHandler.clickX + game.getGameCamera().getxOffset() && x + doorWidth > MouseHandler.clickX + game.getGameCamera().getxOffset()) {
            if (y < MouseHandler.clickY + game.getGameCamera().getyOffset() && y + doorHeight > MouseHandler.clickY + game.getGameCamera().getyOffset()) {
                ArrayList solidBlocks = ArrayLists.getSolidBlocks();
                solidBlocks.clear();
                SaveWorld saveWorld = new SaveWorld(game);
                World.setWorld(saveWorld);
                game.getGameCamera().setxOffset(0);
                game.getGameCamera().setyOffset(0);
                MouseHandler.resetClicks();
            }
        }
    }
}