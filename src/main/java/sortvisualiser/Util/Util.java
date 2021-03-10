package sortvisualiser.Util;

import sortvisualiser.SortArray;

public class Util {
    /** find the index of the max value up to a given index */
    public static int findMaxValueIndex(SortArray array, int upToIndex) {
        int maxIndex = 0;
        for (int i = 0; i < upToIndex; i++) {
            if (array.getValue(i) > array.getValue(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    /** find the index of the max value */
    public static int findMaxValueIndex(SortArray array) {
        int maxIndex = 0;
        for (int i = 0; i < array.arraySize(); i++) {
            if (array.getValue(i) > array.getValue(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    /** find the index of the min value */
    public static int findMinValueIndex(SortArray array) {
        int minIndex = 0;
        for (int i = 0; i < array.arraySize(); i++) {
            if (array.getValue(i) < array.getValue(minIndex)) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}