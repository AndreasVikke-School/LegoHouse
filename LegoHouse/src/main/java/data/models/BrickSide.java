package data.models;

/**
 *
 * @author Andreas Vikke
 */
public class BrickSide {
    private int bricks2x4;
    private int bricks2x2;
    private int bricks2x1;

    public BrickSide(int bricks2x4, int bricks2x2, int bricks2x1) {
        this.bricks2x4 = bricks2x4;
        this.bricks2x2 = bricks2x2;
        this.bricks2x1 = bricks2x1;
    }

    public int getBricks2x4() {
        return bricks2x4;
    }

    public int getBricks2x2() {
        return bricks2x2;
    }

    public int getBricks2x1() {
        return bricks2x1;
    }
}
