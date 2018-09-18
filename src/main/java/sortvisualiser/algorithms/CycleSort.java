package sortvisualiser.algorithms;

import sortvisualiser.SortArray;


public class CycleSort implements ISortAlgorithm {

    private long stepDelay = 125;
    private class CycleResult {
        public int position;
        public boolean cont;

        public CycleResult(int retValue, boolean shouldContinue) {
            position = retValue;
            cont = shouldContinue;

        }
    }
    
    private CycleResult doCycle(int begin, int position, int value, SortArray array, boolean canFinishEarly) {
        position = begin;
        for (int i = begin + 1; i < array.arraySize(); i++) {
            if (array.getValue(i) < value) {
                position++;
            }
        }
        if ((position == begin) && canFinishEarly) return new CycleResult(0, true);

        while (value == array.getValue(position)) {
            position++;
        }
        return new CycleResult(position, false);
    }

    @Override
    public void runSort(SortArray array) {
        int n = array.arraySize();
        for (int begin = 0; begin <= n - 2; begin++) {
            int value = array.getValue(begin);
            int position = begin;
            CycleResult result = doCycle(begin, position, value, array, true);
            if (result.cont) continue;
            position = result.position;
            if (position != begin) {
                int t = value;
                value = array.getValue(position);
                array.updateSingle(position, t, getDelay(), true);
            }
            while (position != begin) {
                position = doCycle(begin, position, value, array, false).position;
                if (value != array.getValue(position)) {
                    int t = value;
                    value = array.getValue(position);
                    array.updateSingle(position, t, getDelay(), true);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Cycle sort";
    }

    @Override
    public long getDelay() {
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) {
        this.stepDelay = delay;
    }
}