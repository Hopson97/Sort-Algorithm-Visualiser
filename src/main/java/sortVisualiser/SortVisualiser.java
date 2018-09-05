package sortVisualiser;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import sortVisualiser.algorithms.ISortAlgorithm;

/**
 * The main class for the sort visualiser GUI
 *
 * @author Matt Hopson
 */
public final class SortVisualiser extends Screen {
    private final SortArray sortArray;
    private final ArrayList<ISortAlgorithm> sortQueue;

    /**
     * Creates the GUI
     * @param algorithms List of algorithms to run for visualisation
     * @param app The main application
     */
    public SortVisualiser(ArrayList<ISortAlgorithm> algorithms, MainApp app) {
        super(app);
        sortArray = new SortArray();
        add(sortArray);

        sortQueue = algorithms;
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
    
    public void onOpen() {
        SwingWorker swingWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                for (ISortAlgorithm algorithm : sortQueue) {
                    shuffleAndWait();

                    sortArray.setName(algorithm.getName());
                    algorithm.runSort(sortArray);

                    sortArray.resetColours();
                    sortArray.highlightArray();
                    sortArray.resetColours();
                }
                return null;
            }
            
            @Override
            public void done() {
                app.popScreen(); 
            }
        };
    }
}
