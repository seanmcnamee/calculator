package com.brandonassociates.backend.expression;

import com.brandonassociates.backend.operations.IOperator;

public interface IExpression {
    double calculate();
    void appendOperand(double operand);
    void appendOperator(IOperator operator);
    void logInfo();
}
