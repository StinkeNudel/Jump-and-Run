package Worlds;

import Blocks.Grass;
import Blocks.SolidBlocks;
import Entity.Enemy;
import Entity.Letter;
import Entity.Player;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TestWorld extends World {

    private BufferedImage Image; //background image
    private Player player;
    private Letter letter;
    private Enemy enemy;

    /**
     * constructor
     *
     * @param game
     */
    public TestWorld(Game game) {
        super(game);
        player = new Player(game, game.width/2, 100);
        ArrayLists.player.add(player);
        letter = new Letter(game, game.width - 100, game.height / 2 - 240);
        generateBlocks();

        enemy = new Enemy(game, 900, 100);
        game.getGameCamera().move(0, 0);
    }

    @Override
    public void tick() {

        player.tick();
        letter.tick();
        enemy.tick();

    }

    @Override
    public void render(Graphics g) {
        //render background
        g.drawImage(Image, 0, 0, null);
        player.render(g);
        letter.render(g);
        enemy.render(g);
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            m.render(g);

        }
    }

    public void generateBlocks() {
        int BlockX = 100, BlockY = game.height - 100;

        for (int i = 0; i < 40; i++) {
            Grass z = new Grass(game, BlockX, BlockY);
            ArrayLists.solidBlocks.add(z);
            BlockX = BlockX + 64;
        }
        Grass z = new Grass(game, 300, BlockY - 64);
        ArrayLists.solidBlocks.add(z);
    }
}
