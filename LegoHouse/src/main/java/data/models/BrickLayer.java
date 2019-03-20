package data.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public class BrickLayer {
    private List<BrickSide> sides;

    public BrickLayer() {
        sides = new ArrayList();
    }

    public List<BrickSide> getSides() {
        return sides;
    }

    public void addSide(BrickSide side) {
        sides.add(side);
    }    
}
