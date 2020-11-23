package nbody;

public class Helpers {

    public static int[][] ranges(int startNum, int endNum, int rangesNum) {

        float lowLimit = startNum;
        float partition = (endNum - lowLimit) / rangesNum;   // n is the number partitions of the range [a,b]
        if (partition < 1.0) {
            throw new IllegalArgumentException();
        }

        int[][] ranges = new int[rangesNum][2];

        ranges[0][0] = Math.round(lowLimit);
        for (int rowIndex = 0; rowIndex < rangesNum - 1; rowIndex++) {
            lowLimit += partition;
            ranges[rowIndex][1] = Math.round(lowLimit);
            ranges[rowIndex + 1][0] = ranges[rowIndex][1] + 1;
        }
        ranges[rangesNum - 1][1] = endNum;

        return ranges;
    }
}
