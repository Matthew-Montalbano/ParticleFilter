package org.example.mattmontalbano;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParticleFilterTest {

    private ParticleFilter _particleFilter;
    private NewRandom _randGen;
    private int _numParticles;
    private Particle[] _oldParticles;

    @BeforeEach
    public void init() {
        _numParticles = 10;
        _randGen = new NewRandom(new Random(100));
        PositionObservation observation = new PositionObservation(0, 0, 3, 0, _randGen);
        _particleFilter = new ParticleFilter(_numParticles, 0, observation, _randGen);
    }

    @Nested
    class PerformMotionModelUpdateTest {

        @BeforeEach
        public void init() {
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(Particle::new)
                                  .toArray(Particle[]::new);
        }

        @Test
        public void givenPositiveTimePassed_whenPerformMotionModelUpdate_allParticlesTimeUpdated() {
            _particleFilter.performMotionModelUpdate(10);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertNotEquals(_oldParticles[i].getTime(), newParticles[i].getTime());
            }
        }

        @Test
        public void givenPositiveTimePassed_whenPerformMotionModelUpdate_allParticlesXUpdated() {
            _particleFilter.performMotionModelUpdate(10);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertNotEquals(_oldParticles[i].getX(), newParticles[i].getX());
            }
        }

        @Test
        public void givenPositiveTimePassed_whenPerformMotionModelUpdate_allParticlesYUpdated() {
            _particleFilter.performMotionModelUpdate(10);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertNotEquals(_oldParticles[i].getY(), newParticles[i].getY());
            }
        }

        @Test
        public void givenNegativeTimePassed_whenPerformMotionModelUpdate_allParticlesTimeSame() {
            _particleFilter.performMotionModelUpdate(-1);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getTime(), newParticles[i].getTime());
            }
        }

        @Test
        public void givenNegativeTimePassed_whenPerformMotionModelUpdate_allParticlesXSame() {
            _particleFilter.performMotionModelUpdate(-1);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getX(), newParticles[i].getX());
            }
        }

        @Test
        public void givenNegativeTimePassed_whenPerformMotionModelUpdate_allParticlesYSame() {
            _particleFilter.performMotionModelUpdate(-1);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getY(), newParticles[i].getY());
            }
        }

        @Test
        public void givenZeroTimePassed_whenPerformMotionModelUpdate_allParticlesTimeSame() {
            _particleFilter.performMotionModelUpdate(0);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getTime(), newParticles[i].getTime());
            }
        }

        @Test
        public void givenZeroTimePassed_whenPerformMotionModelUpdate_allParticlesXSame() {
            _particleFilter.performMotionModelUpdate(0);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getX(), newParticles[i].getX());
            }
        }

        @Test
        public void givenZeroTimePassed_whenPerformMotionModelUpdate_allParticlesYSame() {
            _particleFilter.performMotionModelUpdate(0);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getY(), newParticles[i].getY());
            }
        }
    }

    @Test
    public void givenObservation_whenWeightParticlesAgainstObservation_thenAllWeightsSumToOne() {
        PositionObservation observation = spy(new PositionObservation(2, 5, 2, 0, _randGen));

        _particleFilter.weightParticlesAgainstObservation(observation);

        verify(observation, times(_numParticles)).computeLikelihood(any(Particle.class));
        double weightSum = 0;
        for (Particle particle : _particleFilter.getParticleSet()) {
            weightSum += particle.getWeight();
        }
        // use approximation to account for imprecision in double math
        assertTrue(approximatelyOne(weightSum));
    }

    private boolean approximatelyOne(double num) {
        return 0.99999999 < num && num < 1.00000001;
    }

    @Nested
    class ResampleTest {
        @Test
        public void whenResample_thenParticleSetContainsNewObjects() {
            _oldParticles = _particleFilter.getParticleSet();

            _particleFilter.resample();

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _numParticles; i++) {
                assertNotEquals(_oldParticles[i], newParticles[i]);
            }
        }

        @Test
        public void whenResample_thenAllParticlesHaveEqualWeight() {
            _particleFilter.resample();

            Particle[] particleSet = _particleFilter.getParticleSet();
            for (Particle particle : particleSet) {
                assertEquals(1.0 / _numParticles, particle.getWeight());
            }
        }

        @Test
        public void whenResample_thenAllParticlesHaveFalseWasSampled() {
            _particleFilter.resample();

            Particle[] particleSet = _particleFilter.getParticleSet();
            for (Particle particle : particleSet) {
                assertFalse(particle.getWasSampled());
            }
        }
    }
}
