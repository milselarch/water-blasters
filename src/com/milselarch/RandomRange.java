package com.milselarch;

import java.util.Random;

/** Generate random integers in a certain range. */
public final class RandomRange {
    private Random random;

    RandomRange() {
        random = new Random();
    }

    public static int rand(int aStart, int aEnd, Random aRandom) {
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }

        //get the range, casting to long to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        int randomNumber =  (int) (fraction + aStart);
        //log("Generated : " + randomNumber);
        return randomNumber;
    }

    public int rand(int aStart, int aEnd) {
        return this.rand(aStart, aEnd, this.random);
    }

    private static void log(String aMessage){
        System.out.println(aMessage);
    }
}
