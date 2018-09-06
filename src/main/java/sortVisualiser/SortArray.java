package sortVisualiser;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
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
    
    private ArrayList<Integer> majorKeys;
    
    Synthesizer synth;

    public SortArray() {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
        setBackground(Color.darkGray);
        array = new int[NUM_BARS];
        barColours = new int[NUM_BARS];
        for (int i = 0; i < NUM_BARS; i++) {
            array[i] = i;
            barColours[i] = 0;
        }
        
        majorKeys = new ArrayList<>();
        for (int i = 0; i < 108; i += 12) {
            majorKeys.add(i);
            majorKeys.add(i + 2);
            majorKeys.add(i + 4);
            majorKeys.add(i + 5);
            majorKeys.add(i + 7);
            majorKeys.add(i + 9);
            majorKeys.add(i + 11);
        }
    }

    public int arraySize() {
        return array.length;
    }

    public int getValue(int index) {
        return array[index];
    }

    public void swap(int firstIndex, int secondIndex, long millisecondDelay) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;

        barColours[firstIndex] = 100;
        barColours[secondIndex] = 100;

        repaint();
        try {
            Thread.sleep(millisecondDelay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        makeSound((array[firstIndex] + array[secondIndex]) / 2);
    }
    
    
    private int convertToMajor(int v) {
        float n = ((float)v / (float)NUM_BARS);
        int index = (int)(n * (float)majorKeys.size());
        index = Math.max(1, Math.min(107, index));
        return majorKeys.get(index);
    }
    
    
    private void makeSound(int value) {
        MidiChannel[] channel = synth.getChannels();
        Instrument[] instruments = synth.getDefaultSoundbank().getInstruments();

        int note = convertToMajor(value);
        channel[0].programChange(instruments[143].getPatch().getProgram());
        channel[0].noteOn(note, 50);
    }

    public void updateSingle(int index, int value, long millisecondDelay) {
        array[index] = value;
        barColours[index] = 100;
        repaint();
        try {
            Thread.sleep(millisecondDelay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        makeSound(value);
    }

    public void shuffle() {
        Random rng = new Random();
        for (int i = 0; i < arraySize(); i++) {
            int swapWithIndex = rng.nextInt(arraySize() - 1);
            swap(i, swapWithIndex, 5);
        }
    }

    public void highlightArray() {
        for (int i = 0; i < arraySize(); i++) {
            updateSingle(i, getValue(i), 5);
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
        Graphics2D graphics = (Graphics2D) g;
        super.paintComponent(graphics);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Verdana", Font.BOLD, 27));
        graphics.drawString(algorithmName, 100, 100);

        for (int x = 0; x < NUM_BARS; x++) {
            int height = getValue(x) * 2;
            int xBegin = x + (BAR_WIDTH - 1) * x;
            int yBegin = WIN_HEIGHT - height;

            int val = barColours[x] * 2;
            graphics.setColor(new Color(255, 255 - val, 255 - val));
            graphics.fillRect(xBegin, yBegin, BAR_WIDTH, height);
            if (barColours[x] > 0) {
                barColours[x] -= 10;
            }
        }
    }

    public void setName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
