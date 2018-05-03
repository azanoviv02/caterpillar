package com.netcracker.caterpillar.spanning;

public class SpanningTreeSolution {

    public static final int MODULO = 1000000007;

    public static void main(String[] args) {

        int[] inputArray = {0, 100, 0};
//        int[] inputArray = {0, 3, 0, 1, 2, 1, 3, 1, 0};
        int spanningTreeAmount = getSpanningTreeAmount(inputArray);
        System.out.println(spanningTreeAmount);
    }

    private static int getSpanningTreeAmount(int[] inputArray) {
        int minusOne = 1;
        int current = twoInPower(inputArray[0]) % MODULO;
        for (int i = 1; i < inputArray.length; i++) {
            int leafAmount = inputArray[i];
            int minusTwo = minusOne;
            minusOne = current;
            current = ((minusOne + minusTwo) * twoInPower(leafAmount)) % MODULO;
        }

        return (minusOne + current) % MODULO;
    }

    private static final int twoInPower(int power) {
//        return 1 << power;
//        return powerModular(2, power, 1000000007);
        int result = 1;
        for (int i = 0; i < power; i++) {
            result = (result << 1) % MODULO;
        }
        return result;
    }

    /* Iterative Function to calculate
   (x^y)%p in O(log y) */
    private static int powerModular(int x, int y, int p) {
        // Initialize result
        int res = 1;

        // Update x if it is more
        // than or equal to p
        x = x % p;

        while (y > 0) {
            // If y is odd, multiply x
            // with result
            if ((y & 1) == 1)
                res = (res * x) % p;

            // y must be even now
            // y = y / 2
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }
}
