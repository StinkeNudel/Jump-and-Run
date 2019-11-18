package Entity;

import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Letter extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/Scroll_Open-12.png.png");

    private Game game;
    private int scrollImage;
    private int w = 104;
    private int h = 128;



    public Letter(Game game, double x, double y) {
        super(x, y);
        this.game = game;
    }

    public void tick() {
        move();
    }

    public void render(Graphics g) {

        g.drawImage(image, (int) x, (int) y, w, h, null);
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
                    image = ImageLoader.loadImage("/Scroll_Open-11.png.png");
                    scrollImage++;
                    break;
                case 1:
                    w = 156;
                    h = 192;
                    image = ImageLoader.loadImage("/Scroll_Open-10.png.png");
                    scrollImage++;
                    break;
                case 2:
                    w = 182;
                    h = 224;
                    image = ImageLoader.loadImage("/Scroll_Open-9.png.png");
                    scrollImage++;
                    break;
                case 3:
                    w = 208;
                    h = 256;
                    image = ImageLoader.loadImage("/Scroll_Open-8.png.png");
                    scrollImage++;
                    break;
                case 4:
                    w = 234;
                    h = 288;
                    image = ImageLoader.loadImage("/Scroll_Open-7.png.png");
                    scrollImage++;
                    break;
                case 5:
                    w = 260;
                    h = 320;
                    image = ImageLoader.loadImage("/Scroll_Open-6.png.png");
                    scrollImage++;
                    break;
                case 6:
                    w = 286;
                    h = 352;
                    image = ImageLoader.loadImage("/Scroll_Open-5.png.png");
                    scrollImage++;
                    break;
                case 7:
                    w = 312;
                    h = 384;
                    image = ImageLoader.loadImage("/Scroll_Open-4.png.png");
                    scrollImage++;
                    break;
                case 8:
                    w = 338;
                    h = 416;
                    image = ImageLoader.loadImage("/Scroll_Open-3.png.png");
                    scrollImage++;
                    break;
                case 9:
                    w = 364;
                    h = 448;
                    image = ImageLoader.loadImage("/Scroll_Open-2.png.png");
                    scrollImage++;
                    break;
                case 10:
                    w = 390;
                    h = 480;
                    image = ImageLoader.loadImage("/Scroll_Open-1.png.png");
                    scrollImage++;
                    break;
            }
        }
    }

}

