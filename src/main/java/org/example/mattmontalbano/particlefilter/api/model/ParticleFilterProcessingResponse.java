package org.example.mattmontalbano.particlefilter.api.model;

import org.example.mattmontalbano.particlefilter.algorithm.Particle;

public record ParticleFilterProcessingResponse(String id, long time, Particle[] particles) {
}
