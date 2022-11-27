public class Consumer extends Thread {

    private final int[] arrayB;
    private final Buffer buffer;

    public Consumer(int[] array, Buffer buffer) {
        this.arrayB = array;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < arrayB.length; i++) {
                arrayB[i] = buffer.read();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}