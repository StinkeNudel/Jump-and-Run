package Worlds;

import Blocks.*;
import Entity.*;
import Entity.Doors.Door;
import Main.ArrayLists;
import Main.Game;
import Main.TextPrinter;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TestWorld extends World {

    private final Player player;
    private final Worm worm;

    private final Door door;
    private final Item key;
    private final Item axe;
    private Schwurbel schwurbel;
    private int textCount;
    private boolean onlyOnceText = true;
    int worldChangeAlpha = 1;
    private boolean worldChange = false;

    public WorldShifter worldShifter;
    private int startAlpha = 254;


    /**
     * Constructor
     *
     * @param game Game Object
     */
    public TestWorld(Game game) {
        super(game);
        loadFile();

        worldShifter = new WorldShifter(game, 0, 0);

        schwurbelnator();

        player = new Player(game, game.width / 2, game.height - (int) (Game.blockSize * 3.453125));
        ArrayLists.player.add(player);

        worm = new Worm(game, game.width / 2 + (int) (Game.blockSize * 7.8125), game.height - (int) (Game.blockSize * 7.8125));
        ArrayLists.enemys.add(worm);


        door = new Door(game, game.width / 2, game.height - (int) (Game.blockSize * 3.515625), "Door");
        game.getGameCamera().setyOffset((int) (Game.blockSize * 4.6875));

        key = new Item(game, game.width / 2 + (int) (Game.blockSize * 2.34375), game.height - (int) (Game.blockSize * 2.8125), "key");
        ArrayLists.items.add(key);

        axe = new Item(game, game.width / 2 + (int) (Game.blockSize * 3.90625), game.height - (int) (Game.blockSize * 2.8125), "axe");
        ArrayLists.items.add(axe);

        saveGame();
    }


    /**
     * ticks the world
     */
    public void tick() {
        player.tick();
        worm.tick();
        door.tick();
        input();

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
        worm.render(g);
        door.render(g);
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
        renderText(g);
        worldChange(g);
        startBlackScreen(g);
    }

    private void renderText(Graphics g) {
        switch (textCount) {
            case 1:
                TextPrinter.addText("Hello There", 3 * Game.blockSize, 6 * Game.blockSize, g);
                break;
            case 2:
                TextPrinter.addText("General Kenobi", 3 * Game.blockSize, 6 * Game.blockSize, g);
                break;
        }
        if (game.getKeyHandler().e && onlyOnceText) {
            textCount++;
            if (textCount >= 3) {
                textCount = 0;
            }
            TextPrinter.clearText();
            onlyOnceText = false;
        } else if (!game.getKeyHandler().e) {
            onlyOnceText = true;
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

    public void input() {
        if (player.getBounds().intersects(door.getBounds()) && game.getKeyHandler().e) {
            worldChange = true;
        }
    }

    private void worldChange(Graphics g) {
        if (worldChange) {
            if (worldChangeAlpha < 252) {
                worldChangeAlpha += 2;
            }
            Color test = new Color(0, 0, 0, worldChangeAlpha);
            g.setColor(test);
            g.fillRect(0, 0, game.width, game.height);
            if (worldChangeAlpha >= 252) {
                ArrayList solidBlocks = ArrayLists.getSolidBlocks();
                solidBlocks.clear();
                Level2 level2 = new Level2(game);
                World.setWorld(level2);
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

    public void schwurbelnator() {
        ArrayList schwurbels = ArrayLists.getSchwurbels();
        int schwurbelX = game.width / 2 + (int) (Game.blockSize * 3.125), schwurbelY = game.height - (int) (Game.blockSize * 3.453125), idk;

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
                    if (type.equals("-")) {
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