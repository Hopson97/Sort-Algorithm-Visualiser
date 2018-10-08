package me.hopson.sortvisualiser;

import javax.sound.midi.*;
import java.util.ArrayList;

/**
 * @author Matthew Hopson
 */
public class MidiSoundPlayer {
    private static int cachedIndex = -1;
    private final ArrayList<Integer> keys;
    private final MidiChannel channel;

    private final int inputValueMaximum;
    private Synthesizer synthesizer;

    MidiSoundPlayer(int maxValue) {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
        inputValueMaximum = maxValue;


        //Set up midi channel
        channel = synthesizer.getChannels()[0];

        //Electric grand piano sounds the best, so it tries to find that.
        //Sometimes it is not supported, so it defaults to 143
        Instrument[] instruments = synthesizer.getDefaultSoundbank().getInstruments();
        if (cachedIndex == -1) {
            boolean found = false;
            int index;
            for (index = 0; index < instruments.length; index++) {
                Instrument i = instruments[index];
                if (i.getName().equals("Electric Grand Piano")) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                index = 2;
            }
            cachedIndex = index;
        }

        channel.programChange(instruments[cachedIndex].getPatch().getProgram());

        //Set up keys
        keys = new ArrayList<>();
        //24 is the start of the somewhat audible major keys
        //108 is the last decent
        for (int i = 24; i < 108; i += 12) {
            keys.add(i);
            keys.add(i + 2);
            keys.add(i + 4);
            keys.add(i + 5);
            keys.add(i + 7);
            keys.add(i + 9);
            keys.add(i + 11);
        }
    }

    private int convertToMajor(int v) {
        float n = ((float) v / (float) inputValueMaximum);
        int index = (int) (n * (float) keys.size());
        index = Math.max(1, Math.min(107, index));
        return keys.get(index);
    }

    public void makeSound(int value) {
        int note = convertToMajor(value);
        channel.noteOn(note, 25);
    }
}
