package sortvisualiser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;

/**
 * The array that can be sorted
 *
 * @author mhops
 */
public class SortArray extends JPanel {
    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 720;
    private static final int BAR_WIDTH = 5;
    private static final int NUM_BARS = WIN_WIDTH / BAR_WIDTH;

    private final int[] array;
    private final int[] barColours;
    private String algorithmName = "";
    private long algorithmDelay = 0;
    
    private MidiSoundPlayer player;
    private boolean playSounds;

    private int arrayChanges = 0; // Number of changes to the array the current algorithm has taken so far

    public SortArray(boolean playSounds) {
        setBackground(Color.DARK_GRAY);
        array = new int[NUM_BARS];
        barColours = new int[NUM_BARS];
        for (int i = 0; i < NUM_BARS; i++) {
            array[i] = i;
            barColours[i] = 0;
        }
        player = new MidiSoundPlayer(NUM_BARS);
        this.playSounds = playSounds;
    }

    public int arraySize() {
        return array.length;
    }

    public int getValue(int index) {
        return array[index];
    }

    private void finaliseUpdate(int value,  long millisecondDelay, boolean isStep) {
        repaint();
        try {
            Thread.sleep(millisecondDelay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        if (playSounds) {
            player.makeSound(value);
        }
        if (isStep) 
            arrayChanges++;
    }

    public void swap(int firstIndex, int secondIndex, long millisecondDelay, boolean isStep) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;

        barColours[firstIndex] = 100;
        barColours[secondIndex] = 100;

        finaliseUpdate((array[firstIndex] + array[secondIndex]) / 2, millisecondDelay, isStep);
    }

    public void updateSingle(int index, int value, long millisecondDelay, boolean isStep) {
        array[index] = value;
        barColours[index] = 100;
        finaliseUpdate(value, millisecondDelay, isStep);
        repaint();
    }

    public void shuffle() {
        arrayChanges = 0;
        Random rng = new Random();
        for (int i = 0; i < arraySize(); i++) {
            int swapWithIndex = rng.nextInt(arraySize() - 1);
            swap(i, swapWithIndex, 5, false);
        }
        arrayChanges = 0;
    }

    public void highlightArray() {
        for (int i = 0; i < arraySize(); i++) {
            updateSingle(i, getValue(i), 5, false);
        }
    }

    /**
     * Gets the canvas size
     *
     * @return size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIN_WIDTH, WIN_HEIGHT);
    }

    public void resetColours() {
        for (int i = 0; i < NUM_BARS; i++) {
            barColours[i] = 0;
        }
        repaint();
    }

    /**
     * Draws the array
     *
     * @param g The graphics device for drawing
     */
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D panelGraphics = (Graphics2D) g.create();

		try
		{
			Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
			renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			panelGraphics.addRenderingHints(renderingHints);

			panelGraphics.setColor(Color.WHITE);
			panelGraphics.setFont(new Font("Monospaced", Font.BOLD, 20));
			panelGraphics.drawString(" Current algorithm: " + algorithmName, 10, 30);
			panelGraphics.drawString("Current step delay: " + algorithmDelay + "ms", 10, 55);
			panelGraphics.drawString("     Array Changes: " + arrayChanges, 10, 80);

			for (int x = 0; x < NUM_BARS; x++) {
				int height = getValue(x) * 2;
				double heightPercent = (double) height / (double) WIN_HEIGHT;
				height = (int) (heightPercent * (double) getHeight());
				int xBegin = x + (BAR_WIDTH - 1) * x;
				int yBegin = getHeight() - height;

				int val = barColours[x] * 2;
				if (val > 190) {
					panelGraphics.setColor(new Color(255 - val, 255, 255 - val));
				} else {
					panelGraphics.setColor(new Color(255, 255 - val, 255 - val));
				}
				panelGraphics.fillRect(xBegin, yBegin, BAR_WIDTH, height);
				if (barColours[x] > 0) {
					barColours[x] -= 5;
				}
			}
		} finally {
        	panelGraphics.dispose();
        }
    }

    @Override
    public void setName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
    
    public void setAlgorithmDelay(long delay) {
        algorithmDelay = delay;
    }
}
