package Entity;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    BufferedImage image = ImageLoader.loadImage("/test.jpg");
    private Game game;

    public Player(Game game, double x, double y) {
        super(x, y);
        this.game = game;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }
}
