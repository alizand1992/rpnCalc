package rpnCalc;

import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.reflect.Field;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

public class RpnCalcTest {
    private static final double DELTA = 1e-15;

    // @Ignore
    @Test
    public void emptyConstructorHasResultOfZero() {
        RpnCalc rpn = new RpnCalc();
        assertEquals(new BigDecimal(0.0), rpn.getResult());
    }

    // @Ignore
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

    // @Ignore
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

    // @Ignore
    @Test
    public void addInputCreatesInputButDoesNothingForEmptyInput() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc();
        rpn.addInput("");
        assertEquals(new BigDecimal(0.0), rpn.getResult());
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
    public void addInputAddsAsManyAsThereAreTokens() throws IllegalInputException {
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

    // @Ignore
    @Test(expected = IllegalInputException.class)
    public void addInputThrowsExceptionForNonAuthorizedCharacters() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc();
        rpn.addInput("a");
        rpn.addInput("b");
        rpn.addInput("c");
        rpn.addInput("d");
    }

    // @Ignore
    @Test
    public void addInputDoesNotThrowExceptionForAllowedOps() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc();
        rpn.addInput("+");
        rpn.addInput("-");
        rpn.addInput("*");
        rpn.addInput("/");
    }

    // @Ignore
    @Test
    public void addInputDoesNotThrowExceptionForNumbers() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc();
        rpn.addInput("1");
        rpn.addInput("+1");
        rpn.addInput("-1");
        rpn.addInput("1.2");
        rpn.addInput(".2");
    }

    // @Ignore
    @Test
    public void calculateDoesNothingWhenThereAreNoInputs() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc();
        rpn.calculate();
        assertEquals(new BigDecimal(0.0), rpn.getResult());

        rpn.addInput("1 2");
        rpn.calculate();
        assertEquals(new BigDecimal(0.0), rpn.getResult());
    }

    // @Ignore
    @Test
    public void calculateAddsTwoNumbersIfThereAreSufficientInputs() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc("1 2 +");
        assertEquals(new BigDecimal(0.0), rpn.getResult());
        rpn.calculate();
        assertEquals(new BigDecimal(3.0), rpn.getResult());
    }

    // @Ignore
    @Test
    public void calculateDeductsTwoNumbersIfThereAreSuffientInputs() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc("1 2 -");
        assertEquals(new BigDecimal(0.0), rpn.getResult());
        rpn.calculate();
        assertEquals(new BigDecimal(-1.0), rpn.getResult());
    }

    // @Ignore
    @Test
    public void calculateMultiplesTwoNumbersIfThereAreSufficientInputs() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc("2 3 *");
        assertEquals(new BigDecimal(0.0), rpn.getResult());
        rpn.calculate();
        assertEquals(new BigDecimal(6.0), rpn.getResult());
    }

    // @Ignore
    @Test
    public void calculateDividesTwoNumbersIfThereAreSufficientInputs() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc("5 2 /");
        assertEquals(new BigDecimal(0.0), rpn.getResult());
        rpn.calculate();
        assertEquals(new BigDecimal(2.5), rpn.getResult());
    }

    // @Ignore
    @Test(expected = IllegalInputException.class)
    public void calculateHandlesDivisionByZero() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc("5 0 /");
        assertEquals(new BigDecimal(0.0), rpn.getResult());
        rpn.calculate();
        assertEquals(new BigDecimal(0.0), rpn.getResult());
    }

    // @Ignore
    @Test
    public void calculateHandlesMoreThanOneIteration() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc("1 2 + 4 -");
        rpn.calculate();
        assertEquals(new BigDecimal(-1.0), rpn.getResult());
    }

    // @Ignore
    @Test
    public void calculateWithThreeDigitsInRow() throws IllegalInputException {
        RpnCalc rpn = new RpnCalc("1 2 3 + -");
        rpn.calculate();
        assertEquals(new BigDecimal(-4.0), rpn.getResult());
    }

    // @Ignore
    @Test
    public void isOpReturnsTrueForOps() {
        assertTrue(RpnCalc.isOperator("+"));
        assertTrue(RpnCalc.isOperator("-"));
        assertTrue(RpnCalc.isOperator("*"));
        assertTrue(RpnCalc.isOperator("/"));
    }

}
