package Entity;

import GFX.ImageLoader;
import Input.MouseHandler;
import Main.Game;
import Worlds.ScrollWorld;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Letter extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/Scroll/Scroll_Open-12.png.png");

    private Game game;
    private int scrollImage;
    private int w = 104;
    private int h = 128;



    public Letter(Game game, double x, double y) {
        super(game, x, y);
        this.game = game;
    }

    public void tick() {

        move();
        readMe();
    }

    public void render(Graphics g) {

        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), w, h, null);
    }

    public void move() {

        /**
         * Scroll fliegt in die Mitte und rollt sich dann aus
         */
        if (x >= game.width / 2 - 195) {
            x = x - 4;
        } else {
            switch (scrollImage) { //switch ist für die Scroll-Animation
                case 0:
                    w = 130;
                    h = 160;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-11.png.png");
                    scrollImage++;
                    break;
                case 1:
                    w = 156;
                    h = 192;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-10.png.png");
                    scrollImage++;
                    break;
                case 2:
                    w = 182;
                    h = 224;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-9.png.png");
                    scrollImage++;
                    break;
                case 3:
                    w = 208;
                    h = 256;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-8.png.png");
                    scrollImage++;
                    break;
                case 4:
                    w = 234;
                    h = 288;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-7.png.png");
                    scrollImage++;
                    break;
                case 5:
                    w = 260;
                    h = 320;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-6.png.png");
                    scrollImage++;
                    break;
                case 6:
                    w = 286;
                    h = 352;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-5.png.png");
                    scrollImage++;
                    break;
                case 7:
                    w = 312;
                    h = 384;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-4.png.png");
                    scrollImage++;
                    break;
                case 8:
                    w = 338;
                    h = 416;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-3.png.png");
                    scrollImage++;
                    break;
                case 9:
                    w = 364;
                    h = 448;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-2.png.png");
                    scrollImage++;
                    break;
                case 10:
                    w = 390;
                    h = 480;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-1.png.png");
                    scrollImage++;
                    break;
            }
        }
    }

    public void readMe () {
       if (x <= MouseHandler.clickX && x + w >= MouseHandler.clickX) {
       System.out.println("X");
       if (
        y - h/2 <= MouseHandler.clickY && y + h >= MouseHandler.clickY) {
           System.out.println("Y");
           ScrollWorld scrollWorld = new ScrollWorld(game);
           World.setWorld(scrollWorld);
           MouseHandler.resetClicks();
       }
       }
    }

}

