package sortVisualiser.algorithms;

import sortVisualiser.SortArray;

/**
 * Selection sort implementation
 * @author Matt Hopson
 */
public class SelectionSort implements ISortAlgorithm
{
    @Override
    public void runSort(SortArray array) {
        int len = array.arraySize();
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array.getValue(j) < array.getValue(minIndex)) {
                    minIndex = j;
                }
            }
            array.swap(i, minIndex, 40);
        }
    }

    @Override
    public String getName() {
        return "Selection Sort";
    }

    @Override
    public long getDelay() {
        return 40;
    }
}
