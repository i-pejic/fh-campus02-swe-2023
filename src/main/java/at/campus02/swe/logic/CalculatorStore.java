package at.campus02.swe.logic;

import at.campus02.swe.CalculatorException;

import java.util.HashMap;

public class CalculatorStore {

    private HashMap<String, Double> storeMap = new HashMap<>();

    public void storeValue(String name, double value) throws CalculatorException {
        storeMap.put(name, value);
    }

    public double loadValue(String name) throws CalculatorException {
        if(storeMap.get(name)==null) throw new CalculatorException();
        return storeMap.getOrDefault(name, 0.0);
    }

    public void clearStore() {
        storeMap.clear();
    }
}