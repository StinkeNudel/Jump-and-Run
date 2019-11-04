package Worlds;

import Entity.Letter;
import Entity.Player;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuWorld extends World {

    private BufferedImage Image; //background image
    private Player player;
    private Letter letter;

    /**
     * Constructor
     */
    public MenuWorld(Game game) {
        super(game);
        player = new Player(game, 100, 100);
        letter = new Letter(game, game.width - 100, game.height - 700);

    }

    /**
     * World tick
     */
    @Override
    public void tick() {
        player.tick();
        letter.tick();
    }

    /**
     * World render
     */
    @Override
    public void render(Graphics g) {
        //render background
        g.drawImage(Image, 0, 0, null);
        player.render(g);
        letter.render(g);
    }
}
