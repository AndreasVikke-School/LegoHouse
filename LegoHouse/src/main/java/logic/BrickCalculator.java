package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public class BrickCalculator {
    
    private static HashMap<String, Integer> lengthBricks;
    private static HashMap<String, Integer> widthBricks;
    
    public static List<HashMap<String, Integer>> calculateBricks(int length, int width, int height) {
        lengthBricks = new  HashMap();
        widthBricks = new  HashMap();
        calculateLength(length);
        calculateWidth(width);
        List<HashMap<String, Integer>> bricks = new ArrayList();
        bricks.add(lengthBricks);
        bricks.add(widthBricks);
        return bricks;
    }
    
    private static void calculateLength(int length) {
        for(int i = 0; i < length;) {
            if(i + 4 <= length) {
                int oldLengthVal = lengthBricks.getOrDefault("2x4", 0);
                lengthBricks.put("2x4", ++oldLengthVal);
                i += 4;
            } else if(i + 2 <= length) {
                int oldLengthVal = lengthBricks.getOrDefault("2x2", 0);
                lengthBricks.put("2x2", ++oldLengthVal);
                i += 2;
            } else {
                int oldLengthVal = lengthBricks.getOrDefault("2x1", 0);
                lengthBricks.put("2x1", ++oldLengthVal);
                i += 1;
            }
        }
    }
    
    private static void calculateWidth(int width) {
        width -= 4;
        for(int i = 0; i < width;) {
            if(i + 4 <= width) {
                int oldLengthVal = widthBricks.getOrDefault("2x4", 0);
                widthBricks.put("2x4", ++oldLengthVal);
                i += 4;
            } else if(i + 2 <= width) {
                int oldLengthVal = widthBricks.getOrDefault("2x2", 0);
                widthBricks.put("2x2", ++oldLengthVal);
                i += 2;
            } else {
                int oldLengthVal = widthBricks.getOrDefault("2x1", 0);
                widthBricks.put("2x1", ++oldLengthVal);
                i += 1;
            }
        }
    }
}
