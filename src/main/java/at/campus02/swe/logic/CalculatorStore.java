package at.campus02.swe.logic;

import java.util.HashMap;

public class CalculatorStore {

    private HashMap<String, Double> storeMap = new HashMap<>();

    public void storeValue(String name, double value) {
        storeMap.put(name, value);
    }

    public double loadValue(String name) {
        return storeMap.getOrDefault(name, 0.0);
    }

    public void clearStore() {
        storeMap.clear();
    }
}