package org.example.mattmontalbano.particlefilter.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateTest {

    @Test
    public void givenNumberLessThanMaxSpeed_whenSetVelocity_returnSameNumber() {
        State state = new State(0, 0, 0, 0);
        double newVelocity = State.MAX_SPEED - (0.5 * State.MAX_SPEED);

        state.setXVelocity(newVelocity);

        assertEquals(state.getXVelocity(), newVelocity);
    }

    @Test
    public void givenNumberGreaterThanMaxSpeed_whenSetVelocity_returnMaxSpeed() {
        State state = new State(0, 0, 0, 0);
        double newVelocity = State.MAX_SPEED + 1;

        state.setXVelocity(newVelocity);

        assertEquals(state.getXVelocity(), State.MAX_SPEED);
    }

    @Test
    public void givenBiggerNegativeNumberThanNegativeMaxSpeed_whenSetVelocity_returnNegativeMaxSpeed() {
        State state = new State(0, 0, 0, 0);
        double newVelocity = -State.MAX_SPEED - 1;

        state.setXVelocity(newVelocity);

        assertEquals(state.getXVelocity(), -State.MAX_SPEED);
    }
}
