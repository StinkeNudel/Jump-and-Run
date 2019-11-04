package Worlds;

import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuWorld extends World {

    private BufferedImage Image; //background image

    /**
     * Constructor
     */
    public MenuWorld(Game game) {
        super(game);
    }

    /**
     * World tick
     */
    @Override
    public void tick() {
    }

    /**
     * World render
     */
    @Override
    public void render(Graphics g) {
        //render background
        g.drawImage(Image, 0, 0, null);
    }
}
