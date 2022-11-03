package org.example.mattmontalbano;

import java.util.Random;

public class NewRandom {

    private final Random _random;

    public NewRandom(Random random) {
        _random = random;
    }

    public double nextDoubleBetween(double min, double max) {
        return nextDouble() * (max - min) + min;
    }

    public double nextDouble() {
        return _random.nextDouble();
    }
}
