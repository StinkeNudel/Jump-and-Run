package Background;

import Entity.Entity;
import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rose extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/Plants/Rose1.png");
    private int counter;
    private int counterCounter;

    public Rose(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
        animation();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), game.blockSize*2, game.blockSize*2, null);
    }

    private void animation() {
        switch (counter) {
            case 0:
                image = ImageLoader.loadImage("/Plants/Rose1.png");
                break;
            case 1:
                image = ImageLoader.loadImage("/Plants/Rose2.png");
                break;
        }
        if (counterCounter >= 8) {
            counter++;
            counterCounter = 0;
        } else
            counterCounter++;

        if (counter >= 2) {
            counter = 0;
        }
    }
}