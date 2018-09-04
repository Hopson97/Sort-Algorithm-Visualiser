package sortVisualiser.algorithms;

import sortVisualiser.SortArray;

/**
 *
 * @author Matthew Hopson
 */
public class GnomeSort implements ISortAlgorithm {
    @Override
    public void runSort(SortArray array) {
        int index = 0;
        while (index < array.arraySize()) {
            if (index == 0) {
                index++;
            }
            if (array.getValue(index) >= array.getValue(index - 1)) {
                index++;
            }
            else {
                array.swap(index, index - 1, getDelay());
                index--;
            }
        }
    }

    @Override
    public String getName() {
        return "Shell sort";
    }

    @Override
    public long getDelay() {
        return 5;
    }
}
