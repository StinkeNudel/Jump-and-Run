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


    int touchMeWidth = 60;
    int touchMeHeigth = 60;

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
        if (x < MouseHandler.clickX && x + touchMeWidth > MouseHandler.clickX
                && y < MouseHandler.clickY && y + touchMeHeigth > MouseHandler.clickY) {
            SaveWorld saveWorld = new SaveWorld(game);
            World.setWorld(saveWorld);
        }
    }
}
