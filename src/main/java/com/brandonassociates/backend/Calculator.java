package com.brandonassociates.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.brandonassociates.backend.expression.ExpressionStack;
import com.brandonassociates.backend.expression.IExpression;
import com.brandonassociates.backend.operations.AddOperator;
import com.brandonassociates.backend.operations.ParenthasisCloseOperator;
import com.brandonassociates.backend.operations.DivideOperator;
import com.brandonassociates.backend.operations.ExponentOperator;
import com.brandonassociates.backend.operations.IOperator;
import com.brandonassociates.backend.operations.MultiplyOperator;
import com.brandonassociates.backend.operations.ParenthasisOpenOperator;
import com.brandonassociates.backend.operations.SubtractOperator;

/**
 * Calculator that accepts infix-notated inputs
 */
public class Calculator implements ICalculator {
    private final List<IOperator> operators;
    private final Map<Character, IOperator> delimitersMap;
    private final List<Character> operatorCharArr;

    public Calculator() {
        // List of operators
        this.operators = Arrays.asList(
                new ParenthasisOpenOperator(),
                new ParenthasisCloseOperator(),
                new ExponentOperator(),
                new MultiplyOperator(),
                new DivideOperator(),
                new AddOperator(),
                new SubtractOperator());

        // Supporting information for parsing input
        delimitersMap = new HashMap<>();
        operatorCharArr = new ArrayList<>();
        for (IOperator operator : operators) {
            Character operatorChar = operator.getOperatorCharacter();
            delimitersMap.put(operatorChar, operator);
            operatorCharArr.add(operatorChar);
        }
    }

    @Override
    public double calculate(String inputString) {
        IExpression expression = new ExpressionStack();

        int i = 0;
        String stringOfExpressionItems = inputString;
        while (i < stringOfExpressionItems.length()) {
            //Find the next operator
            Character currentChar = stringOfExpressionItems.charAt(i);
            boolean isMatch = false;
            for (Character operatorChar : operatorCharArr) {
                if (operatorChar.equals(currentChar)) {
                    isMatch = true;
                    break;
                }
            }

            if (!isMatch) {
                i++;
                continue;
            }

            //An operator was found
            if (i > 0) {
                //Parse and add any number if applicable
                int value = Integer.parseInt(stringOfExpressionItems.substring(0, i));
                expression.appendOperand(value);
            }
            //Then the operator itself
            expression.appendOperator(delimitersMap.get(currentChar));

            //Only look at the unprocessed part of the stringOfExpressionItems
            stringOfExpressionItems = stringOfExpressionItems.substring(i + 1);
            i = 0;
        }

        //Handle final number at the end of the stringOfExpressionItems
        if (stringOfExpressionItems.length() > 0) {
            int value = Integer.parseInt(stringOfExpressionItems);
            expression.appendOperand(value);
        }

        expression.logInfo();
        return expression.calculate();
    }

    @Override
    public Set<Character> getSupportedOperations() {
        return delimitersMap.keySet();
    }
}
