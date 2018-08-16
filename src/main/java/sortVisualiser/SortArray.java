package sortVisualiser;

import java.util.Random;
import static util.Sleep.sleepFor;

/**
 * The array that can be sorted
 * @author mhops
 */
public class SortArray {
    private final ArrayCanvas arrayVisualiser;
    private int[] array;
    
    public SortArray(int len, ArrayCanvas arrayVisualiser) {
        array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = i;
        }
        shuffleArray();
        
        this.arrayVisualiser = arrayVisualiser;
    }
    
    private void shuffleArray() {
        Random rng = new Random();
        for (int i = 0; i < array.length; i++) {
            int swapWithIndex = rng.nextInt(array.length - 1);
            int temp = array[i];
            array[i] = array[swapWithIndex];
            array[swapWithIndex] = temp;
        }
    }
    
    public int size() {
        return array.length;
    }
    
    public int getValue(int index) {
        return array[index];
    }
    
    public void swapUpdate(int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        arrayVisualiser.repaint();
        sleepFor(10000);
    }
}
