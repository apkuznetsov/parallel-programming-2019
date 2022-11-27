import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Main {

    private static final int LAST_NUMBER = 200_000_000;
    private static final int THREADS_NUM = 56;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        //writer.print(SequentialFindingPrimes.primes(LAST_NUMBER));
        SequentialFindingPrimes.primes(LAST_NUMBER);

        //PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        //writer.print(ParallelFindingPrimes.primes(LAST_NUMBER, THREADS_NUM));
    }
}
