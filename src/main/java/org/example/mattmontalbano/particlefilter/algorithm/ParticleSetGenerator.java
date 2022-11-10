package org.example.mattmontalbano.particlefilter.algorithm;

public class ParticleSetGenerator {

    public static Particle[] createParticlesAroundObservation(PositionObservation observation,
                                                              int numParticles,
                                                              long startTime,
                                                              double maxSpeed,
                                                              NewRandom randGen) {
        Particle[] particles = new Particle[numParticles];
        for (int i = 0; i < numParticles; i++) {
            particles[i] = new Particle(observation.getX(),
                                        observation.getY(),
                                        randGen.nextDoubleBetween(-maxSpeed, maxSpeed),
                                        randGen.nextDoubleBetween(-maxSpeed, maxSpeed),
                                        maxSpeed,
                                        0,
                                        startTime,
                                        randGen);
        }
        return particles;
    }
}
