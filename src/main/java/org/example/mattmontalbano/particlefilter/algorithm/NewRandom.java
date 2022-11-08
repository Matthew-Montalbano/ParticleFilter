package org.example.mattmontalbano.particlefilter.algorithm;

import java.util.random.RandomGenerator;

public class NewRandom {

    private final RandomGenerator _random;

    public NewRandom(RandomGenerator random) {
        _random = random;
    }

    public double nextDoubleBetween(double min, double max) {
        return nextDouble() * (max - min) + min;
    }

    public double nextDouble() {
        return _random.nextDouble();
    }
}
