public class Main {

    public static final int ARR_SIZE = 500_000_000;
    public static final int BUF_SIZE = 250_000_000;

    public static void main(String[] args) throws InterruptedException {

        final int[] arrayA = new int[ARR_SIZE];
        final int[] arrayB = new int[arrayA.length];
        final Buffer buffer = new Buffer(BUF_SIZE);

        for (int i = 0; i < arrayA.length; i++) {
            arrayA[i] = i;
        }

        final Producer producer = new Producer(arrayA, buffer);
        final Consumer consumer = new Consumer(arrayB, buffer);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
