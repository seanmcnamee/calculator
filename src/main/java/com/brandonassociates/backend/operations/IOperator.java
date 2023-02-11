package com.brandonassociates.backend.operations;

import java.security.InvalidAlgorithmParameterException;

public interface IOperator {
    /**
     * The calculation to occur
     * @param operandA The first operand
     * @param operandB The second operand
     * @return The result of the given operator's calculation
     */
    public double calculate(double operandA, double operandB) throws IllegalStateException;

    /**
     * The character that users can input to trigger this operation
     * @return
     */
    public Character getOperatorCharacter();

    /**
     * The lower the value, the higher the priority
     * @return
     */
    public int getPriorityLevel();
}
