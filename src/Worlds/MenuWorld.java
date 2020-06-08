package Worlds;

import Blocks.SolidBlocks;
import Entity.Player;
import Input.MouseHandler;
import Main.ArrayLists;
import Main.Game;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MenuWorld extends World {

    private Player player;
    private MouseHandler mouseHandler;
    private int checkpoint;

    /**
     * Constructor
     */
    public MenuWorld(Game game) {
        super(game);
    }

    /**
     * World tick
     */
    @Override
    public void tick() {
        input();
    }

    /**
     * World render
     */
    @Override
    public void render(Graphics g) {

        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            m.render(g);
        }

        //render startButton
        g.setColor(Color.BLACK);
        g.fillRect(game.width / 2 - (int)(game.blockSize*3.125), game.height / 2 - (int)(game.blockSize*0.78125), (int)(game.blockSize*6.25), (int)(game.blockSize*1.5625));
        g.setColor(Color.WHITE);
        g.drawString("TestWorld", game.width / 2 - (int)(game.blockSize*0.3125), game.height / 2);

        //render newGameButton
        g.setColor(Color.BLACK);
        g.fillRect(game.width / 2 - (int)(game.blockSize*3.125), game.height / 2 + (int)(game.blockSize*1.5625), (int)(game.blockSize*6.25), (int)(game.blockSize*1.5625));
        g.setColor(Color.WHITE);
        g.drawString("New Game", game.width / 2 - (int)(game.blockSize*0.3125), game.height / 2 + (int)(game.blockSize*2.34375));

        //render loadButton
        g.setColor(Color.BLACK);
        g.fillRect(game.width / 2 - (int)(game.blockSize*3.125), game.height / 2 + (int)(game.blockSize*3.90625), (int)(game.blockSize*6.25), (int)(game.blockSize*1.5625));
        g.setColor(Color.WHITE);
        g.drawString("Load Game", game.width / 2 - (int)(game.blockSize*0.3125), game.height / 2 + (int)(game.blockSize*4.6875));

        g.setColor(Color.black);
        g.fillRect((int)(game.blockSize*1.5625), (int)(game.blockSize*1.5625), (int) Game.blockSize, (int) game.blockSize);
    }

    /**
     * loads the SafeFile
     */
    private void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\SaveFile"));
            checkpoint = Integer.parseInt(br.readLine());
            System.out.println("yo" + checkpoint);
            br.close();
        } catch (Exception e) {
        }
    }

    /**
     * KeyInput
     */
    public void input() {

        if (MouseHandler.clickX > game.width / 2 - (int)(game.blockSize*3.125) && MouseHandler.clickX < game.width / 2 + (int)(game.blockSize*3.125) && MouseHandler.clickY > game.height / 2 - 50 && MouseHandler.clickY < game.height / 2 + (int)(game.blockSize*0.78125)) {
            ArrayLists.trees.clear();
            ArrayLists.cloud1s.clear();
            ArrayLists.cloud2s.clear();
            ArrayLists.mountains.clear();
            TestWorld testWorld = new TestWorld(game);
            setWorld(testWorld);
            MouseHandler.resetClicks();
        }

        if (MouseHandler.clickX > game.width / 2 - (int)(game.blockSize*3.125) && MouseHandler.clickX < game.width / 2 + (int)(game.blockSize*3.125) && MouseHandler.clickY > game.height / 2 + (int)(game.blockSize*1.5625) && MouseHandler.clickY < game.height / 2 + (int)(game.blockSize*3.125)) {
            ArrayLists.trees.clear();
            ArrayLists.cloud1s.clear();
            ArrayLists.cloud2s.clear();
            ArrayLists.mountains.clear();
            World1 tutorial = new World1(game);
            setWorld(tutorial);
            MouseHandler.resetClicks();
        }

        if (MouseHandler.clickX > game.width / 2 - (int)(game.blockSize*3.125) && MouseHandler.clickX < game.width / 2 +(int)(game.blockSize*3.125) && MouseHandler.clickY > game.height / 2 + (int)(game.blockSize*3.90625) && MouseHandler.clickY < game.height / 2 + (int)(game.blockSize*5.46875)) {
            ArrayLists.trees.clear();
            ArrayLists.cloud1s.clear();
            ArrayLists.cloud2s.clear();
            ArrayLists.mountains.clear();
            loadFile();
            switch (checkpoint) {
                case 0:
                    World1 world1 = new World1(game);
                    setWorld(world1);
                    break;
                case 1:
                    TestWorld testWorld = new TestWorld(game);
                    setWorld(testWorld);
                    break;
            }
            MouseHandler.resetClicks();
        }
    }
}



