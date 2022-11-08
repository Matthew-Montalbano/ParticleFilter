package org.example.mattmontalbano.particlefilter.algorithm;

public interface Observation {

    double computeLikelihood(Particle particle);

}
