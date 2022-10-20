package com.example.bank.app;

import java.security.SecureRandom;
import java.util.Random;

import com.example.bank.proje.*;

public class TryPolymorphismForFree {

    private static final Random random = new SecureRandom();

    public static void main(String[] args) {
        Account acc;

        if (random.nextBoolean()) {
            acc = new Account("TR1", 10000);
        } else {
            acc = new checkingAccount("TR2", 20000, 5000);
        }
        
        System.out.println(acc.getClass().getName());
        acc.withdraw(15000);
    }

}
