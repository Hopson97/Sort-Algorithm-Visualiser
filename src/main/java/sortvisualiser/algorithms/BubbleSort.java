package sortvisualiser.algorithms;

import sortvisualiser.SortArray;

/**
 * Bubble sort implementation
 *
 * @author mhops
 */
public class BubbleSort implements ISortAlgorithm {

    private long stepDelay = 2;
    /**
     * This method implements the bubble sort algorithm, see
     * <a href="https://en.wikipedia.org/wiki/Bubble_sort">Bubble_sort</a> to understand more.
     * Takes a SortArray object called array and sorts his elements according to the mathematical theory
     * of the order "less than", see <a href="https://en.wikipedia.org/wiki/Order_theory">Order_theory</a> to
     * understand more.
     *
     * @param array the array to be sorted
     * @see SortArray
     */
    @Override
    public void runSort(SortArray array) {
        int len = array.arraySize();
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array.getValue(j) > array.getValue(j + 1)) {
                    array.swap(j, j + 1, getDelay(), true);
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
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) {
        this.stepDelay = delay;
    }
}