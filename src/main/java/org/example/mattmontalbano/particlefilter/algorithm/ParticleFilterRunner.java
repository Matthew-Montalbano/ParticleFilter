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

    public void updateTime(long newTime) {
        if (newTime < _currentTime) {
            return;
        }
        int latestObservationIndex = getLatestObservationIndex(newTime);
        for (int i = _currentObservationIndex + 1; i <= latestObservationIndex; i++) {
            Observation observation = _scenario.getObservations()[i];
            runObservationThroughParticleFilter(observation);
            _currentTime = observation.getTime();
        }
        _currentObservationIndex = latestObservationIndex;
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

    private void runObservationThroughParticleFilter(Observation observation) {
        _particleFilter.weightParticlesAgainstObservation(observation);
        _particleFilter.resample();
        _particleFilter.performMotionModelUpdate(observation.getTime() - _currentTime);
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
