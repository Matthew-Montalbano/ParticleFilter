package org.example.mattmontalbano.particlefilter.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParticleFilterTest {

    private final NewRandom _randGen = new NewRandom(new Random(100));
    private final int _numParticles = 10;
    private final long _meanManeuverTime = 10;
    private Particle[] _oldParticles;
    private Particle[] _particleSpies;
    private ParticleFilter _particleFilter;

    @BeforeEach
    public void init() {
        PositionObservation observation = new PositionObservation(0, 0, 3, 0, _randGen);
        _particleSpies = createParticleSpies(_numParticles, observation, 10);
    }

    @Nested
    class PerformMotionModelUpdateTest {

        @Test
        public void givenPositiveTimePassed_whenPerformMotionModelUpdate_allParticlesTimeUpdated() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(Particle::new)
                                  .toArray(Particle[]::new);

            _particleFilter.performMotionModelUpdate(10);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertNotEquals(_oldParticles[i].getTime(), newParticles[i].getTime());
            }
        }

        @Test
        public void givenPositiveTimePassed_whenPerformMotionModelUpdate_allParticlesXUpdated() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(Particle::new)
                                  .toArray(Particle[]::new);

            _particleFilter.performMotionModelUpdate(10);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertNotEquals(_oldParticles[i].getX(), newParticles[i].getX());
            }
        }

        @Test
        public void givenPositiveTimePassed_whenPerformMotionModelUpdate_allParticlesYUpdated() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(Particle::new)
                                  .toArray(Particle[]::new);

            _particleFilter.performMotionModelUpdate(10);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertNotEquals(_oldParticles[i].getY(), newParticles[i].getY());
            }
        }

        @Test
        public void givenNegativeTimePassed_whenPerformMotionModelUpdate_allParticlesTimeSame() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(Particle::new)
                                  .toArray(Particle[]::new);

            _particleFilter.performMotionModelUpdate(-1);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getTime(), newParticles[i].getTime());
            }
        }

        @Test
        public void givenNegativeTimePassed_whenPerformMotionModelUpdate_allParticlesXSame() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(Particle::new)
                                  .toArray(Particle[]::new);

            _particleFilter.performMotionModelUpdate(-1);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getX(), newParticles[i].getX());
            }
        }

        @Test
        public void givenNegativeTimePassed_whenPerformMotionModelUpdate_allParticlesYSame() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(Particle::new)
                                  .toArray(Particle[]::new);

            _particleFilter.performMotionModelUpdate(-1);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getY(), newParticles[i].getY());
            }
        }

        @Test
        public void givenZeroTimePassed_whenPerformMotionModelUpdate_allParticlesTimeSame() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(Particle::new)
                                  .toArray(Particle[]::new);

            _particleFilter.performMotionModelUpdate(0);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getTime(), newParticles[i].getTime());
            }
        }

        @Test
        public void givenZeroTimePassed_whenPerformMotionModelUpdate_allParticlesXSame() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(Particle::new)
                                  .toArray(Particle[]::new);

            _particleFilter.performMotionModelUpdate(0);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getX(), newParticles[i].getX());
            }
        }

        @Test
        public void givenZeroTimePassed_whenPerformMotionModelUpdate_allParticlesYSame() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(Particle::new)
                                  .toArray(Particle[]::new);

            _particleFilter.performMotionModelUpdate(0);

            Particle[] newParticles = _particleFilter.getParticleSet();
            for (int i = 0; i < _oldParticles.length; i++) {
                assertEquals(_oldParticles[i].getY(), newParticles[i].getY());
            }
        }

        @Test
        public void givenZeroMeanManeuverTime_whenPerformMotionModelUpdate_thenEveryParticleManeuver() {
            long meanManeuverTime = 0;
            _particleFilter = new ParticleFilter(_particleSpies, meanManeuverTime, _randGen);

            _particleFilter.performMotionModelUpdate(10);

            for (Particle particle : _particleFilter.getParticleSet()) {
                verify(particle, times(1)).maneuver();
            }
        }

        @Test
        public void givenInfiniteMeanManeuverTime_whenPerformMotionModelUpdate_thenNoParticleManeuver() {
            long meanManeuverTime = Long.MAX_VALUE;
            _particleFilter = new ParticleFilter(_particleSpies, meanManeuverTime, _randGen);

            _particleFilter.performMotionModelUpdate(10);

            for (Particle particle : _particleFilter.getParticleSet()) {
                verify(particle, never()).maneuver();
            }
        }
    }

    @Nested
    class WeightParticlesAgainstObservationTest {

        @Test
        public void givenObservation_whenWeightParticlesAgainstObservation_thenAllWeightsSumToOne() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
            PositionObservation observationSpy = spy(new PositionObservation(2, 5, 2, 0, _randGen));

            _particleFilter.weightParticlesAgainstObservation(observationSpy);

            verify(observationSpy, times(_numParticles)).computeLikelihood(any(Particle.class));
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
    }

    @Nested
    class ResampleTest {

        @BeforeEach
        public void init() {
            _particleFilter = new ParticleFilter(_particleSpies, _meanManeuverTime, _randGen);
        }

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

        @Test
        public void whenResample_thenSetWasResampledAtMostOnce() {
            _oldParticles = Arrays.stream(_particleFilter.getParticleSet())
                                  .map(particle -> spy(new Particle(particle)))
                                  .toArray(Particle[]::new);

            _particleFilter.resample();

            for (Particle particle : _oldParticles) {
                verify(particle, atMost(1)).setWasSampled(true);
            }
        }
    }

    private Particle[] createParticleSpies(int numParticles, PositionObservation observation, double maxSpeed) {
        Particle[] particleSpies = new Particle[numParticles];
        for (int i = 0; i < numParticles; i++) {
            double x = observation.getX();
            double y = observation.getY();
            double xVel = _randGen.nextDoubleBetween(maxSpeed, maxSpeed);
            double yVel = _randGen.nextDoubleBetween(maxSpeed, maxSpeed);
            particleSpies[i] = spy(new Particle(x, y, xVel, yVel, maxSpeed, 0, 0, _randGen));
        }
        return particleSpies;
    }
}
