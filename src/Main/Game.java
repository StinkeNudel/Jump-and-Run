package Main;

import GFX.GameCamera;
import Worlds.MenuWorld;
import Display.Display;
import Input.KeyHandler;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private Display display; //JFrame and Canvas
    public int width, height; //with and height of the game
    public String title; //title of the Window

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;//Graphics object

    public static int FPS;

    private KeyHandler keyHandler; //KeyListener

    private GameCamera gameCamera;

    //Worlds
    private World gameWorld;
    public World menuWorld;

    /**
     * Constructor
     *
     * @param title  title of the Window
     * @param width  with of the game
     * @param height height of the game
     */
    public Game(String title, int width, int height) {
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
        //EndDrawing

        bs.show();
        g.dispose();
    }


    /**
     * initial Method
     */
    private void init() {
        display = new Display(title, width, height); //creates Display
        display.getFrame().addKeyListener(keyHandler); //adds KeyListener

        gameCamera = new GameCamera(0, 0);

        ArrayLists arrayLists = new ArrayLists(); //creates ArrayLists object
        menuWorld = new MenuWorld(this); //creates MenuWorld
        World.setWorld(menuWorld); //sets World to MenuWorld

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
                System.out.println("FPS: " + ticks);
                FPS = ticks;
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }


    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
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
