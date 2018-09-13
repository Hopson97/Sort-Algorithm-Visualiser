package sortvisualiser;

import sortvisualiser.screens.MainMenuScreen;
import sortvisualiser.screens.Screen;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The main application point for controlling the program
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
    
    public Screen getCurrentScreen() {
        return screens.get(screens.size() - 1);
    }
    
    public void pushScreen(Screen screen) {
        if (!screens.isEmpty()) {
            getCurrentScreen().setVisible(false); 
        }
        screens.add(screen);
        window.add(screen);
        screen.onOpen();
    }
    
    public void popScreen() {
        if (!screens.isEmpty()) {
            Screen prev = getCurrentScreen();
            prev.setVisible(false);
            screens.remove(prev);
            window.remove(prev);
            if (!screens.isEmpty()) {
                getCurrentScreen().onOpen();
                getCurrentScreen().setVisible(true);
            }
            else {
                window.dispose();
            }
        }
    }
    
    public void start() {
        pushScreen(new MainMenuScreen(this));
        window.pack();
    }
    
    public static void main(String... args) {
        System.setProperty("sun.java2d.opengl", "true");
        SwingUtilities.invokeLater(() -> {
            new MainApp().start();
        });
    }
}
