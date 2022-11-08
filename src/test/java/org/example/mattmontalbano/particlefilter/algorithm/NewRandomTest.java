package org.example.mattmontalbano.particlefilter.algorithm;

import org.junit.jupiter.api.Test;

import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NewRandomTest {

    @Test
    public void givenRandomZero_whenNextDoubleBetween_returnMin() {
        RandomGenerator randomMock = mock(RandomGenerator.class);
        when(randomMock.nextDouble()).thenReturn(0.0);
        NewRandom newRandom = new NewRandom(randomMock);
        double min = 2.0;
        double max = 4.0;

        double actual = newRandom.nextDoubleBetween(min, max);

        assertEquals(min, actual);
    }

    @Test
    public void givenRandomOne_whenNextDoubleBetween_returnMax() {
        RandomGenerator randomMock = mock(RandomGenerator.class);
        when(randomMock.nextDouble()).thenReturn(1.0);
        NewRandom newRandom = new NewRandom(randomMock);
        double min = 2.0;
        double max = 4.0;

        double actual = newRandom.nextDoubleBetween(min, max);

        assertEquals(max, actual);
    }
}
