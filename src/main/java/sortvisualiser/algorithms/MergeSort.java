package sortvisualiser.algorithms;

import sortvisualiser.SortArray;

/**
 * Merge sort implementation
 *
 * @author Matthew Hopson
 */
public class MergeSort implements ISortAlgorithm {

    private long stepDelay = 20;
    /**
     * Returns a subsequence of the array take from input. The original array is cut starting
     * from begin position indicated by the homonymous parameter up to (begin + size) position.
     *
     * @param array this is the array tu cut
     * @param begin it represents the start position of the subsequence
     * @param size  is the length of the subsequence
     * @return the subsequence of the array
     * @see SortArray
     */
    private int[] getSubArray(SortArray array, int begin, int size) {
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = array.getValue(begin + i);
        }
        return arr;
    }

    /**
     * This is the core of the algorithm merge sort,
     * take the array from input and do the cut and merge things.
     *
     * @param array  this is the array to cut and merge
     * @param left   the left index of the array
     * @param middle the middle index of the array
     * @param right  the right index of the array
     * @see SortArray
     */
    private void merge(SortArray array, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int leftArray[] = getSubArray(array, left, leftSize);
        int rightArray[] = getSubArray(array, middle + 1, rightSize);

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                array.updateSingle(k, leftArray[i], getDelay(), true);
                i++;
            } else {
                array.updateSingle(k, rightArray[j], getDelay(), true);
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array.updateSingle(k, leftArray[i], getDelay(), true);
            i++;
            k++;
        }

        while (j < rightSize) {
            array.updateSingle(k, rightArray[j], getDelay(), true);
            j++;
            k++;
        }
    }

    /**
     * Merge sort is a "divide and conquer" algorithm, it works by splitting the array into tiny sections
     * sorting them indivually, and then finally merges it back together, see
     * <a href="https://en.wikipedia.org/wiki/Merge_sort">Merge_sort</a> to understand more.
     * The method takes a SortArray object called array and sorts his elements according to the mathematical theory
     * of the order "less than", see <a href="https://en.wikipedia.org/wiki/Order_theory">Order_theory</a> to
     * understand more.
     * Recursion was adopted for simplicity
     *
     * @param array the array to be sorted
     * @param left  the left index of the array
     * @param right the right index of the array
     * @see SortArray
     */
    private void mergeSort(SortArray array, int left, int right) {
        if (left < right) {
            int middleIndex = (left + right) / 2;

            mergeSort(array, left, middleIndex);
            mergeSort(array, middleIndex + 1, right);
            merge(array, left, middleIndex, right);
        }
    }

    /**
     * This is the method that call the first instance of mergeSort.
     * Merge sort is a "divide and conquer" algorithm, it works by splitting the array into tiny sections
     * sorting them indivually, and then finally merges it back together, see
     * <a href="https://en.wikipedia.org/wiki/Merge_sort">Merge_sort</a> to understand more.
     * The method takes a SortArray object called array and sorts his elements according to the mathematical theory
     * of the order "less than", see <a href="https://en.wikipedia.org/wiki/Order_theory">Order_theory</a> to
     * understand more.
     *
     * @param array the array to be sorted
     * @see SortArray
     */
    @Override
    public void runSort(SortArray array) {
        int left = 0;
        int right = array.arraySize() - 1;
        mergeSort(array, left, right);
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public long getDelay() {
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) {
        this.stepDelay = delay;
    }
}
