package sortVisualiser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static sortVisualiser.Main.WIN_HEIGHT;
import static sortVisualiser.Main.WIN_WIDTH;
import sortVisualiser.algorithms.ISortAlgorithm;

/**
 *
 * @author Matthew Hopson
 */
public class MainMenu extends JPanel {
    private static final Color BACKGROUND_COLOUR = Color.darkGray;
    public MainMenu() {
        setUpGUI();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIN_WIDTH, WIN_HEIGHT);
    }
    
    private void addCheckBox(String name, JPanel container) {
        JCheckBox box = new JCheckBox(name, true);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.setBackground(BACKGROUND_COLOUR);
        box.setForeground(Color.WHITE);
        container.add(box);
    }
    
    public void setUpGUI() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   
        setBackground(BACKGROUND_COLOUR);
        container.setBackground(BACKGROUND_COLOUR);
        
        try {
            ClassLoader loader = getClass().getClassLoader();
            BufferedImage image = ImageIO.read(new File(loader.getResource("logo.png").getFile()));
            JLabel label = new JLabel(new ImageIcon(image));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(label);
        } catch (IOException e) {
            System.out.println("Unable to load logo");
        }
        
        container.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCheckBox("Selection sort",   container);
        addCheckBox("Quick sort",       container);
        addCheckBox("Merge sort",       container);
        addCheckBox("Insertion sort",   container);
        addCheckBox("Gnome sort",       container);
        addCheckBox("Bubble sort",      container);
        
        JButton startButton = new JButton("Begin Visual Sorter");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(container);
        add(startButton);
    }
    
    private class AlgorithmCheckBox {
        private final ISortAlgorithm algorithm;
        
        public AlgorithmCheckBox(ISortAlgorithm algorithm) {
            this.algorithm = algorithm;
        }
    }
    
}
