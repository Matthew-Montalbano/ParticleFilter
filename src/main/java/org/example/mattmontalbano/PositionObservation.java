package org.example.mattmontalbano;

import org.apache.commons.math3.distribution.NormalDistribution;

public class PositionObservation implements Observation {
    private final double _x;
    private final double _y;
    private final double _standardDeviation;
    private final NewRandom _randGen;
    private final NormalDistribution _dist;

    public static final double STANDARD_DEVIATION = 3.0;

    public PositionObservation(double x, double y, double standardDeviation, NewRandom randGen) {
        _x = x;
        _y = y;
        _standardDeviation = standardDeviation;
        _randGen = randGen;
        _dist = new NormalDistribution(0, standardDeviation);
    }

    @Override
    public double computeLikelihood(Particle particle) {
        double distance = calculateDistance(_x, particle.getX(), _y, particle.getY());
        return _dist.density(distance);
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static PositionObservation[] createNObservations(TimeStateObject[] trueTargetState,
                                                            double standardDeviation,
                                                            NewRandom randGen) {
        PositionObservation[] observations = new PositionObservation[trueTargetState.length - 1];
        for (int i = 0; i < observations.length; i++) {
            TimeStateObject target = trueTargetState[i];
            double x = target.getX() + randGen.nextDoubleBetween(-standardDeviation, standardDeviation);
            double y = target.getY() + randGen.nextDoubleBetween(-standardDeviation, standardDeviation);
            observations[i] = new PositionObservation(x, y, standardDeviation, randGen);
        }
        return observations;
    }

    public double getX() {
        return _x + _randGen.nextDoubleBetween(-_standardDeviation, _standardDeviation);
    }

    public double getY() {
        return _y + _randGen.nextDoubleBetween(-_standardDeviation, _standardDeviation);
    }

    public double getStandardDeviation() {
        return _standardDeviation;
    }
}
