package me.hopson.sortvisualiser.screens;

import me.hopson.sortvisualiser.MainApp;

import javax.swing.*;
import java.awt.*;

/**
 * @author mhops
 */
public abstract class Screen extends JPanel {
    MainApp app;

    Screen(MainApp app) {
        this.app = app;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MainApp.WIN_WIDTH, MainApp.WIN_HEIGHT);
    }

    public abstract void onOpen();
}
