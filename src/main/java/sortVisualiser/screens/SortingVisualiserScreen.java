package sortVisualiser.screens;

import java.util.ArrayList;
import javax.swing.SwingWorker;
import sortVisualiser.MainApp;
import sortVisualiser.SortArray;

import sortVisualiser.algorithms.ISortAlgorithm;

/**
 * The main class for the sort visualiser GUI
 *
 * @author Matt Hopson
 */
public final class SortingVisualiserScreen extends Screen {
    private final SortArray sortArray;
    private final ArrayList<ISortAlgorithm> sortQueue;

    /**
     * Creates the GUI
     * @param algorithms List of algorithms to run for visualisation
     * @param playSounds Whether or not you want the algorithm to play sounds
     * @param app The main application
     */
    public SortingVisualiserScreen(ArrayList<ISortAlgorithm> algorithms, boolean playSounds, MainApp app) {
        super(app);
        sortArray = new SortArray(playSounds);
        add(sortArray);

        sortQueue = algorithms;
    }
    
    private void longSleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } 
    }

    private void shuffleAndWait() {
        sortArray.shuffle();
        sortArray.resetColours();
        longSleep();
    }
    
    public void onOpen() {
        //This would block the EventDispatchThread, and so
        //it must run on a worker thread
        SwingWorker swingWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                System.out.println("RUNNING");
                for (ISortAlgorithm algorithm : sortQueue) {
                    shuffleAndWait();

                    sortArray.setName(algorithm.getName());
                    algorithm.runSort(sortArray);

                    sortArray.resetColours();
                    sortArray.highlightArray();
                    sortArray.resetColours();
                    longSleep();
                }
                return null;
            }
            
            @Override
            public void done() {
                app.popScreen(); 
            }
        };
        
        swingWorker.execute();
    }
}
