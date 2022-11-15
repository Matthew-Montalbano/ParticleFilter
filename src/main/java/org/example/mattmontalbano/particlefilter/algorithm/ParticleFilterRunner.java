package org.example.mattmontalbano.particlefilter.algorithm;

public class ParticleFilterRunner {
    private final ParticleFilter _particleFilter;
    private final Scenario _scenario;
    private long _currentTime;
    private int _currentObservationIndex;


    public ParticleFilterRunner(ParticleFilter particleFilter, Scenario scenario) {
        _particleFilter = particleFilter;
        _scenario = scenario;
        _currentTime = scenario.getObservations()[0].getTime();
        _currentObservationIndex = 0;
    }

    public boolean processNextObservation() {
        if (_currentObservationIndex >= _scenario.getObservations().length - 1) {
            return false;
        }
        Observation observation = _scenario.getObservations()[_currentObservationIndex + 1];
        long prevObservationTime = _scenario.getObservations()[_currentObservationIndex].getTime();
        long currObservationTime = observation.getTime();
        long timeSinceLastObservation = currObservationTime - prevObservationTime;
        runObservationThroughParticleFilter(observation, timeSinceLastObservation);
        _currentObservationIndex += 1;
        _currentTime = currObservationTime;
        return true;
    }

    public void updateTime(long newTime) {
        if (newTime < _currentTime) {
            return;
        }
        int latestObservationIndex = getLatestObservationIndex(newTime);
        runObservationsThroughParticleFilter(_currentObservationIndex + 1, latestObservationIndex);
        _currentObservationIndex = latestObservationIndex;
        _currentTime = newTime;
    }

    // Get index of Observation whose time is the largest time smaller than the time parameter
    private int getLatestObservationIndex(long time) {
        Observation[] observations = _scenario.getObservations();
        int low = 0;
        int high = observations.length;
        while (low < high) {
            int mid = low + ((high - low) / 2);
            if (time < observations[mid].getTime()) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low - 1;
    }

    private void runObservationsThroughParticleFilter(int firstObservationIndex, int lastObservationIndex) {
        long prevObservationTime = _scenario.getObservations()[firstObservationIndex - 1].getTime();
        for (int i = firstObservationIndex; i <= lastObservationIndex; i++) {
            Observation observation = _scenario.getObservations()[i];
            long currObservationTime = observation.getTime();
            long timeSinceLastObservation = currObservationTime - prevObservationTime;
            runObservationThroughParticleFilter(observation, timeSinceLastObservation);
            prevObservationTime = currObservationTime;
        }
    }

    private void runObservationThroughParticleFilter(Observation observation, long timeSinceLastObservation) {
        _particleFilter.performMotionModelUpdate(timeSinceLastObservation);
        _particleFilter.weightParticlesAgainstObservation(observation);
        _particleFilter.resample();
    }

    public ParticleFilter getParticleFilter() {
        return _particleFilter;
    }

    public Scenario getScenario() {
        return _scenario;
    }

    public long getCurrentTime() {
        return _currentTime;
    }
}
