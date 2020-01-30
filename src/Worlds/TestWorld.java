package Worlds;

import Background.*;
import Blocks.GrassBlock;
import Blocks.SolidBlocks;
import Entity.*;
import Entity.Doors.DoorSaveRoom;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class TestWorld extends World {

    BufferedImage dirt = ImageLoader.loadImage("/Blocks/DirtBlock.png");

    private Player player;

    private DoorSaveRoom doorSaveRoom;


    /**
     * Constructor
     *
     * @param game Game Object
     */
    public TestWorld(Game game) {
        super(game);
        generateBackground();
        generateBlocks();

        player = new Player(game, game.width / 2, game.height - 221);
        ArrayLists.player.add(player);

        doorSaveRoom = new DoorSaveRoom(game, game.width / 2, game.height - 225);
        game.getGameCamera().setyOffset(300);
        saveGame();
    }

    /**
     * ticks the world
     */
    public void tick() {
        player.tick();
        doorSaveRoom.tick();
    }

    /**
     * renders the world
     *
     * @param g Graphics Object
     */
    public void render(Graphics g) {
        renderBackground(g);
        doorSaveRoom.render(g);
        player.render(g);
        renderBlocks(g);
    }

    private void renderBackground(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 10000, 10000);

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
     * generates the Bocks in the World
     */
    public void generateBlocks() {
        int BlockX = -3000, BlockY = game.height - 100;

        for (int i = 0; i < 1000; i++) {
            GrassBlock z = new GrassBlock(game, BlockX, BlockY);
            ArrayLists.solidBlocks.add(z);
            BlockX = BlockX + 64;
        }
    }

    /**
     * saves in SafeFile
     */
    private void saveGame() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Workspace\\Jump-and-Run\\src\\SaveFile"));
            bw.write("1");
            bw.close();
        } catch (Exception e) {
        }
    }
}