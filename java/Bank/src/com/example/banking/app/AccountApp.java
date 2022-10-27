package com.example.banking.app;

import java.util.Scanner;

import com.example.banking.proje.Account;
import com.example.banking.proje.InsufficientBalanceException;

public class AccountApp {

    public static void main(String[] args) throws Throwable {

        Account acc;
        System.out.println(Account.counter);
        acc = new Account("TR1", 50000);

        Scanner input = new Scanner(System.in);
        System.out.println(Account.counter);

        String islem;
        int miktar;


        try {
            acc.withdraw(5000);

        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            throw e;
        }

        acc.deposit(32500);


        System.out.println(acc.toString());
    }

}
