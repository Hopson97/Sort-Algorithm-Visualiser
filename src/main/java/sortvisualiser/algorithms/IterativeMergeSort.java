package sortvisualiser.algorithms;
import sortvisualiser.SortArray;

/**
 * Iterative Merge sort implementation
 *
 * @author Randy Bushman
 */
public class IterativeMergeSort implements ISortAlgorithm
{
	private long stepDelay = 5;
	
	/**
	 * This is an implementation of Merge Sort using Iteration instead of Recursion.
	 * It works by splitting the array into small subsequences. These subsequences are
	 * then merged into larger sorted arrays. In the end, there will be one big sorted array.
	 * @param array the array to be sorted
	 * @see SortArray
	 */
	@Override
	public void runSort(SortArray array) 
	{
		for(int exp = 1; exp < array.arraySize(); exp <<= 1)
			for(int k = 0, j = exp+exp, s = array.arraySize()-exp; k<s; k+=j)
				merge(array, k, exp);
	}
	
	/**
	 * Returns a subsequence of the array taken from input. The original array is cut starting
	 * from start, and ending at end.
	 * @param array Given array to cut
	 * @param start The starting position of the subsequence (inclusive)
	 * @param end The ending position of the subsequence (exclusive)
	 * @return The requested subsequence of the array
	 */
	private int[] getSubArray(SortArray array, int start, int end)
	{
		int size = end - start;
		int arr[] = new int[size];
		for (int i = 0; i < size; i++) 
			arr[i] = array.getValue(start + i);		
		return arr;
	}
	
	/**
	 * Merges two sorted subsequences of the array into one big sorted subsequence
	 * @param arr The array being sorted
	 * @param start The start of the subsequence
	 * @param exp The current exponent, and the size of the subsequences
	 */
	private void merge(SortArray arr, int start, int exp)
	{
		int s = start;
		int m = start + exp;
		int end = (arr.arraySize() < m+exp) ? arr.arraySize() : m + exp;				
		int[] leftArr = getSubArray(arr, s, m);
		int[] rightArr = getSubArray(arr, m, end);
		int i = 0, j = 0;
		while(i < leftArr.length && j < rightArr.length)
			if(leftArr[i] <= rightArr[j])
				arr.updateSingle(start++, leftArr[i++], getDelay(), true);
			else
				arr.updateSingle(start++, rightArr[j++], getDelay(), true);
		while (i < leftArr.length)
			arr.updateSingle(start++, leftArr[i++], getDelay(), true);
		while (j < leftArr.length)
			arr.updateSingle(start++, rightArr[j++], getDelay(), true);		
	}	

	@Override
	public String getName() {
		return "Iterative Merge Sort";
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
