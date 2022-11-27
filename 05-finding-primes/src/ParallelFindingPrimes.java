import java.util.*;

public class ParallelFindingPrimes {

    private static final int MIN_LAST_NUMBER = 2;
    private static final int MIN_THREADS_NUM = 1;
    private static final int MAX_THREADS_NUM = 64;

    public static SortedSet<Integer> primes(final int lastNumber, final int threadsNum) {

        if (lastNumber < MIN_LAST_NUMBER) {
            throw new IndexOutOfBoundsException();
        }
        if (threadsNum < MIN_THREADS_NUM || threadsNum > MAX_THREADS_NUM) {
            throw new IllegalArgumentException();
        }

        List<Thread> threads = new ArrayList<>(threadsNum);

        SortedSet<Integer> primes = new TreeSet<Integer>();
        primes.add(2);

        int[][] ranges = ranges(lastNumber, threadsNum);

        long startMillis = System.currentTimeMillis();
        Thread currThread;
        for (int[] range : ranges) {
            currThread = new PrimesFinderThread(primes, range[0], range[1]);
            currThread.start();
            threads.add(currThread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long finishMillis = System.currentTimeMillis();
        long consumedMillis = finishMillis - startMillis;
        System.out.println("Consumed millis: " + consumedMillis);

        return primes;
    }

    private static int[][] ranges(int lastNumber, int threadsNum) {

        float lowLimit = 2.0f;
        float partition = (lastNumber - lowLimit) / threadsNum;   // n is the number partitions of the range [a,b]
        if (partition < 1.0) {
            throw new IllegalArgumentException();
        }

        int[][] ranges = new int[threadsNum][2];

        ranges[0][0] = Math.round(lowLimit);
        for (int rowIndex = 0; rowIndex < threadsNum - 1; rowIndex++) {
            lowLimit += partition;
            ranges[rowIndex][1] = Math.round(lowLimit);
            ranges[rowIndex + 1][0] = ranges[rowIndex][1] + 1;
        }
        ranges[threadsNum - 1][1] = lastNumber;

        return ranges;
    }
}
