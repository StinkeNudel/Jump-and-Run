package Worlds;

import Blocks.BackBlock;
import Blocks.Block;
import Blocks.Ladder;
import Blocks.SolidBlocks;
import Entity.Player;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level2 extends World {
    private final Player player;
    int ladderY = game.blockSize*11;

    public Level2(Game game) {
        super(game);
        loadFile();
        player = new Player(game, 2*game.blockSize, 0);

        for (int i = 0; i < 5; i++) {
            Ladder z = new Ladder(game, game.blockSize*49, ladderY, "ladder");
            ArrayLists.backBlocks.add(z);
            ladderY = ladderY + Game.blockSize;
        }
    }

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
        renderBlocks(g);
    }

    private void renderBlocks(Graphics g) {
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            if (m.x > player.x - 1100 && m.x < player.x + 1100) {
                m.render(g);
            }
        }

        ArrayList backBlocks = ArrayLists.getBackBlocks();
        for (int w = 0; w < backBlocks.size(); w++) {
            BackBlock m = (BackBlock) backBlocks.get(w);
            if (m.x > player.x - 1100 && m.x < player.x + 1100) {
                m.render(g);
            }
        }
    }

    private void loadFile() {
        File file = new File("res\\Worlds\\Level2");
        width = 96;
        height = 43;
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
}
