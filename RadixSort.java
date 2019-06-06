package sortvisualiser.algorithms;
import java.util.Arrays;
import sortvisualiser.SortArray;

/**
 * Radix sort implementation
 *
 * @author Randy Bushman
 */
public class RadixSort implements ISortAlgorithm {

	private long stepDelay = 5;
	private int radix;	
	private int[] countingArr;

	/**
	 * @param radix The number system that you wish to work in. Must be greater than zero.
	 */
	public RadixSort(int radix)
	{
		this.radix = radix;
		countingArr = new int[radix];
	}	
	
	/**
	 * Sets Radix to 10 by default.
	 */
	public RadixSort()
	{
		this(10);
	}

	/**
	 * This is the method that call the first instance of Radix Sort.
	 * Radix Sort is a non comparison based algorithm that uses counting sort as a subroutine.
	 * It works by sorting by the least significant digit from smallest to largest. It then
	 * sorts the next least significant digit and so on. We are not limited to the decimal number 
	 * system however. We can sort in Hex, Binary, etc; hence the name Radix Sort.
	 *
	 * @param array the array to be sorted
	 * @see SortArray
	 */	
	@Override
	public void runSort(SortArray array)
	{
		int largest = array.getMaxValue();
		int[] result = new int[array.arraySize()];
		
		for(int exp = 1; largest/exp > 0; exp *= radix)		//in real life if Radix was 2, then we would bit shift.
		{
			countingArr = countingSort(array, exp);
			
			for(int i = 0; i < result.length; ++i)
				array.updateSingle(i, result[i] = array.getValue(i), getDelay(), false);				
			
			for(int i = 1; i < radix; ++i)
				countingArr[i] += countingArr[i-1];
			
			for(int i = array.arraySize() - 1; i > -1 ; --i)
				array.updateSingle(--countingArr[(result[i]/exp)%radix], result[i], getDelay(), true);	
		}		
	}

	/**
	 * Performs a Counting Sort subroutine 
	 * @param arr The array being sorted
	 * @param exp The current exponent
	 * @return A counting array that gives new indices to all values 
	 */
	private int[] countingSort(SortArray arr, int exp)
	{
		Arrays.fill(countingArr, 0);
		for(int i = 0; i < arr.arraySize(); ++i)
			countingArr[(arr.getValue(i)/exp)%radix]++;
		return countingArr;
	}

	@Override
	public String getName() {
		return "Radix Sort (Base " + radix + ")";
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
