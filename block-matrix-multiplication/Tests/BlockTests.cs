using System.Diagnostics;

namespace lab.Tests
{
    public class BlockTests
    {
        public static readonly int[] TestArrSizes = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 };

        public static double[] TestIntBlockMethod(int testArrSize, int[] testBlockSizes)
        {
            double[] resultsInSecs = new double[testBlockSizes.Length];

            for (int index = 0; index < testBlockSizes.Length; index++)
            {
                int[,] arr1 = Arrays.CreateRandNumsIntArr(testArrSize, testArrSize);
                int[,] arr2 = Arrays.CreateRandNumsIntArr(testArrSize, testArrSize);

                Stopwatch stopwatch = new Stopwatch();
                stopwatch.Start();

                IntBlockArrays.MultByBlockMethod(arr1, arr2, testBlockSizes[index], arr1.GetLength(0), 0, 0, 0, 0);

                stopwatch.Stop();

                resultsInSecs[index] = stopwatch.Elapsed.TotalSeconds;
            }

            return resultsInSecs;
        }

        public static double[] TestDoubleBlockMethod(int testArrSize, int[] testBlockSizes)
        {
            double[] resultsInSecs = new double[testBlockSizes.Length];

            for (int index = 0; index < testBlockSizes.Length; index++)
            {
                double[,] arr1 = Arrays.CreateRandNumsDoubleArr(testArrSize, testArrSize);
                double[,] arr2 = Arrays.CreateRandNumsDoubleArr(testArrSize, testArrSize);

                Stopwatch stopwatch = new Stopwatch();
                stopwatch.Start();

                DoubleBlockArrays.MultByBlockMethod(arr1, arr2, testBlockSizes[index], arr1.GetLength(0), 0, 0, 0, 0);

                stopwatch.Stop();

                resultsInSecs[index] = stopwatch.Elapsed.TotalSeconds;
            }

            return resultsInSecs;
        }

        public static double[] TestFloatBlockMethod(int testArrSize, int[] testBlockSizes)
        {
            double[] resultsInSecs = new double[testBlockSizes.Length];

            for (int index = 0; index < testBlockSizes.Length; index++)
            {
                float[,] arr1 = Arrays.CreateRandNumsFloatArr(testArrSize, testArrSize);
                float[,] arr2 = Arrays.CreateRandNumsFloatArr(testArrSize, testArrSize);

                Stopwatch stopwatch = new Stopwatch();
                stopwatch.Start();

                FloatBlockArrays.MultByBlockMethod(arr1, arr2, testBlockSizes[index], arr1.GetLength(0), 0, 0, 0, 0);

                stopwatch.Stop();

                resultsInSecs[index] = stopwatch.Elapsed.TotalSeconds;
            }

            return resultsInSecs;
        }
    }
}
