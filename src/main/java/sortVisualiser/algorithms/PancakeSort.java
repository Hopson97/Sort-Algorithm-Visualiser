package sortvisualiser.algorithms;

import sortvisualiser.SortArray;
import sortvisualiser.util.Util;

public class PancakeSort implements ISortAlgorithm {
    private void flip(SortArray array, int i) {
        for (int j = 0; j < i; j++, i--) {
            array.swap(i, j, getDelay(), true);
        }
    }

    @Override
    public void runSort(SortArray array) {
        for (int i = array.arraySize(); i > 1; i--) {
            int maxValueIndex = Util.findMaxValueIndex(array, i);
            if (maxValueIndex != i - 1) {
                flip(array, maxValueIndex);
                flip(array, i - 1);
            }
        }
    }

    @Override
    public String getName() {
        return "Pancake sort";
    }

    @Override
    public long getDelay() {
        return 1;
    }
}