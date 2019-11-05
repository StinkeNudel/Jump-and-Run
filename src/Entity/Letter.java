package Entity;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Letter extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/Scroll_Open-12.png.png");

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
        g.drawImage(image, (int) x, (int) y, 104, 138, null);
    }

    public void move() {

        switch (howToFly) {
            case 0: //Scroll fliegt von rechts rein, geradeaus
                x = x - 5;
                if (x == game.width - 500) {
                    howToFly++;
                }
                break;
            case 1: //Scroll fliegt nach schräg oben links
                x = x - 1;
                y = y - 2;
                if (x <= game.width - 550 && y <= game.height - 600) {
                    howToFly++;
                }
                break;
            case 2: //Scroll fliegt kurz nur nach oben
                y = y - 2;
                if (x <= game.width - 550 && y <= game.height - 630) {
                    howToFly++;
                }
                break;
            case 3: //Scroll fliegt nach rechts oben
                x = x + 1;
                y = y - 1;
                if (x >= game.width - 550 && y <= game.height - 650) {
                    howToFly++;
                }
                break;
            case 4: //Scroll fliegt kurz nur nach rechts
                x = x + 2;
                if (x >= game.width - 520 && y <= game.height - 650) {
                    howToFly++;
                }
                break;
            case 5: //Scroll fliegt nach rechts unten
                x = x + 1;
                y = y + 3;
                if (x >= game.width - 500 && y >= game.height - 600) {
                    howToFly++;
                }
                break;
            case 6: //Scroll kurz nur nach unten
                y = y +2;
                if (x >= game.width - 500 && y >= game.height - 580) {
                    howToFly++;
                }
                break;
            case 7: //Scroll fliegt nach links unten
                x = x - 5;
                y = y + 3;
                if (x <= game.width - 550 && y >= game.height - 500) {
                    howToFly++;
                }
                break;

        }

    }
}
