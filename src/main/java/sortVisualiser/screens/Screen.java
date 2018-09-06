package sortVisualiser.screens;

import java.awt.Dimension;
import javax.swing.JPanel;
import sortVisualiser.MainApp;
import static sortVisualiser.MainApp.WIN_HEIGHT;
import static sortVisualiser.MainApp.WIN_WIDTH;

/**
 * 
 * @author mhops
 */
public abstract class Screen extends JPanel {
    protected MainApp app;
    
    public Screen(MainApp app) {
        this.app = app;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIN_WIDTH, WIN_HEIGHT);
    }
    
    public abstract void onOpen();
}
