package com.brandonassociates;

import com.brandonassociates.backend.Calculator;
import com.brandonassociates.backend.ICalculator;
import com.brandonassociates.frontend.ConsoleGui;
import com.brandonassociates.frontend.ICalculatorUi;

public class App 
{
    public static void main( String[] args )
    {
        ICalculator calculator = new Calculator();
        ICalculatorUi userInterface = new ConsoleGui(calculator);
        userInterface.start();
    }
}
