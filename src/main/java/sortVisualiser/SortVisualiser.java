package sortVisualiser;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import sortVisualiser.algorithms.BubbleSort;
import sortVisualiser.algorithms.GnomeSort;
import sortVisualiser.algorithms.ISortAlgorithm;
import sortVisualiser.algorithms.InsertionSort;
import sortVisualiser.algorithms.MergeSort;
import sortVisualiser.algorithms.QuickSort;
import sortVisualiser.algorithms.SelectionSort;

/**
 * The main class for the sort visualiser GUI
 * @author Matt Hopson
 */
public class SortVisualiser {
    private final JFrame window;
    private final SortArray sortArray;  
    private final ArrayList<ISortAlgorithm> sortQueue;
    
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
        sortQueue.add(new GnomeSort());
        sortQueue.add(new MergeSort());
        sortQueue.add(new QuickSort());
        sortQueue.add(new SelectionSort());
        sortQueue.add(new InsertionSort());
        sortQueue.add(new BubbleSort());
    }
   
    private void shuffleAndWait() {
        sortArray.shuffle();
        sortArray.resetColours();
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void run() {
        for (ISortAlgorithm algorithm : sortQueue) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            shuffleAndWait();

            sortArray.setName(algorithm.getName());
            algorithm.runSort(sortArray);
            
            sortArray.resetColours();
            sortArray.highlightArray();
            sortArray.resetColours();
        }
    }
    
    public static void main(String... args) {
        SortVisualiser sortVisualiser = new SortVisualiser();
        sortVisualiser.run();
    }
}
