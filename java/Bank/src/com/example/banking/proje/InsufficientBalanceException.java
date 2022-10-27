package com.example.banking.proje;

public class InsufficientBalanceException extends Throwable {
    private final double deficit;

    public InsufficientBalanceException(String message, double deficit) {
        super(message);
        this.deficit = deficit;
    }

    public double getDeficit() {
        return deficit;
    }
}
