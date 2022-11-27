using System;

namespace lab
{
    public class Arrays
    {
        public static int[,] CreateRandNumsIntArr(int rowsNum, int colsNum)
        {
            int[,] arr = new int[rowsNum, colsNum];

            Random rand = new Random();

            for (int y = 0; y < rowsNum; y++)
                for (int x = 0; x < colsNum; x++)
                    arr[y, x] = rand.Next();

            return arr;
        }

        public static double[,] CreateRandNumsDoubleArr(int rowsNum, int colsNum)
        {
            double[,] arr = new double[rowsNum, colsNum];

            Random rand = new Random();

            for (int y = 0; y < rowsNum; y++)
                for (int x = 0; x < colsNum; x++)
                    arr[y, x] = rand.Next();

            return arr;
        }

        public static float[,] CreateRandNumsFloatArr(int rowsNum, int colsNum)
        {
            float[,] arr = new float[rowsNum, colsNum];

            Random rand = new Random();

            for (int y = 0; y < rowsNum; y++)
                for (int x = 0; x < colsNum; x++)
                    arr[y, x] = rand.Next();

            return arr;
        }

        public static int[,] MultByClassicMethod(int[,] arr1, int[,] arr2)
        {
            int rowsNumArr1 = arr1.GetLength(0);
            int colsNumArr1 = arr1.GetLength(1);
            int colsNumArr2 = arr2.GetLength(1);

            int[,] resArr = new int[rowsNumArr1, colsNumArr2];

            for (int rows1 = 0; rows1 < rowsNumArr1; rows1++)
                for (int cols2 = 0; cols2 < colsNumArr2; cols2++)
                {
                    resArr[rows1, cols2] = 0;

                    for (int cols1 = 0; cols1 < colsNumArr1; cols1++)
                        resArr[rows1, cols2] += arr1[rows1, cols1] * arr2[cols1, cols2];
                }

            return resArr;
        }

        public static void Transpose(int[,] arr)
        {
            int temp;
            for (int i = 0; i < arr.GetLength(0); i++)
            {
                for (int j = 0; j < i; j++)
                {
                    temp = arr[i, j];
                    arr[i, j] = arr[j, i];
                    arr[j, i] = temp;
                }
            }
        }
    }
}
