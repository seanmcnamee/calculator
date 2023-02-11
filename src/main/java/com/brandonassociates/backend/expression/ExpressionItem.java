package com.brandonassociates.backend.expression;

import com.brandonassociates.backend.operations.IOperator;

public class ExpressionItem {
    private enum ExpressionType {
        OPERAND,
        OPERATOR
    }

    private ExpressionType type;
    private double operand;
    private IOperator operator;

    public ExpressionItem(double operand) {
        this.operand = operand;
        this.type = ExpressionType.OPERAND;
    }

    public ExpressionItem(IOperator operator) {
        this.operator = operator;
        this.type = ExpressionType.OPERATOR;
    }

    public boolean isOperand() {
        return this.type == ExpressionType.OPERAND;
    }

    public boolean isOperator() {
        return this.type == ExpressionType.OPERATOR;
    }

    public double getOperand() {
        return operand;
    }

    public IOperator getOperator() {
        return this.operator;
    }
}