package org.example.mattmontalbano.particlefilter.algorithm;

public class ParticleFilter {

    private Particle[] _particleSet;
    private final long _meanManeuverTime;
    private final NewRandom _randGen;

    public ParticleFilter(Particle[] particleSet,
                          long meanManeuverTime,
                          NewRandom randGen) {
        _particleSet = particleSet;
        _meanManeuverTime = meanManeuverTime;
        _randGen = randGen;
        resetParticleWeights();
    }

    public void performMotionModelUpdate(long timePassed) {
        if (timePassed <= 0) {
            return;
        }
        double maneuverChance = _meanManeuverTime == 0 ? 1 : poissonTimeIntervalCDF(timePassed, 1 / _meanManeuverTime);
        for (Particle particle : _particleSet) {
            particle.addCurrentStateToHistory();
            if (_randGen.nextDouble() < maneuverChance) {
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
            Particle sampledParticle = distribution.sampleParticle(_randGen);
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

    public Particle[] getParticleSet() {
        return _particleSet;
    }
}
