package Entity;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Worm extends Enemy {

    private BufferedImage image = ImageLoader.loadImage("/Worm/worm1.png");

    private int animation = 0;
    private int animationCounter;

    public Worm(Game game, double x, double y) {
        super(game, x, y);
        width = 20;
        height = 64;
    }

    @Override
    public void tick() {
        super.tick();

        animationCounter++;
        if (animationCounter >= 3) {
            animation();
            animationCounter = 0;
        }
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }

    private void animation() {
        switch (animation) {
            case 0:
                image = ImageLoader.loadImage("/Worm/worm1.png");
                animation++;
                break;
            case 1:
                image = ImageLoader.loadImage("/Worm/worm2.png");
                animation++;
                break;
            case 2:
            case 16:
                image = ImageLoader.loadImage("/Worm/worm3.png");
                animation++;
                break;
            case 3:
            case 15:
                image = ImageLoader.loadImage("/Worm/worm4.png");
                animation++;
                break;
            case 4:
            case 14:
                image = ImageLoader.loadImage("/Worm/worm5.png");
                animation++;
                break;
            case 5:
            case 13:
                image = ImageLoader.loadImage("/Worm/worm6.png");
                animation++;
                break;
            case 6:
            case 12:
                image = ImageLoader.loadImage("/Worm/worm7.png");
                animation++;
                break;
            case 7:
            case 11:
                image = ImageLoader.loadImage("/Worm/worm8.png");
                animation++;
                break;
            case 8:
            case 10:
                image = ImageLoader.loadImage("/Worm/worm9.png");
                animation++;
                break;
            case 9:
                image = ImageLoader.loadImage("/Worm/worm10.png");
                animation++;
                break;
            case 17:
                image = ImageLoader.loadImage("/Worm/worm2.png");
                animation = 0;
                break;

        }
    }
}
