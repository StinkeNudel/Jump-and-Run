package Entity;

import GFX.ImageLoader;
import Input.MouseHandler;
import Main.Game;
import Worlds.SaveWorld;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TouchMe extends Entity {

    BufferedImage image = ImageLoader.loadImage("/door.png");


    int touchMeWidth = 64;
    int touchMeHeigth = 128;

    private MouseHandler mouseHandler;

    public TouchMe(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
        checkPlayer();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), touchMeWidth, touchMeHeigth, null);


    }

    public void checkPlayer() {
        if (
                MouseHandler.clickY > y && MouseHandler.clickY < y + touchMeHeigth
                && x < MouseHandler.clickX && x + touchMeWidth > MouseHandler.clickX
                ) {
            //System.out.println("X funktioniert");
            System.out.println("Y funktioniert");
            SaveWorld saveWorld = new SaveWorld(game);
            World.setWorld(saveWorld);
            MouseHandler.resetClicks();
        }
    }

}
