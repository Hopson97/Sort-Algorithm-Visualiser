package sortVisualiser.algorithms;

import sortVisualiser.SortArray;

/**
 * Selection sort implementation
 *
 * @author Matt Hopson
 */
public class SelectionSort implements ISortAlgorithm {

    /**
     * This method implements the Selection sort algorithm, see
     * <a href="https://en.wikipedia.org/wiki/Selection_sort">Selection_sort</a> to understand more.
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
