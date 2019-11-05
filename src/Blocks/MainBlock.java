package Blocks;

import Entity.Entity;

public abstract class MainBlock extends Entity {
    boolean solid = false;

    public MainBlock(double x, double y) {
        super(x, y);
    }
}
