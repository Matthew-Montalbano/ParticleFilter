package org.example.mattmontalbano;

public class ParticleFilter {

    private Particle[] _particleSet;
    private int _numParticles;
    private long _currentTime;
    private NewRandom _randGen;

    public ParticleFilter(int numParticles, long startTime, Observation observation, NewRandom randGen) {
        _numParticles = numParticles;
        _currentTime = startTime;
        _randGen = randGen;
        initializeParticleSet(numParticles, observation);
    }

    private void initializeParticleSet(int numParticles, Observation observation) {
        _particleSet = new Particle[numParticles];
    }

    public void performMotionModelUpdate() {

    }

    private double poissonTimeIntervalCDF(long time, long eventRate) {
        return 1 - Math.exp(-time * eventRate);
    }

    public void assignNewParticleWeights(Observation observation) {

    }

    public void resample() {

    }
}
