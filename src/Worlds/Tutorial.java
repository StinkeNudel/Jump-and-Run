package Worlds;

import Background.Cloud1;
import Background.Cloud2;
import Background.Mountain;
import Background.Tree;
import Blocks.DirtBlock;
import Blocks.GrassBlock;
import Blocks.SolidBlocks;
import Entity.Enemy;
import Entity.Player;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Tutorial extends World {

    private BufferedImage dirt = ImageLoader.loadImage("/Blocks/DirtBlock.png");
    private Player player;
    private Enemy enemy;

    /**
     * constructor
     *
     * @param game
     */
    public Tutorial(Game game) {
        super(game);
        //generateBackground();
        generateBlocks();

        player = new Player(game, game.width / 2, game.height - 500);
        ArrayLists.player.add(player);

        enemy = new Enemy(game, 900, 90);

        saveGame();

    }

    /**
     * tick World
     */
    public void tick() {
        player.tick();
        enemy.tick();
    }

    /**
     * render World
     *
     * @param g Graphic Object
     */
    public void render(Graphics g) {
        renderBackground(g);
        player.render(g);
        enemy.render(g);
        renderBlocks(g);
    }


    private void renderBackground(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 10000, 10000);

        ArrayList cloud1s = ArrayLists.cloud1s;
        for (int w = 0; w < cloud1s.size(); w++) {
            Cloud1 m = (Cloud1) cloud1s.get(w);
            m.x -= 1;
            m.render(g);
        }

        ArrayList mountains = ArrayLists.mountains;
        for (int w = 0; w < mountains.size(); w++) {
            Mountain m = (Mountain) mountains.get(w);
            m.render(g);
        }

        g.drawImage(dirt, 0 - (int) (game.getGameCamera().getxOffset()), 0 + 980 - (int) (game.getGameCamera().getyOffset()), 10000, 10000, null);

        ArrayList trees = ArrayLists.trees;
        for (int w = 0; w < trees.size(); w++) {
            Tree m = (Tree) trees.get(w);
            m.render(g);
        }

        ArrayList cloud2s = ArrayLists.cloud2s;
        for (int w = 0; w < cloud2s.size(); w++) {
            Cloud2 m = (Cloud2) cloud2s.get(w);
            m.x -= 1;
            m.render(g);
        }
    }

    private void generateBackground() {
        int cloud1X = 0;
        int cloud2X = 500;
        for (int i = 0; i < 20; i++) {
            Cloud1 z = new Cloud1(game, cloud1X, game.height - 1000);
            ArrayLists.cloud1s.add(z);
            cloud1X += 1000;
        }
        for (int i = 0; i < 20; i++) {
            Cloud2 c = new Cloud2(game, cloud2X, game.height - 1050);
            ArrayLists.cloud2s.add(c);
            cloud2X += 1000;
        }
        int mountainX = -3000;
        for (int i = 0; i < 20; i++) {
            Mountain u = new Mountain(game, mountainX, game.height - 800);
            ArrayLists.mountains.add(u);
            mountainX += 800;
        }

        int treeX = -2000;
        for (int i = 0; i < 20; i++) {
            Tree t = new Tree(game, treeX, game.height - 600);
            ArrayLists.trees.add(t);
            treeX += 300;
        }


    }

    private void renderBlocks(Graphics g) {
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            if (m.x > player.x - 1100 && m.x < player.x + 1000) {
                m.render(g);
            }
        }
    }

    /**
     * generates Blocks of the World
     */
    public void generateBlocks() {
        int BlockX = 0, BlockY = game.height - 100, dBlockX = 30 * 64, qBlockY = game.height + 92, rBlockX = 47 * 64,
                sBlockY = game.height + 988;

        for (int i = 0; i < 46; i++) {
            GrassBlock z = new GrassBlock(game, BlockX, BlockY);
            ArrayLists.solidBlocks.add(z);
            BlockX = BlockX + 64;
        }

        //dirt
        DirtBlock a = new DirtBlock(game, 29 * 64, game.height - 164);
        ArrayLists.solidBlocks.add(a);

        GrassBlock b = new GrassBlock(game, 29 * 64, game.height - 228);
        ArrayLists.solidBlocks.add(b);

        DirtBlock c = new DirtBlock(game, 30 * 64, game.height - 228);
        ArrayLists.solidBlocks.add(c);

        for (int i = 0; i < 5; i++) {
            if (i == 2 || i == 3) {
                DirtBlock d = new DirtBlock(game, dBlockX, game.height - 292);
                ArrayLists.solidBlocks.add(d);
            } else {
                GrassBlock d = new GrassBlock(game, dBlockX, game.height - 292);
                ArrayLists.solidBlocks.add(d);
            }
            dBlockX = dBlockX + 64;
        }


        GrassBlock h = new GrassBlock(game, 32 * 64, game.height - 356);
        ArrayLists.solidBlocks.add(h);

        GrassBlock i = new GrassBlock(game, 33 * 64, game.height - 356);
        ArrayLists.solidBlocks.add(i);

        GrassBlock j = new GrassBlock(game, 37 * 64, game.height - 164);
        ArrayLists.solidBlocks.add(j);

        DirtBlock e = new DirtBlock(game, 37 * 64, game.height - 100);
        ArrayLists.solidBlocks.add(e);

        //dirt
        DirtBlock k = new DirtBlock(game, 42 * 64, game.height - 164);
        ArrayLists.solidBlocks.add(k);

        GrassBlock l = new GrassBlock(game, 42 * 64, game.height - 228);
        ArrayLists.solidBlocks.add(l);

        GrassBlock m = new GrassBlock(game, 45 * 64, game.height - 36);
        ArrayLists.solidBlocks.add(m);

        GrassBlock n = new GrassBlock(game, 46 * 64, game.height - 36);
        ArrayLists.solidBlocks.add(n);
        GrassBlock o = new GrassBlock(game, 46 * 64, game.height + 28);
        ArrayLists.solidBlocks.add(o);
        DirtBlock p = new DirtBlock(game, 46 * 64, game.height + 92);
        ArrayLists.solidBlocks.add(p);

        for (int z = 0; z < 15; z++) {
            GrassBlock q = new GrassBlock(game, 47 * 64, qBlockY);
            ArrayLists.solidBlocks.add(q);
            qBlockY = qBlockY + 64;
        }

        for (int z = 0; z < 8; z++) {
            GrassBlock r = new GrassBlock(game, rBlockX, game.height + 1052);
            ArrayLists.solidBlocks.add(r);
            rBlockX = rBlockX + 64;
        }

        for (int z = 0; z < 18; z++) {
            GrassBlock s = new GrassBlock(game, 51 * 64, sBlockY);
            ArrayLists.solidBlocks.add(s);
            sBlockY = sBlockY - 64;
        }


    }


    /**
     * saves in SafeFile
     */
    private void saveGame() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Workspace\\Jump-and-Run\\src\\SaveFile"));
            bw.write("0");
            bw.close();
        } catch (Exception e) {
        }
    }
}

