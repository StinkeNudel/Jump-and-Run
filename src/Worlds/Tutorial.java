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

public class Tutorial extends World {


    private BufferedImage Image; //background image
    BufferedImage tree = ImageLoader.loadImage("/Tree.png");
    BufferedImage mountain = ImageLoader.loadImage("/Berg.png");
    BufferedImage cloud1 = ImageLoader.loadImage("/Wolke1.png");
    BufferedImage cloud2 = ImageLoader.loadImage("/Wolke2.png");
    private Player player;
    private Enemy enemy;
    int cloundAn = 0;

    /**
     * constructor
     *
     * @param game
     */
    public Tutorial(Game game) {
        super(game);
        player = new Player(game, game.width / 2, 100);
        ArrayLists.player.add(player);
        generateBlocks();

        enemy = new Enemy(game, 900, 90);
        game.getGameCamera().move(0, 0);
    }

    @Override
    public void tick() {

        player.tick();
        enemy.tick();

    }

    @Override
    public void render(Graphics g) {
        //render background
        int treeX = -500;
        int mountainX = -500;
        int cloudX = - 500;
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 10000 , 10000);

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



        player.render(g);
        enemy.render(g);
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            m.render(g);
        }
    }

    public void generateBlocks() {
        int BlockX = 0, BlockY = game.height - 100, dBlockX = 30*64, qBlockY = game.height + 92;

        for (int i = 0; i < 46; i++) {
            Grass z = new Grass(game, BlockX, BlockY);
            ArrayLists.solidBlocks.add(z);
            BlockX = BlockX + 64;
        }

        //dirt
        Grass a = new Grass(game, 29*64, game.height - 164);
        ArrayLists.solidBlocks.add(a);

        Grass b = new Grass(game, 29*64, game.height - 228);
        ArrayLists.solidBlocks.add(b);

        Grass c = new Grass(game, 30*64, game.height - 228);
        ArrayLists.solidBlocks.add(c);

        for (int i = 0; i < 5; i++) {
            Grass d = new Grass(game, dBlockX, game.height - 292);
            ArrayLists.solidBlocks.add(d);
            dBlockX = dBlockX + 64;
        }

        //dirt
        Grass e = new Grass(game,32*64, game.height - 292);
        ArrayLists.solidBlocks.add(e);

        //dirt
        Grass f = new Grass(game,33*64, game.height - 292);
        ArrayLists.solidBlocks.add(f);

        Grass g = new Grass(game,34*64, game.height - 292);
        ArrayLists.solidBlocks.add(g);

        Grass h = new Grass(game,32*64, game.height - 356);
        ArrayLists.solidBlocks.add(h);

        Grass i = new Grass(game,33*64, game.height - 356);
        ArrayLists.solidBlocks.add(i);

        Grass j = new Grass(game,37*64, game.height - 164);
        ArrayLists.solidBlocks.add(j);

        //dirt
        Grass k = new Grass(game,42*64, game.height - 164);
        ArrayLists.solidBlocks.add(k);

        Grass l = new Grass(game,42*64, game.height - 228);
        ArrayLists.solidBlocks.add(l);

        Grass m = new Grass(game,45*64, game.height - 36);
        ArrayLists.solidBlocks.add(m);

        Grass n = new Grass(game,46*64, game.height - 36);
        ArrayLists.solidBlocks.add(n);
        Grass o = new Grass(game,46*64, game.height + 28);
        ArrayLists.solidBlocks.add(o);
        Grass p = new Grass(game,46*64, game.height + 92);
        ArrayLists.solidBlocks.add(p);

        for (int z = 0; z < 15; z++) {
            Grass q = new Grass(game, 47 * 64, qBlockY);
            ArrayLists.solidBlocks.add(q);
            qBlockY = qBlockY + 64;
        }


    }
}
