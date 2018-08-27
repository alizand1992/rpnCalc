package com.rpnCalc;

import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.Ignore;
import org.junit.Test;

public class InputQueueTest {
    // @Ignore
    @Test
    public void defaultConstructorDoesNotIncreaseTheSize() {
        InputQueue iq = new InputQueue();
        assertEquals(0, iq.size());
    }

    // @Ignore
    @Test
    public void constructorWithEmptyStringDoesNotIncreaseTheSize() {
        InputQueue iq = new InputQueue("");
        assertEquals(0, iq.size());
    }

    // @Ignore
    @Test
    public void constructorWithOneTokenIncreasesSizeToOne() {
        InputQueue iq = new InputQueue("1");
        assertEquals(1, iq.size());
    }
}
