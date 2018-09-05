package sortVisualiser;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import static sortVisualiser.Main.WIN_HEIGHT;
import static sortVisualiser.Main.WIN_WIDTH;

/**
 *
 * @author Matthew Hopson
 */
public class MainMenu extends JPanel {
    public MainMenu() {
        setUpGUI();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, WIN_HEIGHT);
    }
    
    void addCheckBox(String name) {
        JCheckBox box = new JCheckBox(name);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        add(box);
    }
    
    public void setUpGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        addCheckBox("Selection sort");
        addCheckBox("Quick sort");
        addCheckBox("Merge sort");
        addCheckBox("Insertion sort");
        addCheckBox("Gnome sort");
        addCheckBox("Bubble sort");
        
        JButton startButton = new JButton("Begin Visual Sorter");
        setAlignmentX(Component.CENTER_ALIGNMENT);
        add(startButton);

    }
}
