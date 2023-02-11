package com.brandonassociates.backend.expression;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.brandonassociates.backend.operations.IOperator;

public class ExpressionStack implements IExpression {
    private Deque<IOperator> operatorStack;
    private List<ExpressionItem> postFixExpression;

    public ExpressionStack() {
        operatorStack = new LinkedList<>();
        postFixExpression = new ArrayList<>();
    }

    @Override
    public double calculate() throws IllegalStateException {
        if (!operatorStack.isEmpty()) {
            while (!operatorStack.isEmpty()) {
                postFixExpression.add(new ExpressionItem(operatorStack.pop()));
            }
        }
        if (postFixExpression.isEmpty()) {
            throw new IllegalStateException("The expression does not contain any elements");
        }

        Deque<ExpressionItem> expressionStack = new LinkedList<>();
        for (ExpressionItem expressionItem : postFixExpression) {
            if (expressionItem.isOperator()) {
                if (expressionStack.size() < 2) {
                    System.out.println("Current expression: " + expressionStack);
                    throw new IllegalStateException("An operator was supplied without applicable operands");
                }

                ExpressionItem operandB = expressionStack.pop();
                ExpressionItem operandA = expressionStack.pop();
                if (!operandB.isOperand() || !operandA.isOperand()) {
                    throw new IllegalStateException("An operator was supplied without applicable operands");
                }

                // Calculate and put result back on stack
                double result = expressionItem.getOperator().calculate(operandA.getOperand(), operandB.getOperand());
                expressionStack.push(new ExpressionItem(result));
            } else {
                expressionStack.push(new ExpressionItem(expressionItem.getOperand()));
            }
        }

        if (expressionStack.size() != 1 || !expressionStack.peek().isOperand()) {
            throw new IllegalStateException("The expression was unable to be calculated");
        }

        return expressionStack.pop().getOperand();
    }

    @Override
    public void appendOperand(double operand) {
        // Operands always get immediately added to the final expression
        System.out.println("Putting operand '" + operand + "' to the postFixExpression");
        postFixExpression.add(new ExpressionItem(operand));
        logInfo();
    }

    @Override
    public void appendOperator(IOperator operator) {
        // Handle edge case - parenthasis
        if (operator.getOperatorCharacter() == '(') {
            operatorStack.push(operator);
            System.out.println("Adding open parenthasis");
        } else if (operator.getOperatorCharacter() == ')') {
            System.out.println("Closing parenthasis...");
            while (!operatorStack.isEmpty() && operatorStack.peek().getOperatorCharacter() != '(') {
                // Stack all operators until the parenthasis is handled
                postFixExpression.add(new ExpressionItem(operatorStack.pop()));
            }
            // Remove the starting parenthasis
            if (operatorStack.peek().getOperatorCharacter() == '(') {
                operatorStack.pop();
            }
        } else {

            // Handle main case - operators
            if (operatorStack.isEmpty() || operator.getPriorityLevel() < operatorStack.peek().getPriorityLevel()) {
                // Higher precedence operators occur first
                System.out.println("Adding operator '" + operator.getOperatorCharacter().toString()
                        + "' to operatorStack, because it has higher precedence");
                operatorStack.push(operator);
            } else {
                while (!operatorStack.isEmpty() &&
                        operator.getPriorityLevel() >= operatorStack.peek().getPriorityLevel()) {
                    // Stack all operators until the priority level is reached
                    System.out.println("Moving operator '" + operatorStack.peek().getOperatorCharacter().toString() + "' from operatorStack to the postFixExpression");
                    postFixExpression.add(new ExpressionItem(operatorStack.pop()));
                }
                // Since the priority level is reached, add the operator
                operatorStack.push(operator);
                System.out.println("Putting operator '" + operator.getOperatorCharacter().toString() + "' onto operatorStack");
            }
        }
        logInfo();
    }

    public void logInfo() {
        System.out.println("Expression Stack:");
        System.out.println("\t operator stack: ");
        for (IOperator operator : operatorStack) {
            System.out.println("\t\t" + operator.getOperatorCharacter());
        }
        System.out.println("\t postFixExpression list: ");
        System.out.print("\t\t");
        for (ExpressionItem expressionItem : postFixExpression) {
            System.out.print((expressionItem.isOperand() ? expressionItem.getOperand()
                    : expressionItem.getOperator().getOperatorCharacter().toString()) + "  ");
        }
        System.out.println("");
    }

}
