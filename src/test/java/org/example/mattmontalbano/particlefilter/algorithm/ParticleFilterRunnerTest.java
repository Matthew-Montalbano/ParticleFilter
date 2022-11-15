package org.example.mattmontalbano.particlefilter.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParticleFilterRunnerTest {

    private ParticleFilterRunner _particleFilterRunner;
    private ParticleFilter _particleFilter;
    private Scenario _scenario;

    @BeforeEach
    public void init() {
        _particleFilter = mock(ParticleFilter.class);

        _scenario = mock(Scenario.class);
        long[] times = new long[]{0, 10, 20, 30};
        Observation[] observations = generateObservationMocksWithTimes(times);
        when(_scenario.getObservations()).thenReturn(observations);

        _particleFilterRunner = new ParticleFilterRunner(_particleFilter, _scenario);
    }

    @Test
    public void givenNewTimePassedOneObservation_whenUpdateTime_thenParticleFilterCycleRunOnce() {
        long updateTime = _scenario.getObservations()[1].getTime();

        _particleFilterRunner.updateTime(updateTime);

        verify(_particleFilter, times(1)).weightParticlesAgainstObservation(_scenario.getObservations()[1]);
        verify(_particleFilter, times(1)).resample();
        verify(_particleFilter, times(1)).performMotionModelUpdate(updateTime);
        verifyNoMoreInteractions(_particleFilter);
    }

    @Test
    public void givenNewTimePassedAllObservations_whenUpdateTime_thenParticleFilterCycleRunForAllObservations() {
        int numObservations = _scenario.getObservations().length;
        long updateTime = _scenario.getObservations()[numObservations - 1].getTime();

        _particleFilterRunner.updateTime(updateTime);


        verify(_particleFilter, times(numObservations - 1)).weightParticlesAgainstObservation(any());
        verify(_particleFilter, times(numObservations - 1)).resample();
        verify(_particleFilter, times(numObservations - 1)).performMotionModelUpdate(anyLong());
        verifyNoMoreInteractions(_particleFilter);
    }

    @Test
    public void givenTimeBeforeNextObservation_whenUpdateTime_thenParticleFilterCycleDoesNotRun() {
        Observation[] observations = _scenario.getObservations();
        long updateTime =
                observations[1].getTime() - (long) ((observations[1].getTime() - observations[0].getTime()) * 0.5);

        _particleFilterRunner.updateTime(updateTime);

        verifyNoInteractions(_particleFilter);
    }

    @Test
    public void givenNewTimeBeforeCurrentTime_whenUpdateTime_thenParticleFilterCycleDoesNotRun() {
        long updateTime = -1;

        _particleFilterRunner.updateTime(updateTime);

        verifyNoInteractions(_particleFilter);
    }

    @Test
    public void givenAllObservationsProcessed_whenProcessNextObservation_thenParticleFilterCycleRunsOnce() {
        boolean observationWasProcessed = _particleFilterRunner.processNextObservation();

        verify(_particleFilter, times(1)).weightParticlesAgainstObservation(_scenario.getObservations()[1]);
        verify(_particleFilter, times(1)).resample();
        verify(_particleFilter, times(1)).performMotionModelUpdate(anyLong());
        assertTrue(observationWasProcessed);
    }

    @Test
    public void givenAllObservationsProcessed_whenProcessNextObservation_thenParticleFilterCycleDoesNotRun() {
        for (int i = 0; i < _scenario.getObservations().length - 1; i++) {
            _particleFilterRunner.processNextObservation();
        }

        boolean observationWasProcessed = _particleFilterRunner.processNextObservation();

        assertFalse(observationWasProcessed);
    }

    private Observation[] generateObservationMocksWithTimes(long[] times) {
        Observation[] observationMocks = new Observation[times.length];
        for (int i = 0; i < times.length; i++) {
            Observation observationMock = mock(Observation.class);
            when(observationMock.getTime()).thenReturn(times[i]);
            observationMocks[i] = observationMock;
        }
        return observationMocks;
    }
}
