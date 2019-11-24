package GFX;

public class GameCamera {

    private float xOffset, yOffset;

    /**
     * Constructor
     */
    public GameCamera(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
    }

    /**
     * setter methods for xOffset and yOffset
     *
     * @param xOffset
     */
    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }


    /**
     * getter methods for xOffset and yOffset
     *
     * @return
     */
    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }


}
