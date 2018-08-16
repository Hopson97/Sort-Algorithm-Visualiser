package sortVisualiser;

import java.util.ArrayList;
import javax.swing.JFrame;
import sortVisualiser.algorithms.BubbleSort;
import sortVisualiser.algorithms.ISortAlgorithm;
import sortVisualiser.algorithms.InsertionSort;
import sortVisualiser.algorithms.SelectionSort;
import sortVisualiser.algorithms.Shuffle;
import static util.Sleep.secondsToNano;
import static util.Sleep.sleepFor;

/**
 * The main class for the sort visualiser GUI
 * @author Matt Hopson
 */
public class SortVisualiser {
    private JFrame window;
    private SortArray sortArray;
    private Shuffle shuffler = new Shuffle();
    
    private ArrayList<ISortAlgorithm> sortQueue;
    
    /**
     * Creates the GUI
     */
    public SortVisualiser() {
        window = new JFrame("Sort Visualiser");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        sortArray = new SortArray();
        window.add(sortArray);
        window.pack();
        window.setVisible(true);
        
        sortQueue = new ArrayList<>();
        sortQueue.add(new SelectionSort());
        sortQueue.add(new InsertionSort());
        sortQueue.add(new BubbleSort());
    }
    
    private void highlightArray() {
        for (int i = 0; i < sortArray.arraySize(); i++) {
            sortArray.swapUpdate(i, i);
        }
    }
    
    private void shuffleAndWait() {
        shuffler.runSort(sortArray);
        sortArray.resetColours();
        sleepFor(secondsToNano(2));
    }
    
    public void run() {
        for (ISortAlgorithm algorithm : sortQueue) {
            sleepFor(secondsToNano(1));
            shuffleAndWait();
            algorithm.runSort(sortArray);
            sortArray.resetColours();
            highlightArray();
            sortArray.resetColours();
        }
    }
    
    public static void main(String... args) {
        SortVisualiser sortVisualiser = new SortVisualiser();
        sortVisualiser.run();
    }
}
