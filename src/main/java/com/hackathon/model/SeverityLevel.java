package com.hackathon.model;

public enum SeverityLevel {
    IMMEDIATE(4),
    URGENT(3),
    SOON(2),
    STANDARD(1);

    private final int priority;

    SeverityLevel(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
