package sortVisualiser;

import java.util.ArrayList;
import javax.swing.JFrame;
import sortVisualiser.algorithms.BubbleSort;
import sortVisualiser.algorithms.ISortAlgorithm;
import sortVisualiser.algorithms.Shuffle;
import static util.Sleep.sleepFor;

/**
 * The main class for the sort visualiser GUI
 * @author Matt Hopson
 */
public class SortVisualiser {
    private JFrame window;
    private SortArray sortArray;
    
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
        sortQueue.add(new Shuffle());
        sortQueue.add(new BubbleSort());
    }
    
    public void run() {
        for (ISortAlgorithm algorithm : sortQueue) {
            algorithm.runSort(sortArray);
            sortArray.resetColours();
            sleepFor(2000000);
        }
    }
    
    public static void main(String... args) {
        SortVisualiser sortVisualiser = new SortVisualiser();
        sortVisualiser.run();
    }
}
