package org.example.mattmontalbano.particlefilter.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PositionObservationTest {

    @Test
    public void givenFarDistance_whenComputeLikelihood_returnLowProbability() {
        double standardDeviation = 4;
        Particle particleMock = mock(Particle.class);
        when(particleMock.getX()).thenReturn(standardDeviation * 4);
        when(particleMock.getY()).thenReturn(standardDeviation * 4);
        PositionObservation observation = new PositionObservation(0,
                                                                  0,
                                                                  standardDeviation,
                                                                  0,
                                                                  new NewRandom(new Random()));

        double likelihood = observation.computeLikelihood(particleMock);

        assertTrue(likelihood < 0.0001);
    }

    @Test
    public void givenCloseDistance_whenComputeLikelihood_returnHighProbability() {
        double standardDeviation = 4;
        Particle particleMock = mock(Particle.class);
        when(particleMock.getX()).thenReturn(standardDeviation * 0.1);
        when(particleMock.getY()).thenReturn(standardDeviation * 0.1);
        PositionObservation observation = new PositionObservation(0,
                                                                  0,
                                                                  standardDeviation,
                                                                  0,
                                                                  new NewRandom(new Random()));

        double likelihood = observation.computeLikelihood(particleMock);

        assertTrue(likelihood > 0.07);
    }

    @Test
    public void givenProperParameters_whenCreateObservations_returnArrayCorrectSize() {
        int arraySize = 3;
        double standardDeviation = 3;
        TimeStateObject[] trueTargetState = generateNTimeStateObjects(arraySize);

        Observation[] observations = PositionObservation.createObservations(trueTargetState,
                                                                            standardDeviation,
                                                                            new NewRandom(new Random()));

        assertEquals(arraySize, observations.length);
    }

    @Test
    public void givenEmptyTargetArray_whenCreateObservations_returnEmptyArray() {
        int arraySize = 0;
        double standardDeviation = 3;
        TimeStateObject[] trueTargetState = generateNTimeStateObjects(arraySize);

        Observation[] observations = PositionObservation.createObservations(trueTargetState,
                                                                            standardDeviation,
                                                                            new NewRandom(new Random()));

        assertEquals(arraySize, observations.length);
    }

    /*
    @Test
    public void givenZeroStandardDeviation_whenCreateObservations_returnObservationsSameStatesAsTarget() {
        int arraySize = 3;
        double standardDeviation = 0;
        TimeStateObject[] trueTargetState = generateNTimeStateObjects(arraySize);
        PositionObservation[] expectedObservations = new PositionObservation[arraySize];
        for (int i = 0; i < arraySize; i++) {
            TimeStateObject target = trueTargetState[i];
            expectedObservations[i] = new PositionObservation(target.getX(),
                                                              target.getY(),
                                                              standardDeviation,
                                                              target.getTime(),
                                                              new NewRandom(new Random()));
        }


        Observation[] observations = PositionObservation.createObservations(trueTargetState,
                                                                            standardDeviation,
                                                                            new NewRandom(new Random()));

        assertArrayEquals(expectedObservations, observations);
    }
     */

    private TimeStateObject[] generateNTimeStateObjects(int numObjects) {
        TimeStateObject[] array = new TimeStateObject[numObjects];
        for (int i = 0; i < numObjects; i++) {
            array[i] = new TimeStateObject(i, i, i, i, i);
        }
        return array;
    }
}
