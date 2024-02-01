package at.campus02.swe.logic;


import at.campus02.swe.Calculator;
import at.campus02.swe.CalculatorException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class CalculatorImpl implements Calculator {


    private CalculatorStore calculatorStore = new CalculatorStore();

    private Stack<Double> stack_ = new Stack<Double>();
    private Random random;

    public CalculatorImpl() {
        random = new Random();
    }

    public CalculatorImpl(int seed) {
        random = new Random(seed);
    }

    @Override
    public double perform(Operation op) throws CalculatorException {

        switch (op) {
            case sin, cos, dp -> push(0);
        }


        double b = pop();
        double a = pop();

        switch (op) {
            case add:
                return a + b;
            case sub:
                return a - b;
            case div:
                double c = a / b;
                if (Double.isInfinite(c))
                    throw new CalculatorException("Division by zero");
                return c;
            case mul:
                return a * b;
            case mod:
                return a % b;
            case sin:
                return Math.sin(a);
            case cos:
                return Math.cos(a);
            case rnd:
                return generateRandomNumber(a, b);
            case dp:
                return generateDotProduct((int) a);
        }
        return 0;
    }

    public int generateRandomNumber(double a, double b) throws CalculatorException {
        double maximum = a;
        double minimum = b;

        if (minimum > maximum) {
            double temp = minimum;
            minimum = maximum;
            maximum = temp;
        }

        //double result = minimum + (maximum - minimum) * Math.random();

        int result = random.nextInt((int) maximum + 1 - (int) minimum) + (int) minimum;
        return result;
    }

    public int generateDotProduct(int numberOfElements) throws CalculatorException {
        ArrayList<Integer> firstScalar = new ArrayList<>();
        ArrayList<Integer> secondScalar = new ArrayList<>();

        int dotProduct = 0;

        for (int i = numberOfElements; i > 0; i--) {
            firstScalar.add((int) pop());
        }

        for (int i = numberOfElements; i > 0; i--) {
            secondScalar.add((int) pop());
        }

        for (int i = (numberOfElements - 1); i >= 0; i--) {
            dotProduct += firstScalar.get(i) * secondScalar.get(i);
        }

        return dotProduct;
    }

    @Override
    public double pop() throws CalculatorException {
        if (stack_.isEmpty())
            throw new CalculatorException();
        return stack_.pop();
    }

    @Override

    public void store(String name) throws CalculatorException {
        if (stack_.isEmpty()) {
            throw new CalculatorException("Cannot store empty stack");
        }
        double valueToStore = stack_.peek();
        calculatorStore.storeValue(name, valueToStore);
    }

    @Override
    public void load(String name) throws CalculatorException {
        double loadedValue = calculatorStore.loadValue(name);
        stack_.push(loadedValue);

    }

    @Override
    public void push(double v) {
        stack_.push(v);
    }

    @Override
    public void clear() {
        stack_.clear();
    }

}
