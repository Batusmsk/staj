package com.example.banking.app;

import com.example.banking.proje.Account;

import java.util.HashSet;
import java.util.Set;

public class HashSetAccountApp {
    public  static void main(String[] args) {
        Set<Account> accounts = new HashSet<>();
        accounts.add(new Account("TR5", 5000));
        System.out.println(accounts.contains(new Account("TR5", 5000)));
    }

}
