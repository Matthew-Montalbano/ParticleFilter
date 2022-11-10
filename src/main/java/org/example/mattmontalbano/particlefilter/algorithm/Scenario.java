package org.example.mattmontalbano.particlefilter.algorithm;

public class Scenario {

    private final TimeStateObject[] _trueTargetState;
    private final PositionObservation[] _observations;

    public Scenario(TimeStateObject[] trueTargetState,
                    PositionObservation[] observations) {

        _trueTargetState = trueTargetState;
        _observations = observations;
    }

    public TimeStateObject[] getTrueTargetState() {
        return _trueTargetState;
    }

    public PositionObservation[] getObservations() {
        return _observations;
    }


}
