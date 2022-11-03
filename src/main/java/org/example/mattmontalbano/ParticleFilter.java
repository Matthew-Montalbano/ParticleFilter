package org.example.mattmontalbano;

public class ParticleFilter {

    private Particle[] _particleSet;
    private int _numParticles;
    private long _currentTime;

    public static long MEAN_MANEUVER_TIME;

    public ParticleFilter(int numParticles, long startTime, PositionObservation observation) {
        _numParticles = numParticles;
        _currentTime = startTime;
        _particleSet = createParticleSet(numParticles, startTime, observation);
    }

    private Particle[] createParticleSet(int numParticles,
                                         long time,
                                         PositionObservation observation) {
        Particle[] particles = new Particle[numParticles];
        for (int i = 0; i < numParticles; i++) {
            particles[i] = new Particle(observation, 1.0 / numParticles, time);
        }
        return particles;
    }

    public void performMotionModelUpdate(long timePassed) {
        double maneuverChance = poissonTimeIntervalCDF(timePassed, 1 / MEAN_MANEUVER_TIME);
        NewRandomSingleton randGen = NewRandomSingleton.getInstance();
        for (Particle particle : _particleSet) {
            particle.addCurrentStateToHistory();
            if (randGen.nextDouble() < maneuverChance) {
                particle.maneuver();
            }
            particle.moveForTime(timePassed);
        }
    }

    private double poissonTimeIntervalCDF(long time, long eventRate) {
        return 1 - Math.exp(-time * eventRate);
    }

    public void weightParticlesAgainstObservation(Observation observation) {
        double weightSum = 0;
        for (Particle particle : _particleSet) {
            double newWeight = observation.computeLikelihood(particle);
            weightSum += newWeight;
            particle.setWeight(newWeight);
        }
        normalizeParticleWeights(weightSum);
    }

    private void normalizeParticleWeights(double weightSum) {
        for (Particle particle : _particleSet) {
            particle.setWeight(particle.getWeight() / weightSum);
        }
    }

    public void resample() {
        Particle[] newParticles = new Particle[_particleSet.length];
        ParticleWeightDistribution distribution = new ParticleWeightDistribution(_particleSet);
        for (int i = 0; i < newParticles.length; i++) {
            Particle sampledParticle = distribution.sampleParticle();
            Particle newParticle = new Particle(sampledParticle);
            if (sampledParticle.getWasSampled()) {
                newParticle.perturb();
            } else {
                sampledParticle.setWasSampled(true);
            }
            newParticle.setWasSampled(false);
            newParticles[i] = newParticle;
        }
        _particleSet = newParticles;
        resetParticleWeights();
    }

    private void resetParticleWeights() {
        for (Particle particle : _particleSet) {
            particle.setWeight(1.0 / _particleSet.length);
        }
    }
}
