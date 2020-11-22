package nbody;

public class Helpers {

    public static int[][] ranges(int lastNumber, int threadsNum) {

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
