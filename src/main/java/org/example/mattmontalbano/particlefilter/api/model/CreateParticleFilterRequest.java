package org.example.mattmontalbano.particlefilter.api.model;

public record CreateParticleFilterRequest(int numParticles,
                                          String scenarioId,
                                          double maxSpeed,
                                          long meanManeuverTime) {
}
