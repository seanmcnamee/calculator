package com.brandonassociates.backend.operations;

public class ParenthasisCloseOperator implements IOperator {
    @Override
    public double calculate(double operandA, double operandB) throws IllegalStateException {
        throw new IllegalStateException("Can not perform a calculation with operator: " + getOperatorCharacter());
    }

    @Override
    public Character getOperatorCharacter() {
        return ')';
    }

    @Override
    public int getPriorityLevel() {
        return Integer.MAX_VALUE;
    }
}
