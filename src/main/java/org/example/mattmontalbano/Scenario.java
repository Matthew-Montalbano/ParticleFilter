package org.example.mattmontalbano;

public class Scenario {

    private final TimeStateObject[] _trueTargetState;
    private final PositionObservation[] _observations;
    private final long _startTime;
    private final long _endTime;

    public Scenario(long startTime,
                    long endTime,
                    TimeStateObject[] trueTargetState,
                    PositionObservation[] observations) {
        _startTime = startTime;
        _endTime = endTime;
        _trueTargetState = trueTargetState;
        _observations = observations;
    }

    public TimeStateObject[] getTrueTargetState() {
        return _trueTargetState;
    }

    public PositionObservation[] getObservations() {
        return _observations;
    }

    public long getStartTime() {
        return _startTime;
    }

    public long getEndTime() {
        return _endTime;
    }


}
