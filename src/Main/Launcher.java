package Main;

import GFX.GameCamera;
import Worlds.MenuWorld;
import Display.Display;
import Input.KeyHandler;
import Worlds.SaveWorld;
import Worlds.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Launcher implements Runnable {

    private Display display; //JFrame and Canvas
    public int width, height; //with and height of the game
    public String title; //title of the Window

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;//Graphics object


    public static int FPS;

    private KeyHandler keyHandler; //KeyListener


    /**
     * Constructor
     *
     * @param title  title of the Window
     * @param width  with of the game
     * @param height height of the game
     */
    public Launcher(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyHandler = new KeyHandler();
    }


    /**
     * ticks the Main.Game
     */
    private void tick() {
        keyHandler.tick();
        input();
        if (World.getWorld() != null)
            World.getWorld().tick();
    }

    /**
     * renders the Main.Game
     */
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!
        if (World.getWorld() != null) {
            World.getWorld().render(g);
        }
        g.setColor(Color.red);
        g.drawString(String.valueOf(FPS), 10, 10);
        g.setColor(Color.black);
        g.drawString("press A for big window", 580, 50);
        g.drawString("press S for small window", 580, 100);
        //EndDrawing

        bs.show();
        g.dispose();
    }

    private void input() {
        if (keyHandler.a) {
            Game game = new Game("SCHLRP",1920,1080);
            game.start();
            display.closeFrame();
            this.stop();
        }

        if (keyHandler.s) {
            Game game = new Game("SCHLRP",1280,720);
            game.start();
            display.closeFrame();
            this.stop();
        }
    }

    /**
     * initial Method
     */
    private void init() {
        display = new Display(title, width, height); //creates Display
        display.getFrame().addKeyListener(keyHandler); //adds KeyListener
    }

    /**
     * game loop
     */
    public void run() {
        init();
        int ticks = 0;
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                FPS = ticks;
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
