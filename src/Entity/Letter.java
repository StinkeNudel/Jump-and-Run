package Entity;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Letter extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/noodle.jpg");

    private Game game;

    public Letter(Game game, double x, double y) {
        super(x, y);
        this.game = game;
    }

    public void tick() {
        move();
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public void move() {
        int howToFly = 0;
        switch (howToFly) {
            case 0:
                x = x - 1;
                if (x == game.width - 50) {
                    howToFly++;
                }
                break;
            case 1:
                x = x - 1;
                y = y - 1;
                if (x == game.width - 55 && y == game.height - 250) {
                    howToFly++;
                }
                break;
            case 2:
                x = x + 1;
                y = y - 1;
                if (x == game.width - 50 && y == game.height - 255) {
                    howToFly++;
                }
                break;

        }

    }
}
