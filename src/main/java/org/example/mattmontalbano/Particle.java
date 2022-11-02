package org.example.mattmontalbano;

import java.util.HashMap;
import java.util.Map;

public class Particle extends TimeStateObject {

    private double _weight;
    private Map<Long, State> _history;

    public Particle(double x, double y, double xVel, double yVel, double weight, long time) {
        super(x, y, xVel, yVel, time);
        _weight = weight;
        _history = new HashMap<>();
    }

    public void perturb() {

    }

    public void maneuver() {

    }

    public void addCurrentStateToHistory() {
        _history.put(_time, _state);
    }

    public void moveForTime(long timePassed) {

    }
}
