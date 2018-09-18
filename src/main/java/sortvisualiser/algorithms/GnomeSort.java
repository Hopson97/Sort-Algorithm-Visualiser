package sortvisualiser.algorithms;

import sortvisualiser.SortArray;

/**
 * Gnome sort implementation
 *
 * @author Matthew Hopson
 */
 public class GnomeSort implements ISortAlgorithm {

    private long stepDelay = 2;
    /**
     * This method implements the gnome sort algorithm, see
     * <a href="https://en.wikipedia.org/wiki/Gnome_sort">Gnome_sort</a> to understand more.
     * Takes a SortArray object called array and sorts his elements according to the mathematical theory
     * of the order "less than", see <a href="https://en.wikipedia.org/wiki/Order_theory">Order_theory</a> to
     * understand more.
     *
     * @param array the array to be sorted
     * @see SortArray
     */
    @Override
    public void runSort(SortArray array) {
        int index = 0;
        while (index < array.arraySize()) {
            if (index == 0) {
                index++;
            }
            if (array.getValue(index) >= array.getValue(index - 1)) {
                index++;
            } else {
                array.swap(index, index - 1, getDelay(), true);
                index--;
            }
        }
    }

    @Override
    public String getName() {
        return "Gnome sort";
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
