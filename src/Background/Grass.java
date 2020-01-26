package Background;

import Entity.Entity;
import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grass extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/Plants/Grass1.png");
    private int counter;
    private int counterCounter;

    public Grass(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
        animation();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), 128, 128, null);
    }

    private void animation() {
        switch (counter) {
            case 0:
            case 2:
                image = ImageLoader.loadImage("/Plants/Grass1.png");
                break;
            case 1:
                image = ImageLoader.loadImage("/Plants/Grass2.png");
                break;
            case 3:
                image = ImageLoader.loadImage("/Plants/Grass3.png");
                break;
        }
        if (counterCounter >= 8) {
            counter++;
            counterCounter = 0;
        } else
            counterCounter++;

        if (counter >= 4) {
            counter = 0;
        }
    }
}