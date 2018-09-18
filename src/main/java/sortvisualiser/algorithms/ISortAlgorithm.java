package sortvisualiser.algorithms;

import sortvisualiser.SortArray;

/**
 * Base interface for the sort algorithms
 *
 * @author Matt Hopson
 */
public interface ISortAlgorithm {
    public String getName();

    public long getDelay();

    public void setDelay(long delay);

    public void runSort(SortArray array);
}
