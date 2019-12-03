package Entity;

import GFX.ImageLoader;
import Input.MouseHandler;
import Main.Game;
import Worlds.SaveWorld;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TouchMe extends Entity {

    BufferedImage image = ImageLoader.loadImage("/noodle.jpg");


    int touchMeWidth = 412;
    int touchMeHeigth = 450;

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
        if (x < MouseHandler.clickX && x + touchMeWidth > MouseHandler.clickX) {
            if (y < MouseHandler.clickY && y + touchMeHeigth > MouseHandler.clickY) {
                System.out.println("hello");
                SaveWorld saveWorld = new SaveWorld(game);
                World.setWorld(saveWorld);
                MouseHandler.resetClicks();
            }
        }
    }
}
