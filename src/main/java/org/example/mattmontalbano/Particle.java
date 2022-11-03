package org.example.mattmontalbano;

import java.util.HashMap;
import java.util.Map;

public class Particle extends TimeStateObject {
    private double _weight;
    private boolean _wasSampled;
    private final Map<Long, State> _history;

    public static double POSITION_PERTURBATION_AMOUNT = 2;
    public static double VELOCITY_PERTURBATION_AMOUNT = 0.5;
    public static double MANEUVER_AMOUNT = 2;

    public Particle(double x,
                    double y,
                    double xVel,
                    double yVel,
                    double weight,
                    long time,
                    boolean wasSampled,
                    Map<Long, State> history) {
        super(x, y, xVel, yVel, time);
        _weight = weight;
        _time = time;
        _wasSampled = wasSampled;
        _history = history;
    }

    public Particle(double x, double y, double xVel, double yVel, double weight, long time) {
        this(x, y, xVel, yVel, weight, time, false, new HashMap<>());
    }

    public Particle(PositionObservation observation, double weight, long time) {
        this(observation.getX(),
             observation.getY(),
             NewRandom.getInstance().nextDoubleBetween(-State.MAX_SPEED, State.MAX_SPEED),
             NewRandom.getInstance().nextDoubleBetween(-State.MAX_SPEED, State.MAX_SPEED),
             weight,
             time);
    }

    public Particle(Particle oldParticle) {
        this(oldParticle._state.getX(),
             oldParticle._state.getX(),
             oldParticle._state.getXVelocity(),
             oldParticle._state.getYVelocity(),
             oldParticle._weight,
             oldParticle._time,
             oldParticle._wasSampled,
             new HashMap<>(oldParticle._history));
    }

    public void perturb() {
        NewRandom randGen = NewRandom.getInstance();
        _state.setX(_state.getX() + randGen.nextDoubleBetween(-POSITION_PERTURBATION_AMOUNT,
                                                              POSITION_PERTURBATION_AMOUNT));
        _state.setY(_state.getY() + randGen.nextDoubleBetween(-POSITION_PERTURBATION_AMOUNT,
                                                              POSITION_PERTURBATION_AMOUNT));
        _state.setXVelocity(_state.getXVelocity() + randGen.nextDoubleBetween(-VELOCITY_PERTURBATION_AMOUNT,
                                                                              VELOCITY_PERTURBATION_AMOUNT));
        _state.setYVelocity(_state.getYVelocity() + randGen.nextDoubleBetween(-VELOCITY_PERTURBATION_AMOUNT,
                                                                              VELOCITY_PERTURBATION_AMOUNT));
    }

    public void maneuver() {
        NewRandom randGen = NewRandom.getInstance();
        _state.setXVelocity(_state.getXVelocity() + randGen.nextDoubleBetween(-MANEUVER_AMOUNT, MANEUVER_AMOUNT));
        _state.setYVelocity(_state.getYVelocity() + randGen.nextDoubleBetween(-MANEUVER_AMOUNT, MANEUVER_AMOUNT));
    }

    public void addCurrentStateToHistory() {
        _history.put(_time, _state);
    }

    public void moveForTime(long timePassed) {
        _state.setX(_state.getX() + _state.getXVelocity() * timePassed);
        _state.setY(_state.getY() + _state.getYVelocity() * timePassed);
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
