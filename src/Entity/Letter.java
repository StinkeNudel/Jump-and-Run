package Entity;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Letter extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/noodle.jpg");

    private Game game;
    int howToFly = 0;
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

        switch (howToFly) {
            case 0:
                x = x - 1;
                if (x == game.width - 700) {
                    howToFly++;
                    }
                break;
            case 1:
                x = x - 1;
                y = y - 1;
                if (x <= game.width - 950 && y <= game.height - 900) {
                    howToFly++;
                }
                break;
            case 2:
                x = x + 1;
                y = y - 1;
                if (x >= game.width - 800 && y <= game.height - 1000) {
                    howToFly++;
                }
                break;

        }

    }
}
