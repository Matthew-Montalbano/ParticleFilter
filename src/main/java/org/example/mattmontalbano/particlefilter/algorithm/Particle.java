package org.example.mattmontalbano.particlefilter.algorithm;

import java.util.HashMap;
import java.util.Map;

public class Particle extends TimeStateObject {
    private double _weight;
    private boolean _wasSampled;
    private final Map<Long, State> _history;
    private final NewRandom _randGen;

    public static final double POSITION_PERTURBATION_AMOUNT = 1;
    public static final double VELOCITY_PERTURBATION_AMOUNT = 0.5;
    public static final double MANEUVER_AMOUNT = 2;

    public Particle(double x,
                    double y,
                    double xVel,
                    double yVel,
                    double weight,
                    long time,
                    boolean wasSampled,
                    Map<Long, State> history,
                    NewRandom randGen) {
        super(x, y, xVel, yVel, time);
        _weight = weight;
        _time = time;
        _wasSampled = wasSampled;
        _history = history;
        _randGen = randGen;
    }

    public Particle(double x, double y, double xVel, double yVel, double weight, long time, NewRandom randGen) {
        this(x, y, xVel, yVel, weight, time, false, new HashMap<>(), randGen);
    }

    public Particle(PositionObservation observation, double weight, long time, NewRandom randGen) {
        this(observation.getX(),
             observation.getY(),
             randGen.nextDoubleBetween(-State.MAX_SPEED, State.MAX_SPEED),
             randGen.nextDoubleBetween(-State.MAX_SPEED, State.MAX_SPEED),
             weight,
             time,
             randGen);
    }

    public Particle(Particle oldParticle) {
        this(oldParticle._x,
             oldParticle._y,
             oldParticle.getXVelocity(),
             oldParticle.getYVelocity(),
             oldParticle._weight,
             oldParticle._time,
             oldParticle._wasSampled,
             new HashMap<>(oldParticle._history),
             oldParticle._randGen);
    }

    public void perturb() {
        _x += _randGen.nextDoubleBetween(-POSITION_PERTURBATION_AMOUNT, POSITION_PERTURBATION_AMOUNT);
        _y += _randGen.nextDoubleBetween(-POSITION_PERTURBATION_AMOUNT, POSITION_PERTURBATION_AMOUNT);
        setXVelocity(getXVelocity() + _randGen.nextDoubleBetween(-VELOCITY_PERTURBATION_AMOUNT,
                                                                 VELOCITY_PERTURBATION_AMOUNT));
        setYVelocity(getYVelocity() + _randGen.nextDoubleBetween(-VELOCITY_PERTURBATION_AMOUNT,
                                                                 VELOCITY_PERTURBATION_AMOUNT));
    }

    public void maneuver() {
        setXVelocity(getXVelocity() + _randGen.nextDoubleBetween(-MANEUVER_AMOUNT, MANEUVER_AMOUNT));
        setYVelocity(getYVelocity() + _randGen.nextDoubleBetween(-MANEUVER_AMOUNT, MANEUVER_AMOUNT));
    }

    public void addCurrentStateToHistory() {
        _history.put(_time, new State(_x, _y, getXVelocity(), getYVelocity()));
    }

    public void moveForTime(long timePassed) {
        _x += getXVelocity() * timePassed;
        _y += getYVelocity() * timePassed;
        _time += timePassed;
    }

    public double getWeight() {
        return _weight;
    }

    public void setWeight(double weight) {
        _weight = weight;
    }

    public boolean getWasSampled() {
        return _wasSampled;
    }

    public void setWasSampled(boolean wasSampled) {
        _wasSampled = wasSampled;
    }
}
