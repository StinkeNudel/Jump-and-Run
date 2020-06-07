package Worlds;

import Background.*;
import Blocks.*;
import Entity.*;
import Entity.Doors.DoorSaveRoom;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TestWorld extends World {

    private final Player player;
    private final Worm worm;

    private final DoorSaveRoom doorSaveRoom;
    private final Item key;
    private final Item shoe;
    private Schwurbel schwurbel;


    /**
     * Constructor
     *
     * @param game Game Object
     */
    public TestWorld(Game game) {
        super(game);
        loadFile();

        player = new Player(game, game.width / 2, game.height - (int) (Game.blockSize * 3.453125));
        ArrayLists.player.add(player);

        worm = new Worm(game, game.width / 2 + (int) (Game.blockSize * 7.8125), game.height - (int) (Game.blockSize * 7.8125));
        ArrayLists.enemys.add(worm);


        doorSaveRoom = new DoorSaveRoom(game, game.width / 2, game.height - (int) (Game.blockSize * 3.515625));
        game.getGameCamera().setyOffset((int) (Game.blockSize * 4.6875));

        key = new Item(game, game.width / 2 + (int) (Game.blockSize * 2.34375), game.height - (int) (Game.blockSize * 2.8125), "key");
        ArrayLists.items.add(key);

        shoe = new Item(game, game.width / 2 + (int) (Game.blockSize * 3.90625), game.height - (int) (Game.blockSize * 2.8125), "shoe");
        ArrayLists.items.add(shoe);

        saveGame();
    }


    /**
     * ticks the world
     */
    public void tick() {
        player.tick();
        worm.tick();
        doorSaveRoom.tick();

        ArrayList schwurbels = ArrayLists.schwurbels;
        for (int w = 0; w < schwurbels.size(); w++) {
            Schwurbel q = (Schwurbel) schwurbels.get(w);
            q.tick();
        }
    }

    /**
     * renders the world
     *
     * @param g Graphics Object
     */
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 10000, 10000);
        worm.render(g);
        doorSaveRoom.render(g);
        worm.render(g);
        player.render(g);
        renderBlocks(g);

        ArrayList schwurbels = ArrayLists.schwurbels;
        for (int w = 0; w < schwurbels.size(); w++) {
            Schwurbel q = (Schwurbel) schwurbels.get(w);
            q.render(g);
        }

        ArrayList items = ArrayLists.items;
        for (int w = 0; w < items.size(); w++) {
            Item q = (Item) items.get(w);
            q.render(g);
        }
    }

    private void renderBlocks(Graphics g) {
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            if (m.x > player.x - 1100 && m.x < player.x + 1100) {
                m.render(g);
            }
        }
    }

    public void schwurbelnator() {
        ArrayList schwurbels = ArrayLists.getSchwurbels();
        int schwurbelX = game.width / 2 + (int)(game.blockSize*3.125), schwurbelY = game.height - (int)(game.blockSize*3.453125), idk;

        Random random = new Random();

        idk = random.nextInt(30);
        for (int howToSchwurbel = 0; howToSchwurbel < 300; howToSchwurbel++) {
            schwurbel = new Schwurbel(game, schwurbelX + idk, schwurbelY + idk);
            schwurbels.add(schwurbel);
            idk = random.nextInt(30);
        }
    }

    private void loadFile() {
        File file = new File("res\\Worlds\\TestWorld");
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