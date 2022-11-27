namespace lab
{
    public class FloatBlockArrays
    {
        public static float[,] MultByBlockMethod(float[,] a, float[,] b, int blockSize, int currBlockSize, int startRowA, int startColA, int startRowB, int startColB)
        {
            if (blockSize == currBlockSize)
                return MultByClassicMethod(a, b, currBlockSize, startRowA, startColA, startRowB, startColB);

            int newBlockSize = currBlockSize / 2;
            float[,] c11 = SumBlockSizeSquareArrs(
                MultByBlockMethod(a, b, blockSize, newBlockSize, startRowA, startColA, startRowB, startColB),
                MultByBlockMethod(a, b, blockSize, newBlockSize, startRowA, startColA + newBlockSize, startRowB + newBlockSize, startColB));

            float[,] c12 = SumBlockSizeSquareArrs(
                MultByBlockMethod(a, b, blockSize, newBlockSize, startRowA, startColA, startRowB, startColB + newBlockSize),
                MultByBlockMethod(a, b, blockSize, newBlockSize, startRowA, startColA + newBlockSize, startRowB + newBlockSize, startColB + newBlockSize));

            float[,] c21 = SumBlockSizeSquareArrs(
                MultByBlockMethod(a, b, blockSize, newBlockSize, startRowA + newBlockSize, startColA, startRowB, startColB),
                MultByBlockMethod(a, b, blockSize, newBlockSize, startRowA + newBlockSize, startColA + newBlockSize, startRowB + newBlockSize, startColB));

            float[,] c22 = SumBlockSizeSquareArrs(
                MultByBlockMethod(a, b, blockSize, newBlockSize, startRowA + newBlockSize, startColA, startRowB, startColB + newBlockSize),
                MultByBlockMethod(a, b, blockSize, newBlockSize, startRowA + newBlockSize, startColA + newBlockSize, startRowB + newBlockSize, startColB + newBlockSize));

            return JoinBlockSizeSquareArr(c11, c12, c21, c22);
        }

        private static float[,] MultByClassicMethod(float[,] a, float[,] b, int blockSize, int startRowA, int startColA, int startRowB, int startColB)
        {
            float[,] c = new float[blockSize, blockSize];

            for (int y = 0; y < blockSize; y++)
                for (int x = 0; x < blockSize; x++)
                {
                    c[y, x] = 0;

                    for (int k = 0; k < blockSize; k++)
                        c[y, x] += a[y + startRowA, k + startColA] * b[k + startRowB, x + startColB];
                }

            return c;
        }

        private static float[,] SumBlockSizeSquareArrs(float[,] a, float[,] b)
        {
            int blockSize = a.GetLength(0);
            float[,] c = new float[blockSize, blockSize];

            for (int y = 0; y < blockSize; y++)
                for (int x = 0; x < blockSize; x++)
                    c[y, x] = a[y, x] + b[y, x];

            return c;
        }

        private static float[,] JoinBlockSizeSquareArr(float[,] c11, float[,] c12, float[,] c21, float[,] c22)
        {
            int blockSize = c11.GetLength(0) * 2;
            float[,] c = new float[blockSize, blockSize];

            for (int y = 0; y < blockSize; y++)
                for (int x = 0; x < blockSize; x++)
                    if (y < blockSize / 2)
                    {
                        if (x < blockSize / 2)
                            c[y, x] = c11[y, x];
                        else
                            c[y, x] = c12[y, x - blockSize / 2];
                    }
                    else
                    {
                        if (x < blockSize / 2)
                            c[y, x] = c21[y - blockSize / 2, x];

                        else
                            c[y, x] = c22[y - blockSize / 2, x - blockSize / 2];
                    }

            return c;
        }
    }
}
