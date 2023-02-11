package com.brandonassociates.frontend;

public class ConsoleCommand {
    private String name;
    private Runnable action;

    public ConsoleCommand(String name, Runnable action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }
    public Runnable getAction() {
        return action;
    }
}
