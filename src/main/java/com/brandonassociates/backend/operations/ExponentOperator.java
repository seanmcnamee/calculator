package com.brandonassociates.backend.operations;

public class ExponentOperator implements IOperator {
    @Override
    public double calculate(double operandA, double operandB) {
        return Math.pow(operandA, operandB);
    }

    @Override
    public Character getOperatorCharacter() {
        return '^';
    }

    @Override
    public int getPriorityLevel() {
        return 1;
    }
}
