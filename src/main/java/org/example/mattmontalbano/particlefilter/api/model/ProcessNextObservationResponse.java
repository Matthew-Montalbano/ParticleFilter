package org.example.mattmontalbano.particlefilter.api.model;

import org.example.mattmontalbano.particlefilter.algorithm.Particle;

public record ProcessNextObservationResponse(long time, Particle[] particles) {
}
