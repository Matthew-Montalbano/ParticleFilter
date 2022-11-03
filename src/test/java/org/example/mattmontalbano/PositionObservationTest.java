package org.example.mattmontalbano;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class PositionObservationTest {

    @Test
    public void givenFarDistance_whenComputeLikelihood_returnLowProbability() {
        double standardDeviation = 4;
        Particle particleMock = mock(Particle.class);
        when(particleMock.getX()).thenReturn(standardDeviation * 4);
        when(particleMock.getY()).thenReturn(standardDeviation * 4);
        PositionObservation observation = new PositionObservation(0, 0, standardDeviation, new NewRandom(new Random()));

        double likelihood = observation.computeLikelihood(particleMock);

        assertTrue(likelihood < 0.0001);
    }

    @Test
    public void givenCloseDistance_whenComputeLikelihood_returnHighProbability() {
        double standardDeviation = 4;
        Particle particleMock = mock(Particle.class);
        when(particleMock.getX()).thenReturn(standardDeviation * 0.5);
        when(particleMock.getY()).thenReturn(standardDeviation * 0.5);
        PositionObservation observation = new PositionObservation(0, 0, standardDeviation, new NewRandom(new Random()));

        double likelihood = observation.computeLikelihood(particleMock);

        assertTrue(likelihood > 0.07);
    }
}
