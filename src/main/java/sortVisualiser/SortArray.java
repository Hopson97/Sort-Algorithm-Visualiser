package sortVisualiser;

import java.util.Random;

/**
 * The array that can be sorted
 * @author mhops
 */
public class SortArray 
{
    private int[] array;
    public SortArray(int len) {
        array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = i;
        }
        shuffleArray();
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
}
