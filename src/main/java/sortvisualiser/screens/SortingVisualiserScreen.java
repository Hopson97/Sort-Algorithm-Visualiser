package sortvisualiser.screens;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;

import sortvisualiser.MainApp;
import sortvisualiser.SortArray;
import sortvisualiser.algorithms.ISortAlgorithm;

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
        setLayout(new BorderLayout());
        sortArray = new SortArray(playSounds);
        add(sortArray, BorderLayout.CENTER);
        
        sortQueue = algorithms;
    }
    
    private void longSleep() {
        try {
            Thread.sleep(1000);
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
        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } 
                for (ISortAlgorithm algorithm : sortQueue) {
                    shuffleAndWait();

                    sortArray.setName(algorithm.getName());
                    sortArray.setAlgorithmDelay(algorithm.getDelay());
                    
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
