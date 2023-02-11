package com.brandonassociates.backend.operations;

public class MultiplyOperator implements IOperator {
    @Override
    public double calculate(double operandA, double operandB) {
        return operandA * operandB;
    }

    @Override
    public Character getOperatorCharacter() {
        return '*';
    }
    
    @Override
    public int getPriorityLevel() {
        return 2;
    }
}
