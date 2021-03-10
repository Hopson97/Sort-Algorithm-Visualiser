package sortvisualiser.algorithms;

import sortvisualiser.SortArray;
import sortvisualiser.Util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Rubix sorting algorithm
 *
 * @author Anthony Michael
 */
public class StairSort implements ISortAlgorithm
{
    private long stepDelay = 5;
    private int stair;   // number of stair / bins that the algorithm will split the array into
    // larger numbers are slower, but more memory efficient

    /**
     * @param stairs The number system that you wish to work in. Must be greater than zero.
     */
    public StairSort(int stair)
    {
        this.stair = stair;
    }	

    /**
     * Sets stair to 8 by default.
     */
    public StairSort()
    {
        this(8);
    }

    @Override
    public String getName() 
    {
        return "Stair Sort";
    }

    @Override
    public long getDelay()
    {
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) 
    {       
        stepDelay = delay;
    }

    @Override
    public void runSort(SortArray array)
    {
        
        
        int n = array.arraySize(); //length of the array
        int min = array.getValue(Util.findMinValueIndex(array)); //smallest number of the array
        int max = array.getValue(Util.findMaxValueIndex(array)); //largest number of the array
        //array for backup
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = array.getValue(i);
        //things for the stair
        int[] stairSizes = new int[stair]; //the highest values each stair bin will store

        //find the min and max values (done in initialization)

        //scale the array up or down so that the min is zero 
        //not needed bc of the ype of array this program works with
        for (int i = 0; i < n; i++) array.updateSingle(i, array.getValue(i)-min, getDelay(), true);
        max -= min;

        //calculate the stair sizes
        for (int i = 1; i <= stair-1; i++) {
            stairSizes[i] = ((i)*max) / stair;
        }

        //create a new sorted array
        int[] sorted = new int[n];
        int currSortIndex = 0;
        ArrayList<Integer>[] stairBins = new ArrayList[stair]; //array list array to store the stair
        //initialize the bins
        for (int i = 0; i < stair; i++) stairBins[i] = new ArrayList<Integer>();

        
        for (int i = 0; i < n; i++) //parse array, putting things into the bins as needed
        {
            //for loop, checks which bin the item at the current index in arr should go into
            int bestBin = 0;
            for (int x = 0; x < stair; x++)
            {
                if (array.getValue(i) > stairSizes[x]) {
                    bestBin = x;
                }
            }
            stairBins[bestBin].add(array.getValue(i));
        }
        
        //change the array to show the bins
        for(int x=0; x < stairBins.length; x++) {
            //convert arraylist to array, count sort that
            int[] binArr = stairBins[x].stream().mapToInt(i -> i).toArray();
            //apply sorted array
            for (int e : binArr) {
                array.updateSingle(currSortIndex++, e, getDelay(), true);
            }
        }
        currSortIndex=0;
        //for (int i = 0; i < n; i++) array.updateSingle(i, arr[i], getDelay(), false);
        
        
        //  sort each bin 
        //count sort of the bins
        for(int x=0; x < stairBins.length; x++) {
            //convert arraylist to array, count sort that
            int[] binArr = stairBins[x].stream().mapToInt(i -> i).toArray();

            //sort the bin, you can use any algorithm for this
            countSort(binArr);
            
            //apply sorted array
            for (int e : binArr) {
                arr[currSortIndex] = e;
                array.updateSingle(currSortIndex++, e, getDelay(), true);
            }
            stairBins[x].clear();
        }

        //scale the array back to how it was (this should be the last thing done)
        for (int i = 0; i < n; i++) array.updateSingle(i, arr[i]+min, getDelay(), true);      
    }

    /**
     * count sort sub routine
     */
    private static void countSort(int[] arr) {
        //bin start and end values
        int binStartVal = Arrays.stream(arr).min().getAsInt();
        int binEndVal = Arrays.stream(arr).max().getAsInt();
        // range of values
        int range = binEndVal - binStartVal + 1;

        //count and temp arrays
        int[] countArr = new int[range];
        int[] tmpArr = new int[arr.length];

        //count occurances of each number in the arr
        for (int f = 0; f < arr.length; f++) { //step through the bin array
            countArr[arr[f] - binStartVal]++;
        }

        for (int f = 1; f < countArr.length; f ++) {
            countArr[f] += countArr[f-1];
        }

        //build tmpArr with countArr
        for (int f = arr.length - 1; f >= 0; f--) { // step through bin array
            //fancy math stuff to avoid uneeded for loops
            tmpArr[countArr[arr[f] - binStartVal] - 1] = arr[f];
            countArr[arr[f] - binStartVal]--;
        }

        for (int f=0;f<arr.length;f++) {
            arr[f] = tmpArr[f];
        }
    }
}
