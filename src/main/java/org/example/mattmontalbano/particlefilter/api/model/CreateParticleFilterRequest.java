package org.example.mattmontalbano.particlefilter.api.model;

public record CreateParticleFilterRequest(String scenarioId,
                                          int numParticles,
                                          double maxSpeed,
                                          long meanManeuverTime) {
}
