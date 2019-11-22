package Worlds;

import Blocks.Grass;
import Blocks.SolidBlocks;
import Entity.Enemy;
import Entity.Letter;
import Entity.Player;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TestWorld extends World {

    private BufferedImage Image; //background image
    BufferedImage tree = ImageLoader.loadImage("/Tree.png");
    BufferedImage mountain = ImageLoader.loadImage("/Berg.png");
    BufferedImage cloud1 = ImageLoader.loadImage("/Wolke1.png");
    BufferedImage cloud2 = ImageLoader.loadImage("/Wolke2.png");
    private Player player;
    private Letter letter;
    private Enemy enemy;
    int cloundAn = 0;
    private boolean background = false;

    /**
     * constructor
     *
     * @param game
     */
    public TestWorld(Game game) {
        super(game);
        player = new Player(game, game.width / 2, 100);
        ArrayLists.player.add(player);
        letter = new Letter(game, game.width - 100, game.height / 2 - 240);
        generateBlocks();

        enemy = new Enemy(game, 900, 90);
        ArrayLists.enemys.add(enemy);

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
        if(background) {
            //render background
            int treeX = -500;
            int mountainX = -500;
            int cloudX = -500;
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, 10000, 10000);

            for (int i = 0; i < 20; i++) {
                mountainX += 600;
                g.drawImage(mountain, mountainX - (int) (game.getGameCamera().getxOffset() / 8), game.height - 800, 800, 800, null);
            }
            for (int i = 0; i < 20; i++) {
                cloudX += 500;
                g.drawImage(cloud1, cloudX - (int) (game.getGameCamera().getxOffset() / 4 + cloundAn), game.height - 1200, 400, 400, null);
                cloudX += 500;
                g.drawImage(cloud2, cloudX - (int) (game.getGameCamera().getxOffset() / 4 + cloundAn), game.height - 1100, 400, 400, null);
            }
            cloundAn += 1;
            for (int i = 0; i < 20; i++) {
                treeX += 300;
                g.drawImage(tree, treeX - (int) (game.getGameCamera().getxOffset() / 4), game.height - 600, 500, 500, null);
            }
        }


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
