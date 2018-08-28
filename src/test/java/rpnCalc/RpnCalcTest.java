package com.rpnCalc;

import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.reflect.Field;

import org.junit.Ignore;
import org.junit.Test;

public class RpnCalcTest {
    private static final double DELTA = 1e-15;

    //    @Ignore
    @Test
    public void emptyConstructorHasResultOfZero() {
        RpnCalc rpn = new RpnCalc();
        assertEquals(0.0, rpn.getResult(), DELTA);
    }

    //    @Ignore
    @Test
    public void emptyConstructorHasInputsSetToNull() {
        try {
            RpnCalc rpn = new RpnCalc();
            Field field = rpn.getClass().getDeclaredField("inputs");
            field.setAccessible(true);
            InputQueue inputs = (InputQueue)field.get(rpn);
            assertNotNull(inputs);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    //    @Ignore
    @Test
    public void constructorWithInputAddsToTheInputs() {
        try {
            RpnCalc rpn = new RpnCalc("1 2 3");
            Field field = rpn.getClass().getDeclaredField("inputs");
            field.setAccessible(true);
            InputQueue inputs = (InputQueue)field.get(rpn);
            assertEquals(3, inputs.size());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    // @Ignore
    @Test
    public void constructorWithInputAddsAllTokensInOrder() {
        try {
            RpnCalc rpn = new RpnCalc("1 2 3");
            Field field = rpn.getClass().getDeclaredField("inputs");
            field.setAccessible(true);
            InputQueue inputs = (InputQueue)field.get(rpn);
            assertEquals("1", inputs.pop());
            assertEquals("2", inputs.pop());
            assertEquals("3", inputs.pop());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    //    @Ignore
    @Test
    public void addInputCreatesInputButDoesNothingForEmptyInput() {
        RpnCalc rpn = new RpnCalc();
        rpn.addInput("");
        assertEquals(0.0, rpn.getResult(), DELTA);
        try {
            Field field = rpn.getClass().getDeclaredField("inputs");
            field.setAccessible(true);
            InputQueue inputs = (InputQueue)field.get(rpn);
            assertNotNull(inputs);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    // @Ignore
    @Test
    public void addInputAddsAsManyAsThereAreTokens() {
        RpnCalc rpn = new RpnCalc();
        rpn.addInput("1 2 3");
        try {
            Field field = rpn.getClass().getDeclaredField("inputs");
            field.setAccessible(true);
            InputQueue inputs = (InputQueue)field.get(rpn);
            assertEquals("1", inputs.pop());
            assertEquals("2", inputs.pop());
            assertEquals("3", inputs.pop());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Ignore
    @Test
    public void calculateDoesNothingWhenThereAreNoInputs() {
        RpnCalc rpn = new RpnCalc();
        rpn.calculate();
        assertEquals(0.0, rpn.getResult(), DELTA);
    }
}
