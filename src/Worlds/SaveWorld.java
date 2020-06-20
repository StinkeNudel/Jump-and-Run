package Worlds;

import Blocks.SolidBlocks;
import Blocks.StoneBlock;
import Entity.Doors.Door;
import Entity.Player;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class SaveWorld extends World {

    private BufferedImage image = ImageLoader.loadImage("/Blocks/DirtBlock.png");

    private Player player;
    private Door door;
    private int startAlpha = 254;

    /**
     * Constructor
     *
     * @param game Game Object
     */
    public SaveWorld(Game game) {
        super(game);
        generateBlocks();

        player = new Player(game, game.width / 2, game.height - 221);
        ArrayLists.player.add(player);


        door = new Door(game, game.width / 2 + 500, game.height - 225, "door");
        game.getGameCamera().setyOffset(300);
        saveGame();
    }

    /**
     * ticks the world
     */
    public void tick() {
        player.tick();
        door.tick();
    }


    /**
     * renders the world
     *
     * @param g Graphics Object
     */
    public void render(Graphics g) {
        g.drawImage(image, -1000 - (int) (game.getGameCamera().getxOffset()), -1000 - (int) (game.getGameCamera().getyOffset()), 6000, 6000, null);
        door.render(g);
        player.render(g);
        renderBlocks(g);
        startBlackScreen(g);
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

    private void startBlackScreen(Graphics g) {
        if (startAlpha > 1) {
            Color color = new Color(0, 0, 0, startAlpha);
            g.setColor(color);
            g.fillRect(0, 0, game.width, game.height);
            startAlpha -= 2;
        }
    }

    /**
     * generates the Bocks in the World
     */
    public void generateBlocks() {
        int BlockX = game.width / 2 - 500, BlockY = game.height - 100;

        for (int i = 0; i < 20; i++) {
            StoneBlock z = new StoneBlock(game, BlockX, BlockY);
            ArrayLists.solidBlocks.add(z);
            BlockX = BlockX + 64;
        }
        BlockX -= 64;
        BlockY = BlockY - 64;
        for (int i = 0; i < 4; i++) {
            StoneBlock z = new StoneBlock(game, BlockX, BlockY);
            ArrayLists.solidBlocks.add(z);
            BlockY = BlockY - 64;
        }
        for (int i = 0; i < 21; i++) {
            StoneBlock z = new StoneBlock(game, BlockX, BlockY);
            ArrayLists.solidBlocks.add(z);
            BlockX = BlockX - 64;
        }
        BlockX = BlockX + 64;
        BlockY = BlockY + 64;
        for (int i = 0; i < 5; i++) {
            StoneBlock z = new StoneBlock(game, BlockX, BlockY);
            ArrayLists.solidBlocks.add(z);
            BlockY = BlockY + 64;
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