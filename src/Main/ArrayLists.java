package Main;

import java.util.ArrayList;

public class ArrayLists {
    public static ArrayList solidBlocks; //list of the blocks
    public static ArrayList player; // list of the player
    public static ArrayList enemys; //list of the enemys
    public static ArrayList cloud1s; //list of cloud1s
    public static ArrayList mountains; //list of mountains
    public static ArrayList trees; //list of trees
    public static ArrayList cloud2s; //list of cloud2s



    public ArrayLists() {
        solidBlocks = new ArrayList();
        player = new ArrayList();
        enemys = new ArrayList();
        cloud1s = new ArrayList();
        mountains = new ArrayList();
        trees = new ArrayList();
        cloud2s = new ArrayList();

    }


    public static ArrayList getSolidBlocks() {
        return solidBlocks;
    }

    public static ArrayList getPlayer() {
        return player;
    }

    public static ArrayList getEnemys() {return enemys;}
}
