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

    public void runSort(SortArray array);
}
