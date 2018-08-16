package sortVisualiser.algorithms;

import sortVisualiser.SortArray;

/**
 * Bubble sort implementation
 * @author mhops
 */
public class BubbleSort implements ISortAlgorithm
{
    @Override
    public void runSort(SortArray array) {
        int len = array.arraySize();
        for(int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array.getValue(j) > array.getValue(j + 1)) {
                    array.swap(j, j + 1, 2);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
    public long getDelay() {
        return 2;
    }
}