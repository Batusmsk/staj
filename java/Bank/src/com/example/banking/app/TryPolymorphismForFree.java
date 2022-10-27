package com.example.banking.app;

import java.security.SecureRandom;
import java.util.Random;

import com.example.banking.proje.*;

public class TryPolymorphismForFree {

    private static final Random random = new SecureRandom();

    public static void main(String[] args) {
        Account acc;

        if (random.nextBoolean()) {
            acc = new Account("TR1", 10000);
        } else {
            acc = new CheckingAccount("TR2", 20000, 5000);
        }
        
        System.out.println(acc.getClass().getName());
        try {
            acc.withdraw(15000);
        } catch (InsufficientBalanceException e) {
            throw new RuntimeException(e);
        }
    }

}
