package sortVisualiser.algorithms;

/**
 * Bubble sort implementation
 * @author mhops
 */
public class BubbleSort implements ISortAlgorithm
{
    @Override
    public void runSort(int[] data) {
        int len = data.length;
        for(int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }
}