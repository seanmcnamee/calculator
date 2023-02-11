package com.brandonassociates.backend;

import java.util.Set;

public interface ICalculator {
    public double calculate(String inputString);
    public Set<Character> getSupportedOperations();
}
