package sortvisualiser.algorithms;

import sortvisualiser.SortArray;

/**
 * Quick sort implementation
 *
 * @author mhops
 */
public class QuickSort implements ISortAlgorithm {

    private long stepDelay = 30;
    /**
     * This is where the magic of quick sort append.
     *
     * @param array this is the array to cut and merge
     * @param lowIndex the most left index of the array
     * @param highIndex  the most right index of the array
     * @see SortArray
     */
    private int findPivotPoint(SortArray array, int lowIndex, int highIndex) {
        int pivotValue = array.getValue(highIndex);
        int i = lowIndex - 1;
        for (int j = lowIndex; j <= highIndex - 1; j++) {
            if (array.getValue(j) <= pivotValue) {
                i++;
                array.swap(i, j, getDelay(), true);
            }
        }
        array.swap(i + 1, highIndex, getDelay(), true);
        return i + 1;
    }

    /**
     * This is the core of the algorithm quick sort.
     *
     * @param array this is the array to cut and merge
     * @param lowIndex the most left index of the array
     * @param highIndex  the most right index of the array
     * @see SortArray
     */
    private void quickSort(SortArray array, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int pivotPoint = findPivotPoint(array, lowIndex, highIndex);
            quickSort(array, lowIndex, pivotPoint - 1);
            quickSort(array, pivotPoint + 1, highIndex);
        }
    }

    /**
     * This is the method that call the first instance of quickSort, see
     * <a href="https://en.wikipedia.org/wiki/Quicksort">Quicksort</a> to understand more.
     * The method takes a SortArray object called array and sorts his elements according to the mathematical theory
     * of the order "less than", see <a href="https://en.wikipedia.org/wiki/Order_theory">Order_theory</a> to
     * understand more.
     *
     * @param array the array to be sorted
     * @see SortArray
     */
    @Override
    public void runSort(SortArray array) {
        quickSort(array, 0, array.arraySize() - 1);
    }

    @Override
    public String getName() {
        return "Quick Sort";
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
