package com.example.batuhan.proje;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Customer {

    private final String identityNo;
    private String fullName;
    private List<Account> accounts;
    
    public Customer(String identityNo, String fullName) {

        this.identityNo = identityNo;
        this.fullName = fullName;

        accounts = new ArrayList<>();
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public int getNumberOfAccount() {

        return accounts.size();
    }

    public Account getAccount(int index) {
        return accounts.get(index);
    }

    public Optional<Account> getAccount(String iban) {

        for (Account acc : accounts) {
            if (acc.getIban().equals(iban)) {
                return Optional.of(acc);
            }
            
        }
        //System.out.println(iban + "İbanlı bir hesap bulunamadı.");
        return Optional.empty();
    }

    public void addAccount(Account acc) {

        this.accounts.add(acc);
    }

    public Account removeAccount(int index) {

        return accounts.remove(index);
    }

    public Optional<Account> removeAccount(String iban) {
    
        Optional<Account> account = getAccount(iban);
        account.ifPresent(value -> accounts.remove(account.get()));
        return account;
    }
}
