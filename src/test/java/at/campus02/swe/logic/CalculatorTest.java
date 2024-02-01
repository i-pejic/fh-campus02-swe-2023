package at.campus02.swe.logic;

import at.campus02.swe.Calculator;
import at.campus02.swe.Calculator.Operation;
import at.campus02.swe.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testSimpleMulOperation() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(2.0);
        calc.push(3);
        double result = calc.perform(Operation.mul);

        assertEquals(6, result, 0);

    }

    @Test
    public void testSimpleDivOperation() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(6.0);
        calc.push(2);
        double result = calc.perform(Operation.div);

        assertEquals(3, result, 0);

    }


    //
    @Test(expected = CalculatorException.class)
    public void testPopOnEmptyStack() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.pop();

    }

    @Test
    public void testDivisionByZero() throws Exception {

        //Setup
        Calculator calc = new CalculatorImpl();
        try {
            calc.push(2);
            calc.push(0);
            calc.perform(Operation.div);

            fail("Exception expected");


        } catch (CalculatorException e) {
            assertEquals("Division by zero", e.getMessage());
            // e.getCause()
        }


    }

    @Test
    public void testSimpleModuloOperation() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(6.0);
        calc.push(2);
        double result = calc.perform(Operation.mod);

        assertEquals(0, result, 0);

    }

    @Test
    public void testSimpleModuloOperation2() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(10.0);
        calc.push(3);
        double result = calc.perform(Operation.mod);

        assertEquals(1, result, 0);

    }

    @Test
    public void testSimpleModuloOperationNegative() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(6.0);
        calc.push(2);
        double result = calc.perform(Operation.mod);

        assertNotEquals(1, result, 0);

    }

    @Test
    public void testSimplesinTest90Degrees() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(90.0);
        double result = calc.perform(Operation.sin);

        assertNotEquals(1, result, 0);

    }

    @Test
    public void testSimplecosTest90Degrees() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(90.0);
        double result = calc.perform(Operation.cos);

        assertNotEquals(0, result, 0);

    }

    @Test
    public void testRandomMinMax() throws Exception {

        Calculator calc = new CalculatorImpl(18);
        calc.push(9.0);
        calc.push(18.0);
        double result = calc.perform(Operation.rnd);

        assertEquals(9, result, 0);


    }

    @Test
    public void testRandomMaxMin() throws Exception {

        Calculator calc = new CalculatorImpl(5);
        calc.push(22.0);
        calc.push(8.0);
        double result = calc.perform(Operation.rnd);

        assertEquals(10, result, 0);


    }

    @Test
    public void testCalcSkalarPos() throws Exception {
        Calculator calc = new CalculatorImpl();
        calc.push(1.0);
        calc.push(3.0);
        calc.push(2.0);
        calc.push(4.0);
        calc.push(2.0);
        double result = calc.perform(Operation.dp);
        assertEquals(14, result, 0);

    }

    @Test
    public void testCalcSkalarNeg() throws Exception {
        Calculator calc = new CalculatorImpl();
        calc.push(7.0);
        calc.push(8.0);
        calc.push(4.0);
        calc.push(4.0);
        calc.push(2.0);
        double result = calc.perform(Operation.dp);
        assertNotEquals(1, result, 0);
    }

    @Test
    public void testStoreAndLoad() throws Exception{
        CalculatorStore calculatorStore = new CalculatorStore();
        calculatorStore.storeValue("A", 42.0);
        double loadedValue = calculatorStore.loadValue("A");
        assertEquals(42.0, loadedValue, 0);
    }

    @Test
    public void testLoadInvalidName() throws Exception{
        CalculatorStore calculatorStore = new CalculatorStore();
        assertThrows(CalculatorException.class, () -> calculatorStore.loadValue("InvalidName"));
    }

}