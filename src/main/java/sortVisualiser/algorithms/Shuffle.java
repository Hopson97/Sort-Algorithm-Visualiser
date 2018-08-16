package sortVisualiser.algorithms;

import java.util.Random;
import sortVisualiser.SortArray;

/**
 *
 * @author mhops
 */
public class Shuffle implements ISortAlgorithm
{

    @Override
    public void runSort(SortArray array) {
        Random rng = new Random();
        for (int i = 0; i < array.arraySize(); i++) {
            int swapWithIndex = rng.nextInt(array.arraySize() - 1);
            array.swapUpdate(i, swapWithIndex);
        }
    }

}
