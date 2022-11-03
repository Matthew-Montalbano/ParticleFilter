package org.example.mattmontalbano;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
