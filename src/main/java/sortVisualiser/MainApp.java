package sortVisualiser;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Matthew Hopson
 */
public class MainApp {
    private final JFrame window;
    
    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 720;
    
    public MainApp() {
        window = new JFrame ("Sort visualiser");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void start() {
        window.add(new MainMenu());
        window.pack();
        window.setVisible(true);
    }
    
    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            new MainApp().start();
        });
    }
}
