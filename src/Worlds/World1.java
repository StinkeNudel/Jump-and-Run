package Worlds;

import Blocks.Block;
import Blocks.SolidBlocks;
import Entity.Player;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class World1 extends World {
    Player player;
    private int checkpoint;

    public World1(Game game) {
        super(game);
        loadFile();

        player = new Player(game, 0 + game.blockSize, game.height - 10 * game.blockSize);
        ArrayLists.player.add(player);
    }

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        renderBlocks(g);
        player.render(g);
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

    private void loadFile() {
        File file = new File("res\\Worlds\\World1");
        width = 96;
        height = 24;
        String type;
        Scanner s;

        try {
            s = new Scanner(file);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    type = s.next();
                    if (type.equals("N")) {
                        //NOTHING
                    } else {
                        Block b = new Block(game, x * game.blockSize, y * game.blockSize, type);
                        ArrayLists.solidBlocks.add(b);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

