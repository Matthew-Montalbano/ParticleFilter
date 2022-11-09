package org.example.mattmontalbano.particlefilter.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Particle extends TimeStateObject {

    private double _maxSpeed;
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
                    double maxSpeed,
                    double weight,
                    long time,
                    boolean wasSampled,
                    Map<Long, State> history,
                    NewRandom randGen) {
        super(x, y, xVel, yVel, time);
        _maxSpeed = maxSpeed;
        _weight = weight;
        _time = time;
        _wasSampled = wasSampled;
        _history = history;
        _randGen = randGen;
        setXVelocity(xVel);
        setYVelocity(yVel);
    }

    public Particle(double x,
                    double y,
                    double xVel,
                    double yVel,
                    double maxSpeed,
                    double weight,
                    long time,
                    NewRandom randGen) {
        this(x, y, xVel, yVel, maxSpeed, weight, time, false, new HashMap<>(), randGen);
    }

    public Particle(Particle oldParticle) {
        this(oldParticle._x,
             oldParticle._y,
             oldParticle.getXVelocity(),
             oldParticle.getYVelocity(),
             oldParticle._maxSpeed,
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

    @Override
    public void setXVelocity(double xVel) {
        _xVel = enforceSpeedBounds(xVel);
    }

    @Override
    public void setYVelocity(double yVel) {
        _yVel = enforceSpeedBounds(yVel);
    }

    private double enforceSpeedBounds(double speed) {
        if (speed < -_maxSpeed) {
            return -_maxSpeed;
        } else if (speed > _maxSpeed) {
            return _maxSpeed;
        } else {
            return speed;
        }
    }
}
