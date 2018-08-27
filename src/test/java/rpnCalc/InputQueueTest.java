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

    // @Ignore
    @Test
    public void constructorWithMultipleTokensIncreasesTheSizeAsMany() {
        InputQueue iq = new InputQueue("1 2 3");
        assertEquals(3, iq.size());
    }

    // @Ignore
    @Test
    public void popReturnsNullIfEmptu() {
        InputQueue iq = new InputQueue();
        assertNull(iq.pop());
    }

    // @Ignore
    @Test
    public void popRetrievesHead() {
        InputQueue iq = new InputQueue("1 2 3");
        assertEquals("1", iq.pop());
    }

    // @Ignore
    @Test
    public void popRemovesHead() {
        InputQueue iq = new InputQueue("1 2 3");
        iq.pop();
        assertEquals("2", iq.pop());
    }

    // @Ignore
    @Test
    public void inputAddsTokensInCorrectOrder() {
        InputQueue iq = new InputQueue("1 2 3");
        assertEquals("1", iq.pop());
        assertEquals("2", iq.pop());
        assertEquals("3", iq.pop());
    }
}
