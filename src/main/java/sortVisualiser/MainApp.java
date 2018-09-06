package sortVisualiser;

import java.util.ArrayList;
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
    
    private final ArrayList<Screen> screens;
    
    public MainApp() {
        screens = new ArrayList<>();
        window = new JFrame ("Sort visualiser");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
    public void pushScreen(Screen screen) {
        if (!screens.isEmpty()) {
            screens.get(screens.size() - 1).setVisible(false); 
        }
        screens.add(screen);
        window.add(screen);
        window.pack();
        System.out.println("edt: " + SwingUtilities.isEventDispatchThread());
        screen.onOpen();
    }
    
    public void popScreen() {
        if (!screens.isEmpty()) {
            Screen prev = screens.get(screens.size() - 1);
            prev.setVisible(false);
            screens.remove(prev);
            window.remove(prev);
            if (!screens.isEmpty()) {
                screens.get(screens.size() - 1).onOpen();
                screens.get(screens.size() - 1).setVisible(true);
            }
            else
                window.dispose();
        }
    }
    
    public void start() {
        pushScreen(new MainMenu(this));
    }
    
    public static void main(String... args) {
        System.setProperty("sun.java2d.opengl", "true");
        SwingUtilities.invokeLater(() -> {
            new MainApp().start();
        });
    }
}
