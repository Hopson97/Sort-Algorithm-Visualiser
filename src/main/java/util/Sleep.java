package util;

/**
 * Methods to sleep the thread
 * @author mhops
 */
public class Sleep {
    public static void sleepFor(int microseconds) {
        long nanoseconds = microseconds * 1000;
        long timeElapsed;
        final long startTime = System.nanoTime();
        do {
            timeElapsed = System.nanoTime() - startTime;
        } while(timeElapsed < nanoseconds);
    }
}
