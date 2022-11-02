package org.example.mattmontalbano;

import org.apache.commons.math3.distribution.NormalDistribution;

public class PositionObservation implements Observation {
    private double _x;
    private double _y;
    private double _standardDeviation;
    private NewRandom _randGen;
    private NormalDistribution _dist;

    public PositionObservation(long x, long y, double standardDeviation, NewRandom randGen) {
        _x = x;
        _y = y;
        _standardDeviation = standardDeviation;
        _randGen = randGen;
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
