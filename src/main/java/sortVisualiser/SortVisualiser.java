package sortVisualiser;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * The main class for the sort visualiser GUI
 * @author Matt Hopson
 */
public class SortVisualiser {
    private JFrame window;
    private SortArray sortArray;
    
    public SortVisualiser() {
        window = new JFrame("Sort Visualiser");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        sortArray = new SortArray();
        window.add(sortArray);
        sortArray.repaint();
        
        window.pack();
        window.setVisible(true);
    }
    
    public static void main(String... args) {
        SortVisualiser sortVisualiser = new SortVisualiser();
    }
}
