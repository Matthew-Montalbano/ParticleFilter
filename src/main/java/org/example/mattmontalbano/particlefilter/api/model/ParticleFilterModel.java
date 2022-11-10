package org.example.mattmontalbano.particlefilter.api.model;

import org.example.mattmontalbano.particlefilter.algorithm.ParticleFilterRunner;

public record ParticleFilterModel(String id, ParticleFilterRunner particleFilter) {
}
