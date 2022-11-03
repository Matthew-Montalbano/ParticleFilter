package org.example.mattmontalbano;

import java.util.Random;

public class NewRandom extends Random {

    private static NewRandom _randomInstance = null;

    private NewRandom() {
        super();
    }

    public static NewRandom getInstance() {
        if (_randomInstance == null) {
            _randomInstance = new NewRandom();
        }
        return _randomInstance;
    }

    public double nextDoubleBetween(double min, double max) {
        return nextDouble() * (max - min) + min;
    }
}
