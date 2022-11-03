package org.example.mattmontalbano;

import java.util.Random;

public class NewRandomSingleton extends Random {

    private static NewRandomSingleton _randomInstance = null;

    private NewRandomSingleton() {
        super();
    }

    public static NewRandomSingleton getInstance() {
        if (_randomInstance == null) {
            _randomInstance = new NewRandomSingleton();
        }
        return _randomInstance;
    }

    public double nextDoubleBetween(double min, double max) {
        return nextDouble() * (max - min) + min;
    }
}
