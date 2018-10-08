package me.hopson.sortvisualiser;

import me.hopson.sortvisualiser.screens.MainMenuScreen;
import me.hopson.sortvisualiser.screens.Screen;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The main application point for controlling the program
 *
 * @author Matthew Hopson
 */
public class MainApp {
    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 720;
    private final JFrame window;
    private final ArrayList<Screen> screens;

    private MainApp() {
        screens = new ArrayList<>();
        window = new JFrame("Sort visualiser");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void main(String... args) {
        System.setProperty("sun.java2d.opengl", "true");
        SwingUtilities.invokeLater(() -> {
            new MainApp().start();
        });
    }

    private Screen getCurrentScreen() {
        return screens.get(screens.size() - 1);
    }

    public void pushScreen(Screen screen) {
        if (!screens.isEmpty()) {
            window.remove(getCurrentScreen());
        }
        screens.add(screen);
        window.setContentPane(screen);
        window.validate();
        screen.onOpen();
    }

    public void popScreen() {
        if (!screens.isEmpty()) {
            Screen prev = getCurrentScreen();
            screens.remove(prev);
            window.remove(prev);
            if (!screens.isEmpty()) {
                Screen current = getCurrentScreen();
                window.setContentPane(current);
                window.validate();
                current.onOpen();
            } else {
                window.dispose();
            }
        }
    }

    private void start() {
        pushScreen(new MainMenuScreen(this));
        window.pack();
    }
}
