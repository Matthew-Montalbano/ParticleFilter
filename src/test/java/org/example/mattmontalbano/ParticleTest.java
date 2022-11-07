package org.example.mattmontalbano;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Random;

public class ParticleTest {

    @Nested
    class moveForTimeTest {
        private Particle _particle;
        private long _timePassed;

        @BeforeEach
        public void init() {
            _particle = new Particle(5, -3, 2, -4, 0, 0, false, new HashMap<>(), new NewRandom(new Random()));
            _timePassed = 10;
        }

        @Test
        public void givenPositiveTime_whenMoveForTime_returnProperX() {
            _particle.moveForTime(_timePassed);

            assertEquals(25, _particle.getX());
        }

        @Test
        public void givenPositiveTime_whenMoveForTime_returnProperY() {
            _particle.moveForTime(_timePassed);

            assertEquals(-43, _particle.getY());
        }

        @Test
        public void givenPositiveTime_whenMoveForTime_returnProperTime() {
            _particle.moveForTime(_timePassed);

            assertEquals(10, _particle.getTime());
        }
    }

    @Nested
    class perturbTest {
        @Test
        public void givenNonZeroRandomNumber_whenPerturb_thenXUpdated() {
            NewRandom randGen = mock(NewRandom.class);
            when(randGen.nextDoubleBetween(anyDouble(), anyDouble())).thenReturn(0.2);
            int x = 5;
            Particle particle = new Particle(x, -3, 2, -4, 0, 0, false, new HashMap<>(), randGen);

            particle.perturb();

            assertNotEquals(x, particle.getX());
        }

        @Test
        public void givenNonZeroRandomNumber_whenPerturb_thenYUpdated() {
            NewRandom randGen = mock(NewRandom.class);
            when(randGen.nextDoubleBetween(anyDouble(), anyDouble())).thenReturn(0.2);
            int y = -3;
            Particle particle = new Particle(5, y, 2, -4, 0, 0, false, new HashMap<>(), randGen);

            particle.perturb();

            assertNotEquals(y, particle.getY());
        }

        @Test
        public void givenNonZeroRandomNumber_whenPerturb_thenXVelocityUpdated() {
            NewRandom randGen = mock(NewRandom.class);
            when(randGen.nextDoubleBetween(anyDouble(), anyDouble())).thenReturn(0.2);
            int xVel = 2;
            Particle particle = new Particle(5, -3, xVel, -4, 0, 0, false, new HashMap<>(), randGen);

            particle.perturb();

            assertNotEquals(xVel, particle.getXVelocity());
        }

        @Test
        public void givenNonZeroRandomNumber_whenPerturb_thenYVelocityUpdated() {
            NewRandom randGen = mock(NewRandom.class);
            when(randGen.nextDoubleBetween(anyDouble(), anyDouble())).thenReturn(0.2);
            int yVel = -4;
            Particle particle = new Particle(5, -3, 2, yVel, 0, 0, false, new HashMap<>(), randGen);

            particle.perturb();

            assertNotEquals(yVel, particle.getYVelocity());
        }

        @Test
        public void givenZeroRandomNumber_whenPerturb_thenXStaysTheSame() {
            NewRandom randGen = mock(NewRandom.class);
            when(randGen.nextDoubleBetween(anyDouble(), anyDouble())).thenReturn(0.0);
            int x = 5;
            Particle particle = new Particle(x, -3, 2, -4, 0, 0, false, new HashMap<>(), randGen);

            particle.perturb();

            assertEquals(x, particle.getX());
        }

        @Test
        public void givenZeroRandomNumber_whenPerturb_thenYStaysTheSame() {
            NewRandom randGen = mock(NewRandom.class);
            when(randGen.nextDoubleBetween(anyDouble(), anyDouble())).thenReturn(0.0);
            int y = -3;
            Particle particle = new Particle(5, y, 2, -4, 0, 0, false, new HashMap<>(), randGen);

            particle.perturb();

            assertEquals(y, particle.getY());
        }

        @Test
        public void givenZeroRandomNumber_whenPerturb_thenXVelocityStaysTheSame() {
            NewRandom randGen = mock(NewRandom.class);
            when(randGen.nextDoubleBetween(anyDouble(), anyDouble())).thenReturn(0.0);
            int xVel = 2;
            Particle particle = new Particle(5, -3, xVel, -4, 0, 0, false, new HashMap<>(), randGen);

            particle.perturb();

            assertEquals(xVel, particle.getXVelocity());
        }

        @Test
        public void givenZeroRandomNumber_whenPerturb_thenYVelocityStaysTheSame() {
            NewRandom randGen = mock(NewRandom.class);
            when(randGen.nextDoubleBetween(anyDouble(), anyDouble())).thenReturn(0.0);
            int yVel = -4;
            Particle particle = new Particle(5, -3, 2, yVel, 0, 0, false, new HashMap<>(), randGen);

            particle.perturb();

            assertEquals(yVel, particle.getYVelocity());
        }
    }
}
