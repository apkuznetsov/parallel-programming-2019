public class SequentialMain {

    public static void main(String[] args) {

        final int[] arrayA = new int[Main.ARR_SIZE];
        final int[] arrayB = new int[arrayA.length];
        int[] buffer = new int[Main.BUF_SIZE];

        for (int i = 0; i < arrayA.length; i++) {
            arrayA[i] = i;
        }

        int leftIndex = 0;
        int elementsNumToWrite = buffer.length;

        final long start = System.currentTimeMillis();

        while (leftIndex + elementsNumToWrite <= arrayA.length) {

            // запись в буфер:
            if (elementsNumToWrite >= 0) {
                System.arraycopy(arrayA, leftIndex, buffer, 0, elementsNumToWrite);
            }

            // перезапись из буфера в массив B:
            if (elementsNumToWrite >= 0) {
                System.arraycopy(buffer, 0, arrayB, leftIndex, elementsNumToWrite);
            }

            leftIndex += buffer.length;
            if (leftIndex + buffer.length > arrayA.length) {
                elementsNumToWrite = arrayA.length - leftIndex;
            }
        }

        final long finish = System.currentTimeMillis();

        final long timeConsumedMillis = finish - start;
        System.out.println("Time: " + timeConsumedMillis);
    }
}