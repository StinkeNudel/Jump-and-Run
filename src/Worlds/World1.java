package Worlds;

import Background.Cloud1;
import Background.Cloud2;
import Background.Mountain;
import Background.Tree;
import Blocks.*;
import Entity.Player;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class World1 extends World {
    Player player;
    private int checkpoint;
    BufferedImage dirt = ImageLoader.loadImage("/Blocks/DirtBlock.png");


    public World1(Game game) {
        super(game);
        loadFile();
        generateBackground();
        generateObjects();
        player = new Player(game, 0 + Game.blockSize, game.height - 10 * Game.blockSize);
        ArrayLists.player.add(player);
    }

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        renderBackground(g);
        renderBlocks(g);
        player.render(g);
    }

    private void generateObjects() {
        {
            int BlockX = 53 * Game.blockSize, BlockY = game.height;

            for (int i = 0; i < 5; i++) {
                Ladder z = new Ladder(game, BlockX, BlockY, "ladder");
                ArrayLists.backBlocks.add(z);
                BlockY = BlockY + Game.blockSize;
            }

        }
        {
            int BlockX = 65 * Game.blockSize, BlockY = game.height - (int) (Game.blockSize * 0.234375) * 64;

            for (int i = 0; i < 11; i++) {
                Ladder z = new Ladder(game, BlockX, BlockY, "ladder");
                ArrayLists.backBlocks.add(z);
                BlockY = BlockY + Game.blockSize;
            }
        }
    }

    private void renderBackground(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 10000, 10000);

        ArrayList mountains = ArrayLists.mountains;
        for (int w = 0; w < mountains.size(); w++) {
            Mountain m = (Mountain) mountains.get(w);
            m.render(g);
        }


        ArrayList trees = ArrayLists.trees;
        for (int w = 0; w < trees.size(); w++) {
            Tree m = (Tree) trees.get(w);
            m.render(g);
        }


        ArrayList cloud1s = ArrayLists.cloud1s;
        for (int w = 0; w < cloud1s.size(); w++) {
            Cloud1 m = (Cloud1) cloud1s.get(w);
            m.x -= 1;
            m.render(g);
        }

        ArrayList cloud2s = ArrayLists.cloud2s;
        for (int w = 0; w < cloud2s.size(); w++) {
            Cloud2 m = (Cloud2) cloud2s.get(w);
            m.x -= 1;
            m.render(g);
        }

        ArrayList backBlock = ArrayLists.getBackBlocks();
        for (int w = 0; w < backBlock.size(); w++) {
            BackBlock m = (BackBlock) backBlock.get(w);
            if (m.x > player.x - (int) (Game.blockSize * 17.1875) && m.x < player.x + (int) (Game.blockSize * 17.1875)) {
                m.render(g);
            }
        }
    }

    private void generateBackground() {
        int cloud1X = 0;
        int cloud2X = (int) (Game.blockSize * 7.8125);
        for (int i = 0; i < 20; i++) {
            Cloud1 z = new Cloud1(game, cloud1X, game.height - (int) (Game.blockSize * 9.375));
            ArrayLists.cloud1s.add(z);
            cloud1X += (int) (Game.blockSize * 17.1875);
        }
        for (int i = 0; i < 20; i++) {
            Cloud2 c = new Cloud2(game, cloud2X, game.height - (int) (Game.blockSize * 16.40625));
            ArrayLists.cloud2s.add(c);
            cloud2X += (int) (Game.blockSize * 15.625);
        }
        int mountainX = -(int) (Game.blockSize * 46.875);
        for (int i = 0; i < 20; i++) {
            Mountain u = new Mountain(game, mountainX, game.height - (int) (Game.blockSize * 6.25));
            ArrayLists.mountains.add(u);
            mountainX += (int) (Game.blockSize * 12.5);
        }

        int treeX = -(int) (Game.blockSize * 31.25);
        for (int i = 0; i < 20; i++) {
            Tree t = new Tree(game, treeX, game.height - (int) (Game.blockSize * 1.5625));
            ArrayLists.trees.add(t);
            treeX += (int) (Game.blockSize * 4.6875);
        }
    }

    private void renderBlocks(Graphics g) {
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            if (m.x > player.x - (int) (Game.blockSize * 17.1875) && m.x < player.x + (int) (Game.blockSize * 17.1875)) {
                m.render(g);
            }
        }
    }

    private void loadFile() {
        File file = new File("res\\Worlds\\World1");
        width = 96;
        height = 27;
        String type;
        Scanner s;

        try {
            s = new Scanner(file);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    type = s.next();
                    if (type.equals("N")) {
                        //NOTHING
                    } else if (type.equals("l") || type.equals("s") || type.equals("d")) {
                        BackBlock b = new BackBlock(game, x * Game.blockSize, y * Game.blockSize, type);
                        ArrayLists.backBlocks.add(b);
                    } else {
                        Block b = new Block(game, x * Game.blockSize, y * Game.blockSize, type);
                        ArrayLists.solidBlocks.add(b);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

