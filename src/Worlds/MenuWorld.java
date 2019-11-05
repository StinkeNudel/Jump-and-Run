package Worlds;

import Blocks.Grass;
import Blocks.SolidBlocks;
import Entity.Letter;
import Entity.Player;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
        ArrayLists.player.add(player);
        letter = new Letter(game, game.width - 100, game.height - 400);
        generateBlocks();
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
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            m.render(g);
        }

    }

    public void generateBlocks() {
        int BlockX = 100, BlockY = game.height - 100;
        for (int i = 0; i < 40; i++) {
            Grass z = new Grass(BlockX, BlockY);
            ArrayLists.solidBlocks.add(z);
            BlockX = BlockX + 64;
        }
    }
}
