package org.example.mattmontalbano.particlefilter.algorithm;

public class Scenario {

    private final TimeStateObject[] _trueTargetState;
    private final Observation[] _observations;

    public Scenario(TimeStateObject[] trueTargetState,
                    Observation[] observations) {

        _trueTargetState = trueTargetState;
        _observations = observations;
    }

    public TimeStateObject[] getTrueTargetState() {
        return _trueTargetState;
    }

    public Observation[] getObservations() {
        return _observations;
    }


}
