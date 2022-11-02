package org.example.mattmontalbano;

import java.util.Random;

public class NewRandom extends Random {

    public NewRandom() {
        super();
    }

    public NewRandom(long seed) {
        super(seed);
    }

    public double nextDoubleBetween(double min, double max) {
        return nextDouble() * (max - min) + min;
    }
}
