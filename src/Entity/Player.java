package Entity;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    BufferedImage image = ImageLoader.loadImage("/test.jpg");
    private Game game;
    private int playerWidth = 100, playerHeight = 100;

    public Player(Game game, double x, double y) {
        super(x, y);
        this.game = game;
    }

    @Override
    public void tick() {
        input();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, playerWidth, playerHeight, null);
    }

    private void input() {
        if (game.getKeyHandler().w) {
            y = y - 3;
        }
        if (game.getKeyHandler().a) {
            x = x - 3;
        }
        if (game.getKeyHandler().s) {
            y = y + 3;
        }
        if (game.getKeyHandler().d) {
            x = x + 3;
        }
    }
}
