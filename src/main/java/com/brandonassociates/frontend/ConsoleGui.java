package com.brandonassociates.frontend;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import com.brandonassociates.backend.ICalculator;

public class ConsoleGui implements ICalculatorUi {
    private ICalculator calculator;
    private Map<Integer, ConsoleCommand> commands;
    
    private Scanner inputScanner;

    public ConsoleGui(ICalculator calculator) {
        this.calculator = calculator;
        this.inputScanner = new Scanner(System.in);

        this.commands = new HashMap<Integer, ConsoleCommand>();
        this.commands.put(1, new ConsoleCommand("Perform Calculation", () -> performCalculation()));
        this.commands.put(2, new ConsoleCommand("Exit (CTRL+C)", () -> System.exit(0)));
    }

    @Override
    public void start() {
        showMenu();
    }

    private void showMenu() {
        final int defaultUserChoice = -1;
        int userChoice;

        do {
            System.out.println("======= Menu =======");
            System.out.println("Input \t\tDescription");
            for (Entry<Integer, ConsoleCommand> entry : commands.entrySet()) {
                System.out.println(entry.getKey() + " \t\t" + entry.getValue().getName());
            }
            System.out.print("Enter choice (number): ");
            String userChoiceStr = inputScanner.nextLine(); // Read user input
            try {
                userChoice = Integer.parseInt(userChoiceStr);
            } catch (NumberFormatException e) {
                userChoice = defaultUserChoice;
            }
        } while (!commands.containsKey(userChoice));

        ConsoleCommand selectedCommand = commands.get(userChoice);
        Thread commandThread = new Thread(selectedCommand.getAction());
        commandThread.start();
        try {
            commandThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!selectedCommand.getName().equals("Exit (CTRL+C)")) {
            showMenu();
        }
    }

    private void performCalculation() {
        System.out.print("Enter expression: ");
        String expression = inputScanner.nextLine(); // Read user input

        double result = calculator.calculate(expression);
        System.out.println("\n\nResult: " + result); // Output user input
        System.out.println();

    }
}
