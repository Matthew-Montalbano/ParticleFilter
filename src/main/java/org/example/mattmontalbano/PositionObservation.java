package org.example.mattmontalbano;

import org.apache.commons.math3.distribution.NormalDistribution;

public class PositionObservation implements Observation {
    private final double _x;
    private final double _y;
    private final double _standardDeviation;
    private final NormalDistribution _dist;

    public PositionObservation(double x, double y, double standardDeviation) {
        _x = x;
        _y = y;
        _standardDeviation = standardDeviation;
        _dist = new NormalDistribution(0, standardDeviation);
    }

    @Override
    public double computeLikelihood(Particle particle) {
        double distance = calculateDistance(_x, particle.getState().getX(), _y, particle.getState().getY());
        return _dist.density(distance);
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static PositionObservation[] createNObservations(TimeStateObject[] trueTargetState,
                                                            double standardDeviation) {
        PositionObservation[] observations = new PositionObservation[trueTargetState.length - 1];
        NewRandom randGen = NewRandom.getInstance();
        for (int i = 0; i < observations.length; i++) {
            State state = trueTargetState[i].getState();
            double x = state.getX() + randGen.nextDoubleBetween(-standardDeviation, standardDeviation);
            double y = state.getY() + randGen.nextDoubleBetween(-standardDeviation, standardDeviation);
            observations[i] = new PositionObservation(x, y, standardDeviation);
        }
        return observations;
    }

    public double getX() {
        return _x + NewRandom.getInstance().nextDoubleBetween(-_standardDeviation, _standardDeviation);
    }

    public double getY() {
        return _y + NewRandom.getInstance().nextDoubleBetween(-_standardDeviation, _standardDeviation);
    }

    public double getStandardDeviation() {
        return _standardDeviation;
    }
}
