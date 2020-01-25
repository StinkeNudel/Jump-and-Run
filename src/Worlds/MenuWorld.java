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

    private BufferedImage Image; //background image
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
        //render background
        g.drawImage(Image, 0, 0, null);

        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            m.render(g);
        }

        //render startButton
        g.setColor(Color.BLACK);
        g.fillRect(game.width / 2 - 200, game.height / 2 - 50, 400, 100);
        g.setColor(Color.WHITE);
        g.drawString("TestWorld", game.width / 2 - 20, game.height / 2);

        //render newGameButton
        g.setColor(Color.BLACK);
        g.fillRect(game.width / 2 - 200, game.height / 2 + 100, 400, 100);
        g.setColor(Color.WHITE);
        g.drawString("New Game", game.width / 2 - 20, game.height / 2 + 150);

        //render loadButton
        g.setColor(Color.BLACK);
        g.fillRect(game.width / 2 - 200, game.height / 2 + 250, 400, 100);
        g.setColor(Color.WHITE);
        g.drawString("Load Game", game.width / 2 - 20, game.height / 2 + 300);


    }

    /**
     * loads the SafeFile
     */
    private void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\Workspace\\Jump-and-Run\\src\\SaveFile"));
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

        if (MouseHandler.clickX > game.width / 2 - 200 && MouseHandler.clickX < game.width / 2 - 200 + 400 && MouseHandler.clickY > game.height / 2 - 50 && MouseHandler.clickY < game.height / 2 - 50 + 100) {
            TestWorld testWorld = new TestWorld(game);
            setWorld(testWorld);
            MouseHandler.resetClicks();
        }

        if (MouseHandler.clickX > game.width / 2 - 200 && MouseHandler.clickX < game.width / 2 - 200 + 400 && MouseHandler.clickY > game.height / 2 + 100 && MouseHandler.clickY < game.height / 2 + 100 + 100) {
            SaveWorld tutorial = new SaveWorld(game);
            setWorld(tutorial);
            MouseHandler.resetClicks();
        }

        if (MouseHandler.clickX > game.width / 2 - 200 && MouseHandler.clickX < game.width / 2 - 200 + 400 && MouseHandler.clickY > game.height / 2 + 250 && MouseHandler.clickY < game.height / 2 + 250 + 100) {
            loadFile();
            switch (checkpoint) {
                case 0:
                    Tutorial tutorial = new Tutorial(game);
                    setWorld(tutorial);
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



