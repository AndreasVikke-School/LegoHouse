package logic;

import data.models.BrickLayer;
import data.models.BrickSide;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public class BrickCalculatorYellow {
    
    public static List<BrickLayer> calcBricks(int length, int width, int height) {
        List<BrickLayer> brickLayers = new ArrayList();
        
        for(int i = 1; i <= height; i++)
            brickLayers.add(calcLayer(length, width, i));
            
        return brickLayers;
    }
    
    private static BrickLayer calcLayer(int length, int width, int layer) {
        BrickLayer brickLayer = new BrickLayer();
        boolean doorLayer, windowLayer;
        
        if(layer == 1 || layer == 2 || layer == 3)
            doorLayer = true;
        else
            doorLayer = false;
        
        if(layer == 2 || layer == 3)
            windowLayer = true;
        else
            windowLayer = false;
        
        brickLayer.addSide(calcSide(length, layer, doorLayer, windowLayer, 1));
        brickLayer.addSide(calcSide(length, layer, doorLayer, windowLayer, 2));
        brickLayer.addSide(calcSide(width, layer, doorLayer, windowLayer, 0));
        brickLayer.addSide(calcSide(width, layer, doorLayer, windowLayer, 0));
        
        return brickLayer;
    }
    
    private static BrickSide calcSide(int value, int layer, boolean doorLayer, boolean windowLayer, int type) {
        if (layer % 2 > 0 && type != 0)
            value -= 4;
        else if (layer % 2 == 0 && type == 0)
            value -= 4;
        
        if(doorLayer && type == 1)
            value -= 2;
        
         if(windowLayer && type == 2)
            value -= 2;
        
        int calc2x4 = value / 4;
        int calc2x2 = (value % 4) / 2;
        int calc2x1 = (value % 4) % 2;
        
        return new BrickSide(calc2x4, calc2x2, calc2x1);
    }
}
