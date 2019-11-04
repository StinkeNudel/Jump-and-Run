package Entity;

public abstract class Creature extends Entity {
    int health;
    public Creature(double x, double y) {
        super(x, y);
        int health = 1;
    }
}
