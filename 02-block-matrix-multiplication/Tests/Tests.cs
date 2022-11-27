using System.Diagnostics;

namespace lab.Tests
{
    public class Tests
    {
        public static readonly int[] TestArrSizes = { 32, 64, 128, 256, 512, 1024, 2048};

        public static double[] TestClassicMethod(int[] testArrSizes)
        {
            double[] resultsInSecs = new double[testArrSizes.Length];

            for (int index = 0; index < testArrSizes.Length; index++)
            {
                int[,] arr1 = Arrays.CreateRandNumsIntArr(testArrSizes[index], testArrSizes[index]);
                int[,] arr2 = Arrays.CreateRandNumsIntArr(testArrSizes[index], testArrSizes[index]);

                Stopwatch stopwatchForClassic = new Stopwatch();
                stopwatchForClassic.Start();
                Arrays.MultByClassicMethod(arr1, arr2);
                stopwatchForClassic.Stop();

                resultsInSecs[index] = stopwatchForClassic.Elapsed.TotalSeconds;
            }

            return resultsInSecs;
        }

        public static double[] TestTransposeMethod(int[] testArrSizes)
        {
            double[] resultsInSecs = new double[testArrSizes.Length];

            for (int index = 0; index < testArrSizes.Length; index++)
            {
                int[,] arr1 = Arrays.CreateRandNumsIntArr(testArrSizes[index], testArrSizes[index]);
                int[,] arr2 = Arrays.CreateRandNumsIntArr(testArrSizes[index], testArrSizes[index]);

                Stopwatch stopwatchForClassic = new Stopwatch();
                stopwatchForClassic.Start();

                Arrays.Transpose(arr2);
                Arrays.MultByClassicMethod(arr1, arr2);

                stopwatchForClassic.Stop();

                resultsInSecs[index] = stopwatchForClassic.Elapsed.TotalSeconds;
            }

            return resultsInSecs;
        }
    }
}
