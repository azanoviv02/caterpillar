package com.netcracker.caterpillar.spanning;

public class SpanningTreeSolution {

    public static final int MODULO = 1000000007;
    public static final int MODULO_UPPER_BOUND_POWER = getModuloUpperBoundPower();
    public static final int SHIFT_SIZE = 63 - MODULO_UPPER_BOUND_POWER;

    public static int getModuloUpperBoundPower() {
        int power = 0;
        int upperBound = 1;
        while (upperBound < MODULO) {
            upperBound = upperBound << 1;
            power++;
        }
        System.out.println(upperBound + " " + power);
        return power;
    }

    public static void main(String[] args) {

//        int[] inputArray = {0, 100, 0};
        int[] inputArray = {0, 3, 0, 1, 2, 1, 3, 1, 0};
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
        if (power < SHIFT_SIZE) {
            return 1 << power;
        } else {
            long result = 1;
            int remainingPower = power;
            while (remainingPower > SHIFT_SIZE) {
                result = (result << SHIFT_SIZE) % MODULO;
                remainingPower -= SHIFT_SIZE;
            }
            result = (result << remainingPower) % MODULO;
            return Long.valueOf(result).intValue();
        }
    }
}
