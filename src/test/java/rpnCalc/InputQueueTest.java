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

    // @Ignore
    @Test
    public void addInputDoesNotingIfEmpty() {
        InputQueue iq = new InputQueue("1 2 3");
        assertEquals(3, iq.size());
        iq.addInput("");
        assertEquals(3, iq.size());
    }

    //    @Ignore
    @Test
    public void addInputDoesNothingWithWhiteSpaceInput() {
        InputQueue iq = new InputQueue();
        assertEquals(0, iq.size());
        iq.addInput(" ");
        assertEquals(0, iq.size());
    }

    //    @Ignore
    @Test
    public void addInputAddsAsManyTokensAsInInput() {
        InputQueue iq = new InputQueue();
        iq.addInput("1 2 3");
        assertEquals(3, iq.size());
    }

    // @Ignore
    @Test
    public void addInputDoesNotDiscardPreviousInputs() {
        InputQueue iq = new InputQueue("1 2 3");
        assertEquals(3, iq.size());
        iq.addInput("4 5");
        assertEquals(5, iq.size());
    }

    // @Ignore
    @Test
    public void addInputAddsTokensInRightOrder() {
        InputQueue iq = new InputQueue("1 2 3");
        iq.addInput("4 5");
        assertEquals("1", iq.pop());
        assertEquals("2", iq.pop());
        assertEquals("3", iq.pop());
        assertEquals("4", iq.pop());
        assertEquals("5", iq.pop());
    }
}
