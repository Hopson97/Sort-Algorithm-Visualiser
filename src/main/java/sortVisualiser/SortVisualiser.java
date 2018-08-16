package sortVisualiser;

import javax.swing.JFrame;

/**
 * The main class for the sort visualiser GUI
 * @author mhops
 */
public class SortVisualiser {
    
    private JFrame window;
    
    public SortVisualiser() {
        window = new JFrame("Sort Visualiser");
    }
    
    public static void main(String... args) {
        SortVisualiser sortVisualiser = new SortVisualiser();
    }
}
