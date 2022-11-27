import java.util.Iterator;
import java.util.SortedSet;

public class PrimesFinderThread extends Thread {

    private final SortedSet<Integer> primes;
    private final int leftBound;
    private final int rightBound;

    public PrimesFinderThread(SortedSet<Integer> primes, int leftBound, int rightBound) {

        if (leftBound > rightBound) {
            throw new IllegalArgumentException();
        }

        this.primes = primes;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    @Override
    public void run() {

        int startNumber = (leftBound % 2 == 0) ? leftBound + 1 : leftBound;

        for (int i = startNumber; i <= rightBound; i += 2) {
            synchronized (primes) {
                if (isPrime(i)) {
                    primes.add(i);
                    primes.notifyAll();
                }
            }
        }

        System.out.println(Thread.currentThread().getName() + " finished " + "[" + leftBound + "; " + rightBound + "]");
    }

    private boolean isPrime(int number) {

        /* если проверяемое число состоит хотя бы из двух множителей,
         * то ни одно из них не может превышать двоичный корень: */
        double sqrtedNumber = Math.sqrt(number);

        synchronized (primes) {
            while (primes.last() < (int) sqrtedNumber) {
                try {
                    primes.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Iterator<Integer> itor = primes.iterator();
        int currNum;
        while (itor.hasNext()) {
            currNum = itor.next();
            if (currNum > sqrtedNumber) {
                return true;
            }

            if (number % currNum == 0) {
                return false;
            }
        }

        return true;
    }
}
