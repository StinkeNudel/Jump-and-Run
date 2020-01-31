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

    public Letter(Game game, double x, double y) {
        super(game, x, y);
        this.game = game;
        width = 104;
        height = 128;
    }

    public void tick() {

        move();
        readMe();
    }

    public void render(Graphics g) {

        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
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
                    width = 130;
                    height = 160;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-11.png.png");
                    scrollImage++;
                    break;
                case 1:
                    width = 156;
                    height = 192;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-10.png.png");
                    scrollImage++;
                    break;
                case 2:
                    width = 182;
                    height = 224;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-9.png.png");
                    scrollImage++;
                    break;
                case 3:
                    width = 208;
                    height = 256;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-8.png.png");
                    scrollImage++;
                    break;
                case 4:
                    width = 234;
                    height = 288;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-7.png.png");
                    scrollImage++;
                    break;
                case 5:
                    width = 260;
                    height = 320;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-6.png.png");
                    scrollImage++;
                    break;
                case 6:
                    width = 286;
                    height = 352;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-5.png.png");
                    scrollImage++;
                    break;
                case 7:
                    width = 312;
                    height = 384;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-4.png.png");
                    scrollImage++;
                    break;
                case 8:
                    width = 338;
                    height = 416;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-3.png.png");
                    scrollImage++;
                    break;
                case 9:
                    width = 364;
                    height = 448;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-2.png.png");
                    scrollImage++;
                    break;
                case 10:
                    width = 390;
                    height = 480;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-1.png.png");
                    scrollImage++;
                    break;
            }
        }
    }

    public void readMe () {
       if (x <= MouseHandler.clickX && x + width >= MouseHandler.clickX) {
       System.out.println("X");
       if (
        y - height /2 <= MouseHandler.clickY && y + height >= MouseHandler.clickY) {
           System.out.println("Y");
           ScrollWorld scrollWorld = new ScrollWorld(game);
           World.setWorld(scrollWorld);
           MouseHandler.resetClicks();
       }
       }
    }

}

