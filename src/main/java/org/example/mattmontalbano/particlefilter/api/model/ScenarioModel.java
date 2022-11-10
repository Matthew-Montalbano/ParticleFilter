package org.example.mattmontalbano.particlefilter.api.model;

import org.example.mattmontalbano.particlefilter.algorithm.TimeStateObject;

public record ScenarioModel(String id,
                            TimeStateObject[] trueTargetStates,
                            double standardDeviation,
                            long seed) {
}
